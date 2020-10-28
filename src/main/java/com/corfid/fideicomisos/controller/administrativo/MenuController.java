package com.corfid.fideicomisos.controller.administrativo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.cruds.CrudMenuModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.MenuInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/menu")
public class MenuController extends InitialController {

    @Autowired
    @Qualifier("menuServiceImpl")
    private MenuInterface menuInterface;

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    @RequestMapping("/showmenu")
    public ModelAndView showMenuGet() {
        ModelAndView mav = new ModelAndView(Constante.MENU);

        return mav;
    }

    @GetMapping("/lista_menus")
    public ModelAndView showListaMenus(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                       Model model) throws Exception {
        CrudMenuModel crudMenuModel = new CrudMenuModel();
        try {
            datosGenerales.setModulo(Constante.MODULO_MENU);
            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudMenuModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }

    @GetMapping("/addmenu")
    public String intercepcionForm() {
        return "redirect:" + Constante.URL_SELECCION;
    }

    @PostMapping(value = "/addmenu", params = { "cancelar" })
    public String cancelar(final CrudPersonaModel crudPersonaModel,
                           final BindingResult bindingResult,
                           final HttpServletRequest req) throws Exception {
        return "redirect:" + Constante.URL_LISTA_MENUS;
    }

    @PostMapping(value = "/addmenu", params = { "saveRow" })
    public ModelAndView registrarMenu(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      @ModelAttribute(name = "menuModel") MenuModel menuModel,
                                      Model model) throws Exception {
        try {
            Date fechaAndHoraActual = getFechaAndHoraActual();
            CrudMenuModel crudMenuModel = new CrudMenuModel();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            setParametrosAuditoriaModel(fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp(),
                                        fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp());

            menuModel.setIdUsuarioRegistro(datosGenerales.getIdUsuario());
            menuModel.setTipoUsuarioSesion(datosGenerales.getTipoUsuarioSession());

            menuModel = menuInterface.addMenu(menuModel, getParametrosAuditoriaModel());
            if (StringUtil.equiv(menuModel.getCodigoError(), ConstantesError.ERROR_0)) {
                crudMenuModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                crudMenuModel.setCodigoError(menuModel.getCodigoError());
            }

            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudMenuModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }

    @GetMapping("/crudAccionesListaMenus")
    public String intercepcion() {
        return "redirect:" + Constante.URL_SELECCION;
    }

    @PostMapping(value = "/crudAccionesListaMenus", params = { "findRow", "busqueda" })
    public ModelAndView buscarMenu(CrudMenuModel crudMenuModel,
                                   final BindingResult bindingResult,
                                   final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");

            return busqueda(caja,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudMenuModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }

    @PostMapping(value = "/crudAccionesListaMenus", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerecha(CrudMenuModel crudMenuModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");

            return busqueda(caja, Constante.CONST_CERO, Constante.DERECHA, paginaActual, paginaFinal, crudMenuModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }

    @PostMapping(value = "/crudAccionesListaMenus", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierda(CrudMenuModel crudMenuModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");

            return busqueda(caja, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual, paginaFinal, crudMenuModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }

    @PostMapping(value = "/crudAccionesListaMenus", params = { "addRow" })
    public ModelAndView addMenu(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                final CrudMenuModel crudMenuModel,
                                final BindingResult bindingResult) throws Exception {
        return preparingFormMenu(null, datosGenerales.getTipoUsuarioSession());
    }

    @PostMapping(value = "/crudAccionesListaMenus", params = { "editRow" })
    public ModelAndView editMenu(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                 final CrudMenuModel crudMenuModel,
                                 final BindingResult bindingResult,
                                 final HttpServletRequest req) throws Exception {

        return preparingFormMenu(req.getParameter("editRow"), datosGenerales.getTipoUsuarioSession());
    }

    @PostMapping(value = "/crudAccionesListaMenus", params = { "removeRow" })
    public ModelAndView removeMenu(final CrudMenuModel crudMenuModel,
                                   final BindingResult bindingResult,
                                   final HttpServletRequest req) throws Exception {
        try {
            if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
                if (menuInterface.removeMenu(StringUtil.toInteger(req.getParameter("removeRow")))) {
                    crudMenuModel.setCodigoError(ConstantesError.ERROR_0);
                } else {
                    crudMenuModel.setCodigoError(ConstantesError.ERROR_25);
                }
            } else {
                crudMenuModel.setCodigoError(ConstantesError.ERROR_26);
            }
            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudMenuModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }

    private ModelAndView preparingFormMenu(Object id, String tipoUsuarioSesion) throws Exception {
        try {
            ModelAndView model = new ModelAndView(Constante.FORM_MENU);
            List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
            List<CatalogoConstraintModel> listCatalogoConstraintModelTipoMenu;
            List<CatalogoConstraintModel> listCatalogoConstraintModelTipoUsuario;
            MenuModel menuModel = new MenuModel();
            List<MenuModel> listMenuModelPadre = new ArrayList<MenuModel>();

            //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!StringUtil.isEmpty(id)) {
                final Integer idMenu = StringUtil.toInteger(id);
                menuModel = menuInterface.findMenuByIdModel(idMenu);
            }

            listMenuModelPadre = menuInterface.obtenerMenusPadre(tipoUsuarioSesion);

            listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_MENU,
                                                                                                                    Constante.ESTADO_REGISTRO);
            listCatalogoConstraintModelTipoMenu = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_MENU,
                                                                                                              Constante.TIPO_MENU);
            listCatalogoConstraintModelTipoUsuario = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO,
                                                                                                                 Constante.TIPO_USUARIO);

            model.addObject("listMenuModelPadre", listMenuModelPadre);
            model.addObject("menuModel", menuModel);
            //model.addObject("usuario", user.getUsername());
            model.addObject("listEstado", listCatalogoConstraintModelEstadoRegistro);
            model.addObject("listTipoMenu", listCatalogoConstraintModelTipoMenu);
            model.addObject("listTipoUsuario", listCatalogoConstraintModelTipoUsuario);
            return model;
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }

    private ModelAndView busqueda(String busqueda,
                                  String izquierda,
                                  String derecha,
                                  String pagina,
                                  String fin,
                                  CrudMenuModel crudMenuModel) throws Exception {
        CrudMenuModel crudMenuModelLista = new CrudMenuModel();
        try {
            ModelAndView mav = new ModelAndView(Constante.LISTA_MENUS);
            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            crudMenuModelLista = menuInterface.listMenuByDescripcionPaginado(busqueda,
                                                                             paginadoModel.getPaginaActual(),
                                                                             Constante.PAGINADO_5_ROWS);

            if (paginadoModel.isMovIzquierda()) {
                crudMenuModelLista.setCodigoError(ConstantesError.ERROR_1);
                crudMenuModelLista.setMensaje(construirMensaje("Aviso",
                                                               "No hay más Registros a la Izquierda",
                                                               Constante.MENSAJE_INFORMATIVO));
            } else if (paginadoModel.isMovDerecha()) {
                crudMenuModelLista.setMensaje(construirMensaje("Aviso",
                                                               "No hay más Registros a la Derecha",
                                                               Constante.MENSAJE_INFORMATIVO));
                crudMenuModelLista.setCodigoError(ConstantesError.ERROR_1);
            } else {
                if (!StringUtil.isEmpty(crudMenuModel.getCodigoError())) {
                    if (StringUtil.equiv(crudMenuModel.getCodigoError(), ConstantesError.ERROR_0)) {
                        crudMenuModelLista.setMensaje(construirMensaje("Aviso",
                                                                       "La Operacion se Realizó Satisfactoriamente",
                                                                       Constante.MENSAJE_SATISFACTORIO));
                    } else if (StringUtil.equiv(crudMenuModel.getCodigoError(), ConstantesError.ERROR_1)) {
                        crudMenuModelLista.setMensaje(construirMensaje("Aviso",
                                                                       "Ocurrió un error en la operación",
                                                                       Constante.MENSAJE_ERROR));
                    } else {
                        crudMenuModelLista.setMensaje(construirMensaje("Aviso",
                                                                       crudMenuModel.getMensajeError(),
                                                                       Constante.MENSAJE_ERROR));
                    }
                }
            }

            crudMenuModelLista.setBusqueda(busqueda);
            crudMenuModelLista.setPaginaActual(paginadoModel.getPaginaActual());

            mav.addObject("crudMenuModel", crudMenuModelLista);

            return mav;
        } catch (Exception e) {
            crudMenuModelLista.setCodigoError(ConstantesError.ERROR_1);
            crudMenuModelLista.setMensaje(construirMensaje("Aviso",
                                                           "Ocurrió un problema al consultar registros",
                                                           Constante.MENSAJE_ERROR));
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }
}
