package com.corfid.fideicomisos.controller.administrativo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.cruds.CrudRolModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.GenericModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.MenuInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.RolInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/rol")
public class RolController extends InitialController {

    @Autowired
    @Qualifier("rolServiceImpl")
    private RolInterface rolInterface;

    @Autowired
    @Qualifier("menuServiceImpl")
    MenuInterface menuInterface;

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    @Autowired
    @Qualifier("personaServiceImpl")
    private PersonaInterface personaInterface;

    @Autowired
    private Environment environment;

    public String obtenerIp() {
        return environment.getProperty("local.server.port");
    }

    @PostMapping("/cancel")
    public String cancel() {

        return "redirect:" + Constante.URL_LISTA_ROLES;
    }

    @GetMapping("/lista_roles")
    public ModelAndView showListaRoles(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                       Model model,
                                       @RequestParam(name = "result", required = false) String result) throws Exception {
        CrudRolModel crudRolModel = new CrudRolModel();
        crudRolModel.setResult(result);
        crudRolModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
        crudRolModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());

        return busqueda(Constante.CONST_VACIA,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudRolModel);
    }

    @GetMapping("/crudAccionesListaRoles")
    public String intercepcion() {
        return "redirect:/seleccion/seleccion";
    }

    @GetMapping("/addrol")
    public String intercepcionForm() {
        return "redirect:/seleccion/seleccion";
    }

    @PostMapping("/addrol")
    public ModelAndView registrarRol(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                     @ModelAttribute(name = "rolModel") RolModel rolModel,
                                     Model model) throws Exception {
        Date fechaAndHoraActual = getFechaAndHoraActual();
        CrudRolModel crudRolModel = new CrudRolModel();
        RolModel rolModelActualizado = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        setParametrosAuditoriaModel(fechaAndHoraActual,
                                    user.getUsername(),
                                    obtenerIp(),
                                    fechaAndHoraActual,
                                    user.getUsername(),
                                    obtenerIp());

        if (StringUtil.isEmpty(rolModel.getIdEmpresa())) {
            rolModel.setIdEmpresa(datosGenerales.getIdEmpresa());
        }
        rolModel.setTipoUsuarioSesion(datosGenerales.getTipoUsuarioSession());
        rolModelActualizado = rolInterface.addRol(rolModel, getParametrosAuditoriaModel());
        if (!StringUtil.isEmpty(rolModelActualizado)) {
            if (!StringUtil.isEmpty(rolModel.getMenu())) {
                if (rolInterface.updateMenuRol(rolModel.getMenu().split(","),
                                               rolModel.getIdRol(),
                                               getParametrosAuditoriaModel())) {
                    crudRolModel.setResult(Constante.RESULT_CORRECTO);
                } else {
                    crudRolModel.setResult(Constante.RESULT_ERROR);
                }
            }
        } else {
            crudRolModel.setResult(Constante.RESULT_ERROR);
        }

        crudRolModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
        crudRolModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
        return busqueda(Constante.CONST_VACIA,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudRolModel);
    }

    @PostMapping(value = "/crudAccionesListaRoles", params = { "findRow", "busqueda" })
    public ModelAndView buscarRol(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                  CrudRolModel crudRolModel,
                                  final BindingResult bindingResult,
                                  final HttpServletRequest req) throws Exception {
        String caja = req.getParameter("busqueda");
        crudRolModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
        crudRolModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());

        return busqueda(caja,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudRolModel);
    }

    @PostMapping(value = "/crudAccionesListaRoles", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerecha(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      CrudRolModel crudRolModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        String caja = req.getParameter("busqueda");
        String paginaActual = req.getParameter("paginaActual");
        String paginaFinal = req.getParameter("paginaFinal");
        crudRolModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
        crudRolModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());

        return busqueda(caja, Constante.CONST_CERO, Constante.DERECHA, paginaActual, paginaFinal, crudRolModel);
    }

    @PostMapping(value = "/crudAccionesListaRoles", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                        CrudRolModel crudRolModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) throws Exception {
        String caja = req.getParameter("busqueda");
        String paginaActual = req.getParameter("paginaActual");
        String paginaFinal = req.getParameter("paginaFinal");
        crudRolModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
        crudRolModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());

        return busqueda(caja, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual, paginaFinal, crudRolModel);
    }

    @PostMapping(value = "/crudAccionesListaRoles", params = { "addRow" })
    public ModelAndView addRol(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                               final CrudRolModel crudRolModel,
                               final BindingResult bindingResult) throws Exception {
        return preparingFormRol(null, datosGenerales.getTipoUsuarioSession(), datosGenerales.getIdEmpresa());
    }

    @PostMapping(value = "/crudAccionesListaRoles", params = { "editRow" })
    public ModelAndView editRol(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                final CrudRolModel crudRolModel,
                                final BindingResult bindingResult,
                                final HttpServletRequest req) throws Exception {

        return preparingFormRol(req.getParameter("editRow"),
                                datosGenerales.getTipoUsuarioSession(),
                                datosGenerales.getIdEmpresa());
    }

    @PostMapping(value = "/crudAccionesListaRoles", params = { "removeRow" })
    public ModelAndView removeRol(final CrudRolModel crudRolModel,
                                  final BindingResult bindingResult,
                                  final HttpServletRequest req) throws Exception {

        if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
            rolInterface.removeRol(StringUtil.toInteger(req.getParameter("removeRow")));
            crudRolModel.setResult(Constante.RESULT_CORRECTO);
        } else {
            crudRolModel.setResult(Constante.RESULT_ERROR);
        }
        return busqueda(Constante.CONST_VACIA,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudRolModel);
    }

    private ModelAndView preparingFormRol(Object id,
                                          String tipoUsuarioSesion,
                                          Integer idEmpresaSesion) throws Exception {
        ModelAndView model = new ModelAndView(Constante.FORM_ROL);
        List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
        List<MenuModel> listAllMenus;
        List<GenericModel> listGenericModel = null;
        List<GenericModel> listGenericModelVinculados = null;
        List<PersonaModel> listPersonaModel = null;
        List<GenericModel> listGenericPersonaModelVinculado = null;
        List<GenericModel> listGenericPersonaModel = null;
        PersonaModel personaModel = null;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RolModel rolModel = new RolModel();
        if (!StringUtil.isEmpty(id)) {
            Integer idRol = StringUtil.toInteger(id);
            rolModel = rolInterface.findRolByIdModel(idRol);
            if (!StringUtil.isEmpty(rolModel.getListMenu())) {
                listGenericModelVinculados = new ArrayList<GenericModel>();

                for (MenuModel menuModel : rolModel.getListMenu()) {
                    GenericModel genericModel = new GenericModel();
                    genericModel.setId(StringUtil.toStr(menuModel.getIdMenu()));
                    genericModel.setDescripcion(menuModel.getDescripcion());
                    listGenericModelVinculados.add(genericModel);
                }
            }
        }

        listAllMenus = menuInterface.listAllMenus(tipoUsuarioSesion);

        if (!StringUtil.isEmpty(listAllMenus)) {
            listGenericModel = new ArrayList<GenericModel>();

            for (MenuModel menuModel : listAllMenus) {
                GenericModel genericModel = new GenericModel();
                genericModel.setId(StringUtil.toStr(menuModel.getIdMenu()));
                genericModel.setDescripcion(menuModel.getDescripcion());
                listGenericModel.add(genericModel);
            }
        }

        String combo = construirComboSearch(Constante.SELECCION_MULTIPLE,
                                            listGenericModel,
                                            "menu",
                                            "Seleccione Menu..",
                                            listGenericModelVinculados);

        //esta lista es para mostrar las empresas disponibles
        listPersonaModel = personaInterface.obtenerAllEmpresas(tipoUsuarioSesion, idEmpresaSesion);

        if (!StringUtil.isEmpty(listPersonaModel)) {
            listGenericPersonaModel = new ArrayList<GenericModel>();

            for (PersonaModel personaModelTemp : listPersonaModel) {
                GenericModel genericModel = new GenericModel();
                genericModel.setId(StringUtil.toStr(personaModelTemp.getIdPersona()));
                genericModel.setDescripcion(personaModelTemp.getNombreCompleto());
                listGenericPersonaModel.add(genericModel);
            }
        }
        if (!StringUtil.isEmpty(id)) {
            if (!StringUtil.isEmpty(rolModel.getIdEmpresa())) {
                personaModel = personaInterface.findPersonaByIdModel(rolModel.getIdEmpresa());
                if (!StringUtil.isEmpty(personaModel)) {
                    listGenericPersonaModelVinculado = new ArrayList<GenericModel>();

                    GenericModel genericModel = new GenericModel();
                    genericModel.setId(StringUtil.toStr(personaModel.getIdPersona()));
                    genericModel.setDescripcion(personaModel.getNombreCompleto());
                    listGenericPersonaModelVinculado.add(genericModel);
                    listGenericPersonaModel.add(genericModel);
                }
            }
        }

        String comboEmpresa = construirComboSearch(Constante.SELECCION_SIMPLE,
                                                   listGenericPersonaModel,
                                                   "idEmpresa",
                                                   "Seleccione Empresa...",
                                                   listGenericPersonaModelVinculado);

        listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_ROLES,
                                                                                                                Constante.ESTADO_REGISTRO);

        model.addObject("rolModel", rolModel);
        model.addObject("usuario", user.getUsername());
        model.addObject("listEstado", listCatalogoConstraintModelEstadoRegistro);
        model.addObject("comboMenus", combo);
        model.addObject("comboEmpresa", comboEmpresa);

        return model;
    }

    private ModelAndView busqueda(String busqueda,
                                  String izquierda,
                                  String derecha,
                                  String pagina,
                                  String fin,
                                  CrudRolModel crudRolModel) throws Exception {
        ModelAndView mav = new ModelAndView(Constante.LISTA_ROLES);
        String result = crudRolModel.getResult();
        PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

        crudRolModel = rolInterface.listRolByDescripcionPaginado(busqueda,
                                                                 crudRolModel.getIdEmpresaSession(),
                                                                 crudRolModel.getTipoUsuarioSession(),
                                                                 paginadoModel.getPaginaActual(),
                                                                 Constante.PAGINADO_5_ROWS);

        if (StringUtil.equiv(result, Constante.RESULT_ERROR)) {
            crudRolModel.setMensaje(construirMensaje("Aviso",
                                                     "Ocurrió un Problema con la Operación",
                                                     Constante.MENSAJE_ERROR));
        } else if (StringUtil.equiv(result, Constante.RESULT_CORRECTO)) {
            crudRolModel.setMensaje(construirMensaje("Aviso",
                                                     "La Operacion se Realizó Satisfactoriamente",
                                                     Constante.MENSAJE_SATISFACTORIO));
        }

        crudRolModel.setResult(result);

        if (paginadoModel.isMovIzquierda()) {
            crudRolModel.setMensaje(construirMensaje("Aviso",
                                                     "No hay más Registros a la Izquierda",
                                                     Constante.MENSAJE_INFORMATIVO));
            crudRolModel.setResult(Constante.RESULT_ERROR);
        } else if (paginadoModel.isMovDerecha()) {
            crudRolModel.setMensaje(construirMensaje("Aviso",
                                                     "No hay más Registros a la Derecha",
                                                     Constante.MENSAJE_INFORMATIVO));
            crudRolModel.setResult(Constante.RESULT_ERROR);
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        crudRolModel.setBusqueda(busqueda);
        crudRolModel.setPaginaActual(paginadoModel.getPaginaActual());
        crudRolModel.setUsuario(user.getUsername());

        mav.addObject("crudRolModel", crudRolModel);
        mav.addObject("usuario", user.getUsername());

        return mav;
    }
}
