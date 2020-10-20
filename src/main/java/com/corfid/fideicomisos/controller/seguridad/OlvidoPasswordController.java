package com.corfid.fideicomisos.controller.seguridad;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.service.administrativo.OlvidoPasswordInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/olvido")
public class OlvidoPasswordController {

    @Autowired
    @Qualifier("olvidoPasswordServiceImpl")
    OlvidoPasswordInterface olvidoPasswordInterface;

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    @GetMapping("/olvidoPassword")
    public ModelAndView olvidoPassword() {
        ModelAndView model;
        List<CatalogoConstraintModel> listCatalogoConstraintModelTipoDocumento;

        listCatalogoConstraintModelTipoDocumento = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA,
                                                                                                               Constante.TIPO_DOC);

        model = new ModelAndView(Constante.OLVIDO);
        model.addObject("listTipoDocumento", listCatalogoConstraintModelTipoDocumento);

        return model;
    }

    @PostMapping(value = "/accion", params = { "volver" })
    public String volverLogin() {
        return "redirect:/";
    }

    @PostMapping(value = "/accion", params = { "enviar" })
    public ModelAndView recuperar(@RequestParam(name = "documento", required = true) String documento,
                                  @RequestParam(name = "opcion", required = true) String opcion,
                                  @RequestParam(name = "telefono", required = true) String telefono,
                                  @RequestParam(name = "correo", required = true) String correo) throws Exception {
        ModelAndView mav;

        String codigo = olvidoPasswordInterface.recuperarContrasenia(documento, opcion, telefono, correo);
        if (StringUtil.equiv(codigo, ConstantesError.ERROR_0)) {
            mav = new ModelAndView(Constante.LOGIN);
        } else if (StringUtil.isEmpty(codigo)) {
            mav = new ModelAndView(Constante.LOGIN);
        } else {
            mav = new ModelAndView(Constante.SELECCION_CAMBIO_PASSWORD);
            mav.addObject("numeroDocumento", documento);
            mav.addObject("idResp", codigo);
            mav.addObject("opcion", opcion);
        }

        return mav;
    }

    @PostMapping(value = "/busqueda_usuario", params = { "enviar" })
    public ModelAndView busquedaUsuario(@RequestParam(name = "documento", required = true) String documento,
                                        @RequestParam(name = "tipoDocumento", required = true) String tipoDocumento) throws Exception {
        ModelAndView mav;
        Map<String, String> result;

        result = olvidoPasswordInterface.buscarUsuarioRecuperacion(tipoDocumento + documento);
        if (StringUtil.equiv(result.get("codigoError"), ConstantesError.ERROR_0)) {
            mav = new ModelAndView(Constante.SELECCION_MODO_RECUPERACION);
            mav.addObject("numeroDocumento", tipoDocumento + documento);
            mav.addObject("telefono", result.get("telefono").toString());
            mav.addObject("correoReemplazado", result.get("correoReemplazado").toString());
            mav.addObject("telefonoReemplazado", result.get("telefonoReemplazado").toString());
            mav.addObject("correo", result.get("correo").toString());
        } else {
            mav = new ModelAndView(Constante.LOGIN);
        }

        return mav;
    }

    @PostMapping(value = "/busqueda_usuario", params = { "volver" })
    public String volverIngresoUsuario() {
        return "redirect:/";
    }

    @PostMapping(value = "/cambioPassword", params = { "enviar" })
    public ModelAndView cambioPassword(@RequestParam(name = "password", required = true) String password,
                                       @RequestParam(name = "password2", required = true) String password2,
                                       @RequestParam(name = "pin", required = true) String pin,
                                       @RequestParam(name = "idResp", required = true) String idResp,
                                       @RequestParam(name = "numeroDocumento", required = true) String numeroDocumento,
                                       @RequestParam(name = "opcion", required = true) String opcion) throws Exception {
        ModelAndView mav;
        String result;
        String respuesta = olvidoPasswordInterface.validarContrasenia(password, password2);

        if (StringUtil.equiv(respuesta, ConstantesError.ERROR_0)) {
            mav = new ModelAndView(Constante.SELECCION_CAMBIO_PASSWORD_CONFIRMACION);

            result = olvidoPasswordInterface.validarPin(idResp, pin, password, numeroDocumento, opcion);
        } else {
            mav = new ModelAndView(Constante.SELECCION_CAMBIO_PASSWORD);
            result = respuesta;
        }

        mav.addObject("mensaje", result);

        return mav;
    }

    @PostMapping(value = "/cambioPassword", params = { "volver" })
    public String volverValidacionPin() {
        return "redirect:/";
    }

    @PostMapping(value = "/confirmacion", params = { "volver" })
    public String cambioPassword() throws Exception {

        return "redirect:/";
    }

}
