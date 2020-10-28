package com.corfid.fideicomisos.service.impl.seguridad;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.model.administrativo.CatalogoMailModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.service.administrativo.CatalogoMailInterface;
import com.corfid.fideicomisos.service.administrativo.OlvidoPasswordInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.utilities.EnvioMailInterface;
import com.corfid.fideicomisos.service.utilities.RestClientInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Service("olvidoPasswordServiceImpl")
public class OlvidoPasswordServiceImpl extends AbstractService implements OlvidoPasswordInterface {

    @Autowired
    @Qualifier("envioEmail")
    EnvioMailInterface envioMailInterface;

    @Autowired
    @Qualifier("restClientRecoveryPasswordPin")
    RestClientInterface restClientInterface;

    @Autowired
    @Qualifier("usuarioServiceImpl")
    UsuarioInterface usuarioInterface;

    @Autowired
    @Qualifier("personaServiceImpl")
    PersonaInterface personaInterface;

    @Autowired
    @Qualifier("catalogoMailServiceImpl")
    CatalogoMailInterface catalogoMailInterface;

    public String recuperarContrasenia(String numeroDocumento,
                                       String opcion,
                                       String telefono,
                                       String correo) throws Exception {
        String codigo = null;
        Map<String, String> output;
        try {
            if (StringUtil.equiv(opcion, "M")) {
                output = enviarPinCorreo(numeroDocumento, Constante.CODIGO_EMAIL_VALIDACION_PIN, null, null);

                if (_equiv(_toStr(output.get("codigoError")), ConstantesError.ERROR_0)) {
                    codigo = _toStr(output.get("pin"));
                } else {
                    codigo = _toStr(output.get("codigoError"));
                }

            } else {
                codigo = restClientInterface.getObtenerPinValidacion(telefono);

            }

            return codigo;
        } catch (

        Exception e) {
            return codigo;
        }
    }

    public Map<String, String> buscarUsuarioRecuperacion(String numeroDocumento) throws Exception {
        Map<String, String> output = new HashMap<String, String>();
        try {
            UsuarioModel usuarioModel;
            PersonaModel personaModel;
            String correoReemplado;
            String telefonoReemplado;
            usuarioModel = usuarioInterface.findUsuarioByUsuario(numeroDocumento);

            if (!StringUtil.isEmpty(usuarioModel)) {
                personaModel = personaInterface.findPersonaByIdModel(usuarioModel.getIdPersona());

                output.put("telefono", StringUtil.toBlank(personaModel.getNumeroTelefono(), "No Registrado"));
                output.put("correo", StringUtil.toBlank(personaModel.getDireccionEmail(), "No Registrado"));
                output.put("nombre", personaModel.getNombreCompleto());

                if (!_isEmpty(personaModel.getDireccionEmail())) {
                    correoReemplado = personaModel.getDireccionEmail().substring(0,
                                                                                 personaModel.getDireccionEmail().indexOf("@") - 3).replaceAll("[a-zA-Z0-9_]",
                                                                                                                                               "*") + personaModel.getDireccionEmail().substring(personaModel.getDireccionEmail().indexOf("@") - 3,
                                                                                                                                                                                                 personaModel.getDireccionEmail().length());
                } else {
                    correoReemplado = StringUtil.toBlank(personaModel.getDireccionEmail(), "No Registrado");
                }
                if (!_isEmpty(personaModel.getNumeroTelefono())) {
                    telefonoReemplado = personaModel.getNumeroTelefono().substring(0,
                                                                                   personaModel.getNumeroTelefono().length() - 3).replaceAll("[0-9_]",
                                                                                                                                             "*") + personaModel.getNumeroTelefono().substring(personaModel.getNumeroTelefono().length() - 3,
                                                                                                                                                                                               personaModel.getNumeroTelefono().length());
                } else {
                    telefonoReemplado = StringUtil.toBlank(personaModel.getNumeroTelefono(), "No Registrado");
                }
                output.put("correoReemplazado", correoReemplado);
                output.put("telefonoReemplazado", telefonoReemplado);
                output.put("codigoError", ConstantesError.ERROR_0);
                return output;
            } else {
                output.put("codigoError", ConstantesError.ERROR_1);
                return output;
            }
        } catch (Exception e) {
            output.put("codigoError", ConstantesError.ERROR_1);
            return output;
        }
    }

