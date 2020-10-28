package com.corfid.fideicomisos.controller.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.service.administrativo.MenuInterface;
import com.corfid.fideicomisos.service.administrativo.OlvidoPasswordInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/cambioPasswordInterno")
public class CambioPasswordInternoController extends InitialController {

    @Autowired
    @Qualifier("olvidoPasswordServiceImpl")
    OlvidoPasswordInterface olvidoPasswordInterface;

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    @Autowired
    @Qualifier("menuServiceImpl")
    MenuInterface menuInterface;

    @GetMapping("/cambioPassword")
    public ModelAndView cambioPassword(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales) {
        ModelAndView model;
        List<CatalogoConstraintModel> listCatalogoConstraintModelTipoDocumento;
        datosGenerales.setModulo(Constante.MODULO_CAMBIO_INTERNO);

        listCatalogoConstraintModelTipoDocumento = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA,
                                                                                                               Constante.TIPO_DOC);

        model = new ModelAndView(Constante.INTERNO);
        model.addObject("listTipoDocumento", listCatalogoConstraintModelTipoDocumento);

        return model;
    }

    @PostMapping("/primeraVez")
    public ModelAndView primeraVez() {
        ModelAndView model;
        List<CatalogoConstraintModel> listCatalogoConstraintModelTipoDocumento;

        listCatalogoConstraintModelTipoDocumento = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA,
                                                                                                               Constante.TIPO_DOC);

        model = new ModelAndView(Constante.PRIMERA_VEZ);
        model.addObject("listTipoDocumento", listCatalogoConstraintModelTipoDocumento);

        return model;
    }

    @PostMapping(value = "/cambioPassword", params = { "enviar" })
    public ModelAndView cambioPassword(@RequestParam(name = "password", required = true) String password,
                                       @RequestParam(name = "password2", required = true) String password2,
                                       @SessionAttribute("datosGenerales") DatosGenerales datosGenerales) throws Exception {
        ModelAndView mav;
        String result;
        String respuesta = olvidoPasswordInterface.validarContrasenia(password, password2);

        if (StringUtil.equiv(respuesta, ConstantesError.ERROR_0)) {
            result = olvidoPasswordInterface.cambiarContrasenia(datosGenerales.getUsuario(), password2);
        } else {
            result = respuesta;
        }
        mav = new ModelAndView(Constante.INTERNO);
        mav.addObject("mensaje", result);

        return mav;
    }

    @PostMapping(value = "/cambioPasswordPrimeraVez", params = { "enviar" })
    public ModelAndView cambioPasswordPrimeraVez(@RequestParam(name = "password", required = true) String password,
                                                 @RequestParam(name = "password2", required = true) String password2,
                                                 @SessionAttribute("datosGenerales") DatosGenerales datosGenerales) throws Exception {
        ModelAndView mav;
        String result;
        List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
        Collection<Integer> rolesSesion = new ArrayList<Integer>();
        Set<String> roles;

        String respuesta = olvidoPasswordInterface.validarContrasenia(password, password2);
        if (StringUtil.equiv(respuesta, ConstantesError.ERROR_0)) {
            result = olvidoPasswordInterface.cambiarContraseniaAndIndicador(datosGenerales.getUsuario(), password);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
            for (String rol : roles) {
                rolesSesion.add(StringUtil.toInteger(rol));
            }

            listMenuModel = menuInterface.obtenerMenuByRol(rolesSesion);
            datosGenerales.setListMenuModel(listMenuModel);
            datosGenerales.setMenu(construirMenu(listMenuModel));

            mav = new ModelAndView(Constante.MENU);
        } else {
            result = respuesta;
            mav = new ModelAndView(Constante.PRIMERA_VEZ);
            mav.addObject("mensaje", result);
        }

        return mav;
    }
}
