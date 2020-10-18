package com.corfid.fideicomisos.service.impl.seguridad;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.service.administrativo.OlvidoPasswordInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.utilities.EnvioMailInterface;
import com.corfid.fideicomisos.service.utilities.RestClientInterface;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Service("olvidoPasswordServiceImpl")
public class OlvidoPasswordServiceImpl implements OlvidoPasswordInterface {

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

    public String recuperarContrasenia(String numeroDocumento,
                                       String opcion,
                                       String telefono,
                                       String correo) throws Exception {
        String codigo = null;
        try {
            if (StringUtil.equiv(opcion, "M")) {
                System.out.print("********************************Mail**********************************");

                envioMailInterface.sendEmail(correo, "Correo de verificacion", "Hola que hace");
                codigo = ConstantesError.ERROR_0;
            } else {
                System.out.print("********************************SMS**********************************");

                codigo = restClientInterface.getObtenerPinValidacion(telefono);

            }

            return codigo;
        } catch (Exception e) {
            System.out.println("Error de envío==================>" + e.getMessage());
            return codigo;
        }
    }

    public Map<String, String> buscarUsuarioRecuperacion(String numeroDocumento) throws Exception {
        Map<String, String> output = new HashMap<String, String>();
        try {
            UsuarioModel usuarioModel;
            PersonaModel personaModel;
            usuarioModel = usuarioInterface.findUsuarioByUsuario(numeroDocumento);

            if (!StringUtil.isEmpty(usuarioModel)) {
                personaModel = personaInterface.findPersonaByIdModel(usuarioModel.getIdPersona());
                output.put("telefono", personaModel.getNumeroTelefono());
                output.put("correo", personaModel.getDireccionEmail());
                output.put("nombre", personaModel.getNombreCompleto());

                String correoReemplado = personaModel.getDireccionEmail().substring(0,
                                                                                    personaModel.getDireccionEmail().indexOf("@") - 3).replaceAll("[a-zA-Z0-9_]",
                                                                                                                                                  "*") + personaModel.getDireccionEmail().substring(personaModel.getDireccionEmail().indexOf("@") - 3,
                                                                                                                                                                                                    personaModel.getDireccionEmail().length());
                output.put("correoReemplazado", correoReemplado);
                output.put("codigoError", ConstantesError.ERROR_0);
                return output;
            } else {
                output.put("codigoError", ConstantesError.ERROR_0);
                return output;
            }
        } catch (Exception e) {
            output.put("codigoError", ConstantesError.ERROR_0);
            return output;
        }
    }

    public String validarPin(String codigo, String pin, String password, String numeroDocumento) throws Exception {
        try {

            codigo = restClientInterface.validarPin(codigo, pin);

            return codigo;
        } catch (Exception e) {
            System.out.println("Error de envío==================>" + e.getMessage());
            return codigo;
        }
    }

}