    public String validarPin(String codigo,
                             String pin,
                             String password,
                             String numeroDocumento,
                             String opcion) throws Exception {
        try {
            if (StringUtil.equiv(opcion, "M")) {
                if (StringUtil.equiv(pin, codigo)) {
                    codigo = ConstantesError.ERROR_0;
                    usuarioInterface.actualizarContrasenia(numeroDocumento, password);

                    enviarCorreo(numeroDocumento, Constante.CODIGO_EMAIL_CAMBIO_PIN, null, null);
                } else {
                    codigo = ConstantesError.ERROR_31;
                }
            } else {
                codigo = restClientInterface.validarPin(codigo, pin);

                if (_equiv(codigo, ConstantesError.ERROR_0)) {
                    usuarioInterface.actualizarContrasenia(numeroDocumento, password);

                    enviarCorreo(numeroDocumento, Constante.CODIGO_EMAIL_CAMBIO_PIN, null, null);
                }
            }
            return obtenerMensajeError(codigo);
        } catch (Exception e) {
            return codigo;
        }
    }

    public String cambiarContrasenia(String userName, String password) throws Exception {
        try {
            usuarioInterface.actualizarContrasenia(userName, password);

            return obtenerMensajeError(ConstantesError.ERROR_0);
        } catch (Exception e) {
            return ConstantesError.ERROR_1;
        }
    }
    
    public String cambiarContraseniaAndIndicador(String userName, String password) throws Exception {
        try {
            usuarioInterface.actualizarContraseniaAndIndicador(userName, password);

            return obtenerMensajeError(ConstantesError.ERROR_0);
        } catch (Exception e) {
            return ConstantesError.ERROR_1;
        }
    }

    public String validarContrasenia(String password, String password2) throws Exception {
        try {
            String error;
            error = usuarioInterface.validarContrasenia(password, password2);

            if (!_equiv(error, ConstantesError.ERROR_0)) {
                return error;
            } else {
                return ConstantesError.ERROR_0;
            }
        } catch (Exception e) {
            return ConstantesError.ERROR_1;
        }
    }

    public Map<String, String> enviarCorreo(String numeroDocumento,
                                            String codigo,
                                            String nombreEmpresa,
                                            String password) throws Exception {
        Map<String, String> resultado = new HashMap<String, String>();
        try {
            Map<String, String> output;
            String nombreCompleto;
            String correo;
            String cuerpo;

            resultado.put("codigoError", ConstantesError.ERROR_0);

            output = buscarUsuarioRecuperacion(numeroDocumento);
            nombreCompleto = _toStr(output.get("nombre"));
            correo = _toStr(output.get("correo"));

            CatalogoMailModel catalogoMailModel = catalogoMailInterface.obtenerCorreoPorCodigo(codigo);
            cuerpo = catalogoMailModel.getContenido().replace("NOMBRE_COMPLETO", nombreCompleto);
            envioMailInterface.sendEmail(correo, catalogoMailModel.getAsunto(), cuerpo);
            return resultado;
        } catch (Exception e) {
            resultado.put("codigoError", ConstantesError.ERROR_1);
            return resultado;
        }
    }

    public Map<String, String> enviarPinCorreo(String numeroDocumento,
                                               String codigo,
                                               String nombreEmpresa,
                                               String password) throws Exception {
        Map<String, String> resultado = new HashMap<String, String>();
        try {
            Map<String, String> output;
            String nombreCompleto;
            String correo;
            String cuerpo;
            String pin = _toStr(50000 * Math.random());
            pin = pin.substring(0, 4);

            resultado.put("codigoError", ConstantesError.ERROR_0);

            output = buscarUsuarioRecuperacion(numeroDocumento);
            nombreCompleto = _toStr(output.get("nombre"));
            correo = _toStr(output.get("correo"));

            CatalogoMailModel catalogoMailModel = catalogoMailInterface.obtenerCorreoPorCodigo(codigo);
            cuerpo = catalogoMailModel.getContenido().replace("NOMBRE_COMPLETO", nombreCompleto);
            cuerpo = cuerpo.replace("PIN", pin);
            envioMailInterface.sendEmail(correo, catalogoMailModel.getAsunto(), cuerpo);
            resultado.put("pin", pin);
            return resultado;
        } catch (Exception e) {
            resultado.put("codigoError", ConstantesError.ERROR_1);
            return resultado;
        }
    }
}
