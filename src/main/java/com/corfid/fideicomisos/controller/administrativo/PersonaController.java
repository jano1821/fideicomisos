package com.corfid.fideicomisos.controller.administrativo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.administrativo.DireccionModel;
import com.corfid.fideicomisos.model.administrativo.EmailModel;
import com.corfid.fideicomisos.model.administrativo.EmpresaModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.administrativo.TelefonoModel;
import com.corfid.fideicomisos.model.cruds.CrudDireccionModel;
import com.corfid.fideicomisos.model.cruds.CrudEmailModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.cruds.CrudTelefonoModel;
import com.corfid.fideicomisos.model.utilities.AjaxResponseBody;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.GenericModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.DireccionInterface;
import com.corfid.fideicomisos.service.administrativo.EmailInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.TelefonoInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/persona")
public class PersonaController extends InitialController {

    @Autowired
    @Qualifier("personaServiceImpl")
    private PersonaInterface personaInterface;

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    @Autowired
    @Qualifier("direccionServiceImpl")
    private DireccionInterface direccionInterface;

    @Autowired
    @Qualifier("emailServiceImpl")
    private EmailInterface emailInterface;

    @Autowired
    @Qualifier("telefonoServiceImpl")
    private TelefonoInterface telefonoInterface;

    @Autowired
    private Environment environment;

    public String obtenerIp() {
        return environment.getProperty("local.server.port");
    }

    @PostMapping("/cancel")
    public String cancel() {

        return "redirect:" + Constante.URL_LISTA_PERSONAS;
    }

    @GetMapping("/lista_personas")
    public ModelAndView showListaUsuario(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                         Model model) {
        CrudPersonaModel crudPersonaModel = new CrudPersonaModel();
        try {
            crudPersonaModel.setCodigoError(ConstantesError.ERROR_0);
            crudPersonaModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudPersonaModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
            datosGenerales.setModulo(Constante.MODULO_PERSONA);

            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_VACIA,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudPersonaModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "findRow", "busqueda", "busquedaTipoPersona" })
    public ModelAndView buscarPersona(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      CrudPersonaModel crudPersonaModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {
        try {
            String caja = req.getParameter("busqueda");
            String busquedaTipoPersona = req.getParameter("busquedaTipoPersona");
            crudPersonaModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudPersonaModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busqueda(caja,
                            busquedaTipoPersona,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudPersonaModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "rightRow", "busqueda", "busquedaTipoPersona", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerecha(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      CrudPersonaModel crudPersonaModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {
        try {
            String caja = req.getParameter("busqueda");
            String busquedaTipoPersona = req.getParameter("busquedaTipoPersona");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            crudPersonaModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudPersonaModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busqueda(caja,
                            busquedaTipoPersona,
                            Constante.CONST_CERO,
                            Constante.DERECHA,
                            paginaActual,
                            paginaFinal,
                            crudPersonaModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "leftRow", "busqueda", "busquedaTipoPersona", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                        CrudPersonaModel crudPersonaModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) {
        try {
            String caja = req.getParameter("busqueda");
            String busquedaTipoPersona = req.getParameter("busquedaTipoPersona");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            crudPersonaModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudPersonaModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busqueda(caja,
                            busquedaTipoPersona,
                            Constante.IZQUIERDA,
                            Constante.CONST_CERO,
                            paginaActual,
                            paginaFinal,
                            crudPersonaModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/addpersona", params = { "cancelar" })
    public String cancelar(final CrudPersonaModel crudPersonaModel,
                           final BindingResult bindingResult,
                           final HttpServletRequest req) throws Exception {
        return "redirect:" + Constante.URL_LISTA_PERSONAS;
    }

    @PostMapping(value = "/addpersona", params = { "saveRow" })
    public ModelAndView registrarPersona(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                         PersonaModel personaModel,
                                         Model model,
                                         final BindingResult bindingResult,
                                         final HttpServletRequest req) {

        CrudPersonaModel crudPersonaModel = new CrudPersonaModel();
        try {

            Date fechaAndHoraActual = getFechaAndHoraActual();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            setParametrosAuditoriaModel(fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp(),
                                        fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp());

            switch (personaModel.getDescTipoPersona()) {
                case "todos":
                    personaModel.setTipoPersona("%");
                    break;
                case "persona natural":
                    personaModel.setTipoPersona("N");
                    break;
                case "empresa":
                    personaModel.setTipoPersona("J");
                    break;
                default:
                    break;
            }

            personaModel.setIdUsuarioRegistro(datosGenerales.getIdUsuario());

            personaModel = personaInterface.addPersona(personaModel, getParametrosAuditoriaModel());

            if (!StringUtil.isEmpty(personaModel) && StringUtil.equiv(personaModel.getCodigoError(),
                                                                      ConstantesError.ERROR_0)) {
                crudPersonaModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                crudPersonaModel.setCodigoError(personaModel.getCodigoError());
                crudPersonaModel.setMensajeError(personaModel.getDescripcionError());
            }

            crudPersonaModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudPersonaModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_VACIA,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudPersonaModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_PERSONAS);
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "addRow" })
    public ModelAndView addPersona(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                   final CrudPersonaModel crudPersonaModel,
                                   final BindingResult bindingResult) throws Exception {
        try {
            return preparingFormUsuario(null, datosGenerales.getTipoUsuarioSession(), datosGenerales.getIdEmpresa());
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_PERSONAS);
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "editRow" })
    public ModelAndView editPersona(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                    final CrudPersonaModel crudPersonaModel,
                                    final BindingResult bindingResult,
                                    final HttpServletRequest req) throws Exception {
        try {
            return preparingFormUsuario(req.getParameter("editRow"),
                                        datosGenerales.getTipoUsuarioSession(),
                                        datosGenerales.getIdEmpresa());
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_PERSONAS);
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "removeRow" })
    public ModelAndView removePersona(final CrudPersonaModel crudPersonaModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
                if (personaInterface.removePersona(StringUtil.toInteger(req.getParameter("removeRow")))) {
                    crudPersonaModel.setCodigoError(ConstantesError.ERROR_0);
                } else {
                    crudPersonaModel.setCodigoError(ConstantesError.ERROR_1);
                }
            } else {
                crudPersonaModel.setCodigoError(ConstantesError.ERROR_1);
            }

            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_VACIA,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudPersonaModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    /* Inicio Bloque Direcciones */
    @PostMapping(value = "/crudAccionesListaPersonas", params = { "direccionRow" })
    public ModelAndView direccionPersona(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                         final CrudDireccionModel crudDireccionModel,
                                         final BindingResult bindingResult,
                                         final HttpServletRequest req) throws Exception {
        try {
            datosGenerales.setModulo(Constante.MODULO_DIRECCION);
            return busquedaDirecciones(StringUtil.toInteger(req.getParameter("direccionRow")),
                                       Constante.CONST_CERO,
                                       Constante.CONST_CERO,
                                       Constante.PAGINA_INICIAL,
                                       Constante.CONST_CERO,
                                       crudDireccionModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaDirecciones", params = { "leftRow", "idPersona", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierdaDireccion(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                                 CrudDireccionModel crudDireccionModel,
                                                 final BindingResult bindingResult,
                                                 final HttpServletRequest req) {
        try {
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            crudDireccionModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudDireccionModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busquedaDirecciones(StringUtil.toInteger(req.getParameter("idPersona")),
                                       Constante.IZQUIERDA,
                                       Constante.CONST_CERO,
                                       paginaActual,
                                       paginaFinal,
                                       crudDireccionModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaDirecciones", params = { "rightRow", "idPersona", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerechaDireccion(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                               CrudDireccionModel crudDireccionModel,
                                               final BindingResult bindingResult,
                                               final HttpServletRequest req) {
        try {
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            crudDireccionModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudDireccionModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busquedaDirecciones(StringUtil.toInteger(req.getParameter("idPersona")),
                                       Constante.CONST_CERO,
                                       Constante.DERECHA,
                                       paginaActual,
                                       paginaFinal,
                                       crudDireccionModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/adddireccion", params = { "cancelarDireccion" })
    public ModelAndView cancelarDireccion(final DireccionModel direccionModel,
                                          final BindingResult bindingResult,
                                          final HttpServletRequest req) throws Exception {
        try {
            CrudDireccionModel crudDireccionModel = new CrudDireccionModel();
            return busquedaDirecciones(direccionModel.getIdPersona(),
                                       Constante.CONST_CERO,
                                       Constante.CONST_CERO,
                                       Constante.PAGINA_INICIAL,
                                       Constante.CONST_CERO,
                                       crudDireccionModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_DIRECCIONES);
        }
    }

    @PostMapping(value = "/crudAccionesListaDirecciones", params = { "editRowDireccion" })
    public ModelAndView editDireccion(final CrudDireccionModel crudDireccionModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            return preparingFormDireccion(StringUtil.toInteger(StringUtil.toStr(req.getParameter("editRowDireccion"))),
                                          crudDireccionModel.getIdPersona());
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_DIRECCIONES);
        }
    }

    @PostMapping(value = "/adddireccion", params = { "saveDireccion" })
    public ModelAndView saveDireccion(final DireccionModel direccionModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            return registrarDireccion(direccionModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_DIRECCIONES);
        }
    }

    @PostMapping(value = "/crudAccionesListaDirecciones", params = { "removeRowDireccion" })
    public ModelAndView removeDireccion(CrudDireccionModel crudDireccionModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) throws Exception {
        try {
            DireccionModel direccionModel;
            direccionModel = direccionInterface.findDireccionById(StringUtil.toInteger(StringUtil.toStr(req.getParameter("removeRowDireccion"))));
            direccionModel.setEstadoRegistro(Constante.ESTADO_REGISTRO_NO_VIGENTE);
            return registrarDireccion(direccionModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_DIRECCIONES);
        }
    }

    public ModelAndView registrarDireccion(DireccionModel direccionModel) {
        CrudDireccionModel crudDireccionModel = new CrudDireccionModel();
        try {

            Date fechaAndHoraActual = getFechaAndHoraActual();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            setParametrosAuditoriaModel(fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp(),
                                        fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp());

            direccionModel = direccionInterface.addDireccion(direccionModel, getParametrosAuditoriaModel());

            if (!StringUtil.isEmpty(direccionModel) && StringUtil.equiv(direccionModel.getCodigoError(),
                                                                        ConstantesError.ERROR_0)) {
                crudDireccionModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                crudDireccionModel.setCodigoError(direccionModel.getCodigoError());
                crudDireccionModel.setMensajeError(direccionModel.getDescripcionError());
            }

            return busquedaDirecciones(direccionModel.getIdPersona(),
                                       Constante.CONST_CERO,
                                       Constante.CONST_CERO,
                                       Constante.PAGINA_INICIAL,
                                       Constante.CONST_CERO,
                                       crudDireccionModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_DIRECCIONES);
        }
    }

    @PostMapping(value = "/crudAccionesListaDirecciones", params = { "addRowDireccion" })
    public ModelAndView addDireccion(final CrudDireccionModel crudDireccionModel,
                                     final BindingResult bindingResult,
                                     final HttpServletRequest req) throws Exception {
        try {
            return preparingFormDireccion(null, crudDireccionModel.getIdPersona());
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_PERSONAS);
        }
    }

    @PostMapping(value = "/crudAccionesListaDirecciones", params = { "volverDireccion" })
    public String volverDireccion(final CrudDireccionModel crudDireccionModel,
                                  final BindingResult bindingResult) throws Exception {
        return "redirect:" + Constante.URL_LISTA_PERSONAS;
    }

    private ModelAndView preparingFormDireccion(Integer idDireccion, Integer idPersona) throws Exception {
        ModelAndView model = new ModelAndView(Constante.FORM_DIRECCION);
        try {
            List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;

            DireccionModel direccionModel = new DireccionModel();
            if (!StringUtil.isEmpty(idDireccion)) {
                direccionModel = direccionInterface.findDireccionById(idDireccion);
                direccionModel.setEdicion(Constante.MODO_EDICION);
            } else {
                direccionModel.setEdicion(Constante.MODO_NUEVO);
            }
            direccionModel.setIdPersona(idPersona);
            listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA,
                                                                                                                    Constante.ESTADO_REGISTRO);

            model.addObject("direccionModel", direccionModel);
            model.addObject("listEstadoRegistro", listCatalogoConstraintModelEstadoRegistro);
            return model;
        } catch (Exception e) {
            return model;
        }
    }

    private ModelAndView busquedaDirecciones(Integer idPersona,
                                             String izquierda,
                                             String derecha,
                                             String pagina,
                                             String fin,
                                             CrudDireccionModel crudDireccionModel) throws Exception {

        ModelAndView mav = new ModelAndView(Constante.LISTA_DIRECCIONES);
        CrudDireccionModel crudDireccionModelPaginado;
        try {
            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            crudDireccionModelPaginado = direccionInterface.findDireccionByIdPersona(idPersona,
                                                                                     paginadoModel.getPaginaActual(),
                                                                                     Constante.PAGINADO_5_ROWS);

            if (paginadoModel.isMovIzquierda()) {
                crudDireccionModelPaginado.setCodigoError(ConstantesError.ERROR_1);
                crudDireccionModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                       "No hay más Registros a la Izquierda",
                                                                       Constante.MENSAJE_INFORMATIVO));
            } else if (paginadoModel.isMovDerecha()) {
                crudDireccionModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                       "No hay más Registros a la Derecha",
                                                                       Constante.MENSAJE_INFORMATIVO));
                crudDireccionModelPaginado.setCodigoError(ConstantesError.ERROR_1);
            } else {
                if (!StringUtil.isEmpty(crudDireccionModel.getCodigoError())) {
                    if (StringUtil.equiv(crudDireccionModel.getCodigoError(), ConstantesError.ERROR_0)) {
                        crudDireccionModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                               "La Operacion se Realizó Satisfactoriamente",
                                                                               Constante.MENSAJE_SATISFACTORIO));
                    } else if (StringUtil.equiv(crudDireccionModel.getCodigoError(), ConstantesError.ERROR_1)) {
                        crudDireccionModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                               "Ocurrió un error en la operación",
                                                                               Constante.MENSAJE_ERROR));
                    } else {
                        crudDireccionModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                               crudDireccionModel.getMensajeError(),
                                                                               Constante.MENSAJE_ERROR));
                    }
                }
                crudDireccionModelPaginado.setCodigoError(crudDireccionModel.getCodigoError());
            }

            crudDireccionModelPaginado.setPaginaActual(paginadoModel.getPaginaActual());
            //crudDireccionModelPaginado.setUsuario(user.getUsername());
            crudDireccionModelPaginado.setIdPersona(idPersona);

            mav.addObject("crudDireccionModel", crudDireccionModelPaginado);
            //mav.addObject("usuario", user.getUsername());

            return mav;
        } catch (Exception e) {
            return new ModelAndView();
        }
    }
    /* Fin Bloque direcciones */

    /* Inicio Bloque Email */
    @PostMapping(value = "/crudAccionesListaPersonas", params = { "emailRow" })
    public ModelAndView emailPersona(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                     final CrudEmailModel crudEmailModel,
                                     final BindingResult bindingResult,
                                     final HttpServletRequest req) throws Exception {
        try {
            datosGenerales.setModulo(Constante.MODULO_EMAIL);
            return busquedaEmail(StringUtil.toInteger(req.getParameter("emailRow")),
                                 Constante.CONST_CERO,
                                 Constante.CONST_CERO,
                                 Constante.PAGINA_INICIAL,
                                 Constante.CONST_CERO,
                                 crudEmailModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaEmail", params = { "leftRow", "idPersona", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierdaEmail(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                             CrudEmailModel crudEmailModel,
                                             final BindingResult bindingResult,
                                             final HttpServletRequest req) {
        try {
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            crudEmailModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudEmailModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busquedaEmail(StringUtil.toInteger(req.getParameter("idPersona")),
                                 Constante.IZQUIERDA,
                                 Constante.CONST_CERO,
                                 paginaActual,
                                 paginaFinal,
                                 crudEmailModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaEmail", params = { "rightRow", "idPersona", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerechaEmail(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                           CrudEmailModel crudEmailModel,
                                           final BindingResult bindingResult,
                                           final HttpServletRequest req) {
        try {
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            crudEmailModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudEmailModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busquedaEmail(StringUtil.toInteger(req.getParameter("idPersona")),
                                 Constante.CONST_CERO,
                                 Constante.DERECHA,
                                 paginaActual,
                                 paginaFinal,
                                 crudEmailModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/addemail", params = { "cancelarEmail" })
    public ModelAndView cancelarEmail(final EmailModel emailModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            CrudEmailModel crudEmailModel = new CrudEmailModel();
            return busquedaEmail(emailModel.getIdPersona(),
                                 Constante.CONST_CERO,
                                 Constante.CONST_CERO,
                                 Constante.PAGINA_INICIAL,
                                 Constante.CONST_CERO,
                                 crudEmailModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EMAIL);
        }
    }

    @PostMapping(value = "/crudAccionesListaEmail", params = { "editRowEmail" })
    public ModelAndView editEmail(final CrudEmailModel crudEmailModel,
                                  final BindingResult bindingResult,
                                  final HttpServletRequest req) throws Exception {
        try {
            return preparingFormEmail(StringUtil.toInteger(StringUtil.toStr(req.getParameter("editRowEmail"))),
                                      crudEmailModel.getIdPersona());
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EMAIL);
        }
    }

    @PostMapping(value = "/addemail", params = { "saveRowEmail" })
    public ModelAndView saveEmail(final EmailModel emailModel,
                                  final BindingResult bindingResult,
                                  final HttpServletRequest req) throws Exception {
        try {
            return registrarEmail(emailModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EMAIL);
        }
    }

    @PostMapping(value = "/crudAccionesListaEmail", params = { "removeRowEmail" })
    public ModelAndView removeEmail(CrudEmailModel crudEmailModel,
                                    final BindingResult bindingResult,
                                    final HttpServletRequest req) throws Exception {
        try {
            EmailModel emailModel;
            emailModel = emailInterface.findEmailById(StringUtil.toInteger(StringUtil.toStr(req.getParameter("removeRowEmail"))));
            emailModel.setEstadoRegistro(Constante.ESTADO_REGISTRO_NO_VIGENTE);
            return registrarEmail(emailModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EMAIL);
        }
    }

    public ModelAndView registrarEmail(EmailModel emailModel) {
        CrudEmailModel crudEmailModel = new CrudEmailModel();
        try {

            Date fechaAndHoraActual = getFechaAndHoraActual();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            setParametrosAuditoriaModel(fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp(),
                                        fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp());

            emailModel = emailInterface.addEmail(emailModel, getParametrosAuditoriaModel());

            if (!StringUtil.isEmpty(emailModel) && StringUtil.equiv(emailModel.getCodigoError(),
                                                                    ConstantesError.ERROR_0)) {
                crudEmailModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                crudEmailModel.setCodigoError(emailModel.getCodigoError());
                crudEmailModel.setMensajeError(emailModel.getDescripcionError());
            }

            return busquedaEmail(emailModel.getIdPersona(),
                                 Constante.CONST_CERO,
                                 Constante.CONST_CERO,
                                 Constante.PAGINA_INICIAL,
                                 Constante.CONST_CERO,
                                 crudEmailModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EMAIL);
        }
    }

    @PostMapping(value = "/crudAccionesListaEmail", params = { "addRowEmail" })
    public ModelAndView addEmail(final CrudEmailModel crudEmailModel,
                                 final BindingResult bindingResult,
                                 final HttpServletRequest req) throws Exception {
        try {
            return preparingFormEmail(null, crudEmailModel.getIdPersona());
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_PERSONAS);
        }
    }

    @PostMapping(value = "/crudAccionesListaEmail", params = { "volverEmail" })
    public String volverEmail(final CrudEmailModel crudEmailModel, final BindingResult bindingResult) throws Exception {
        return "redirect:" + Constante.URL_LISTA_PERSONAS;
    }

    private ModelAndView preparingFormEmail(Integer idEmail, Integer idPersona) throws Exception {
        ModelAndView model = new ModelAndView(Constante.FORM_EMAIL);
        try {
            List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;

            EmailModel emailModel = new EmailModel();
            if (!StringUtil.isEmpty(idEmail)) {
                emailModel = emailInterface.findEmailById(idEmail);
                emailModel.setEdicion(Constante.MODO_EDICION);
            } else {
                emailModel.setEdicion(Constante.MODO_NUEVO);
            }
            emailModel.setIdPersona(idPersona);
            listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA,
                                                                                                                    Constante.ESTADO_REGISTRO);

            model.addObject("emailModel", emailModel);
            model.addObject("listEstadoRegistro", listCatalogoConstraintModelEstadoRegistro);
            return model;
        } catch (Exception e) {
            return model;
        }
    }

    private ModelAndView busquedaEmail(Integer idPersona,
                                       String izquierda,
                                       String derecha,
                                       String pagina,
                                       String fin,
                                       CrudEmailModel crudEmailModel) throws Exception {

        ModelAndView mav = new ModelAndView(Constante.LISTA_EMAIL);
        CrudEmailModel crudEmailModelPaginado;
        try {
            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            crudEmailModelPaginado = emailInterface.findEmailByIdPersona(idPersona,
                                                                         paginadoModel.getPaginaActual(),
                                                                         Constante.PAGINADO_5_ROWS);

            if (paginadoModel.isMovIzquierda()) {
                crudEmailModelPaginado.setCodigoError(ConstantesError.ERROR_1);
                crudEmailModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                   "No hay más Registros a la Izquierda",
                                                                   Constante.MENSAJE_INFORMATIVO));
            } else if (paginadoModel.isMovDerecha()) {
                crudEmailModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                   "No hay más Registros a la Derecha",
                                                                   Constante.MENSAJE_INFORMATIVO));
                crudEmailModelPaginado.setCodigoError(ConstantesError.ERROR_1);
            } else {
                if (!StringUtil.isEmpty(crudEmailModel.getCodigoError())) {
                    if (StringUtil.equiv(crudEmailModel.getCodigoError(), ConstantesError.ERROR_0)) {
                        crudEmailModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                           "La Operacion se Realizó Satisfactoriamente",
                                                                           Constante.MENSAJE_SATISFACTORIO));
                    } else if (StringUtil.equiv(crudEmailModel.getCodigoError(), ConstantesError.ERROR_1)) {
                        crudEmailModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                           "Ocurrió un error en la operación",
                                                                           Constante.MENSAJE_ERROR));
                    } else {
                        crudEmailModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                           crudEmailModel.getMensajeError(),
                                                                           Constante.MENSAJE_ERROR));
                    }
                }
                crudEmailModelPaginado.setCodigoError(crudEmailModel.getCodigoError());
            }

            crudEmailModelPaginado.setPaginaActual(paginadoModel.getPaginaActual());
            //crudEmailModelPaginado.setUsuario(user.getUsername());
            crudEmailModelPaginado.setIdPersona(idPersona);

            mav.addObject("crudEmailModel", crudEmailModelPaginado);
            //mav.addObject("usuario", user.getUsername());

            return mav;
        } catch (Exception e) {
            return new ModelAndView();
        }
    }
    /* Fin Bloque email */

    /* Inicio Bloque Telefono */
    @PostMapping(value = "/crudAccionesListaPersonas", params = { "telefonoRow" })
    public ModelAndView telefonoPersona(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                        final CrudTelefonoModel crudTelefonoModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) throws Exception {
        try {
            datosGenerales.setModulo(Constante.MODULO_TELEFONO);
            return busquedaTelefono(StringUtil.toInteger(req.getParameter("telefonoRow")),
                                    Constante.CONST_CERO,
                                    Constante.CONST_CERO,
                                    Constante.PAGINA_INICIAL,
                                    Constante.CONST_CERO,
                                    crudTelefonoModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaTelefonos", params = { "leftRow", "idPersona", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierdaTelefono(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                                CrudTelefonoModel crudTelefonoModel,
                                                final BindingResult bindingResult,
                                                final HttpServletRequest req) {
        try {
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            crudTelefonoModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudTelefonoModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busquedaTelefono(StringUtil.toInteger(req.getParameter("idPersona")),
                                    Constante.IZQUIERDA,
                                    Constante.CONST_CERO,
                                    paginaActual,
                                    paginaFinal,
                                    crudTelefonoModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaTelefonos", params = { "rightRow", "idPersona", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerechaTelefono(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                              CrudTelefonoModel crudTelefonoModel,
                                              final BindingResult bindingResult,
                                              final HttpServletRequest req) {
        try {
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            crudTelefonoModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudTelefonoModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

            return busquedaTelefono(StringUtil.toInteger(req.getParameter("idPersona")),
                                    Constante.CONST_CERO,
                                    Constante.DERECHA,
                                    paginaActual,
                                    paginaFinal,
                                    crudTelefonoModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/addtelefono", params = { "cancelarTelefono" })
    public ModelAndView cancelarEmail(final TelefonoModel telefonoModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            CrudTelefonoModel crudTelefonoModel = new CrudTelefonoModel();
            return busquedaTelefono(telefonoModel.getIdPersona(),
                                    Constante.CONST_CERO,
                                    Constante.CONST_CERO,
                                    Constante.PAGINA_INICIAL,
                                    Constante.CONST_CERO,
                                    crudTelefonoModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_TELEFONO);
        }
    }

    @PostMapping(value = "/crudAccionesListaTelefonos", params = { "editRowTelefono" })
    public ModelAndView editTelefono(final CrudTelefonoModel crudTelefonoModel,
                                     final BindingResult bindingResult,
                                     final HttpServletRequest req) throws Exception {
        try {
            return preparingFormTelefono(StringUtil.toInteger(StringUtil.toStr(req.getParameter("editRowTelefono"))),
                                         crudTelefonoModel.getIdPersona());
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_TELEFONO);
        }
    }

    @PostMapping(value = "/addtelefono", params = { "saveRowTelefono" })
    public ModelAndView saveEmail(final TelefonoModel telefonoModel,
                                  final BindingResult bindingResult,
                                  final HttpServletRequest req) throws Exception {
        try {
            return registrarTelefono(telefonoModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_TELEFONO);
        }
    }

    @PostMapping(value = "/crudAccionesListaTelefonos", params = { "removeRowTelefono" })
    public ModelAndView removeEmail(CrudTelefonoModel crudTelefonoModel,
                                    final BindingResult bindingResult,
                                    final HttpServletRequest req) throws Exception {
        try {
            TelefonoModel telefonoModel;
            telefonoModel = telefonoInterface.findTelefonoById(StringUtil.toInteger(StringUtil.toStr(req.getParameter("removeRowTelefono"))));
            telefonoModel.setEstadoRegistro(Constante.ESTADO_REGISTRO_NO_VIGENTE);
            return registrarTelefono(telefonoModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_TELEFONO);
        }
    }

    public ModelAndView registrarTelefono(TelefonoModel telefonoModel) {
        CrudTelefonoModel crudTelefonoModel = new CrudTelefonoModel();
        try {

            Date fechaAndHoraActual = getFechaAndHoraActual();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            setParametrosAuditoriaModel(fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp(),
                                        fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp());

            telefonoModel = telefonoInterface.addTelefono(telefonoModel, getParametrosAuditoriaModel());

            if (!StringUtil.isEmpty(telefonoModel) && StringUtil.equiv(telefonoModel.getCodigoError(),
                                                                       ConstantesError.ERROR_0)) {
                crudTelefonoModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                crudTelefonoModel.setCodigoError(telefonoModel.getCodigoError());
                crudTelefonoModel.setMensajeError(telefonoModel.getDescripcionError());
            }

            return busquedaTelefono(telefonoModel.getIdPersona(),
                                    Constante.CONST_CERO,
                                    Constante.CONST_CERO,
                                    Constante.PAGINA_INICIAL,
                                    Constante.CONST_CERO,
                                    crudTelefonoModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_TELEFONO);
        }
    }

    @PostMapping(value = "/crudAccionesListaTelefonos", params = { "addRowTelefono" })
    public ModelAndView addTelefono(final CrudTelefonoModel crudTelefonoModel,
                                    final BindingResult bindingResult,
                                    final HttpServletRequest req) throws Exception {
        try {
            return preparingFormTelefono(null, crudTelefonoModel.getIdPersona());
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_PERSONAS);
        }
    }

    @PostMapping(value = "/crudAccionesListaTelefonos", params = { "volverTelefono" })
    public String volverTelefono(final CrudTelefonoModel crudTelefonoModel,
                                 final BindingResult bindingResult) throws Exception {
        return "redirect:" + Constante.URL_LISTA_PERSONAS;
    }

    private ModelAndView preparingFormTelefono(Integer idTelefono, Integer idPersona) throws Exception {
        ModelAndView model = new ModelAndView(Constante.FORM_TELEFONO);
        try {
            List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
            List<CatalogoConstraintModel> listCatalogoConstraintModelOperador;

            TelefonoModel telefonoModel = new TelefonoModel();
            if (!StringUtil.isEmpty(idTelefono)) {
                telefonoModel = telefonoInterface.findTelefonoById(idTelefono);
                telefonoModel.setEdicion(Constante.MODO_EDICION);
            } else {
                telefonoModel.setEdicion(Constante.MODO_NUEVO);
            }
            telefonoModel.setIdPersona(idPersona);
            listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA,
                                                                                                                    Constante.ESTADO_REGISTRO);

            listCatalogoConstraintModelOperador = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_TELEFONO,
                                                                                                              Constante.OPERADOR);

            model.addObject("telefonoModel", telefonoModel);
            model.addObject("listEstadoRegistro", listCatalogoConstraintModelEstadoRegistro);
            model.addObject("listOperador", listCatalogoConstraintModelOperador);
            return model;
        } catch (Exception e) {
            return model;
        }
    }

    private ModelAndView busquedaTelefono(Integer idPersona,
                                          String izquierda,
                                          String derecha,
                                          String pagina,
                                          String fin,
                                          CrudTelefonoModel crudTelefonoModel) throws Exception {

        ModelAndView mav = new ModelAndView(Constante.LISTA_TELEFONO);
        CrudTelefonoModel crudTelefonoModelPaginado;
        try {
            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            crudTelefonoModelPaginado = telefonoInterface.findTelefonoByIdPersona(idPersona,
                                                                                  paginadoModel.getPaginaActual(),
                                                                                  Constante.PAGINADO_5_ROWS);

            if (paginadoModel.isMovIzquierda()) {
                crudTelefonoModelPaginado.setCodigoError(ConstantesError.ERROR_1);
                crudTelefonoModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                      "No hay más Registros a la Izquierda",
                                                                      Constante.MENSAJE_INFORMATIVO));
            } else if (paginadoModel.isMovDerecha()) {
                crudTelefonoModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                      "No hay más Registros a la Derecha",
                                                                      Constante.MENSAJE_INFORMATIVO));
                crudTelefonoModelPaginado.setCodigoError(ConstantesError.ERROR_1);
            } else {
                if (!StringUtil.isEmpty(crudTelefonoModel.getCodigoError())) {
                    if (StringUtil.equiv(crudTelefonoModel.getCodigoError(), ConstantesError.ERROR_0)) {
                        crudTelefonoModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                              "La Operacion se Realizó Satisfactoriamente",
                                                                              Constante.MENSAJE_SATISFACTORIO));
                    } else if (StringUtil.equiv(crudTelefonoModel.getCodigoError(), ConstantesError.ERROR_1)) {
                        crudTelefonoModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                              "Ocurrió un error en la operación",
                                                                              Constante.MENSAJE_ERROR));
                    } else {
                        crudTelefonoModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                              crudTelefonoModel.getMensajeError(),
                                                                              Constante.MENSAJE_ERROR));
                    }
                }
                crudTelefonoModelPaginado.setCodigoError(crudTelefonoModel.getCodigoError());
            }

            crudTelefonoModelPaginado.setPaginaActual(paginadoModel.getPaginaActual());
            crudTelefonoModelPaginado.setIdPersona(idPersona);

            mav.addObject("crudTelefonoModel", crudTelefonoModelPaginado);
            //mav.addObject("usuario", user.getUsername());

            return mav;
        } catch (Exception e) {
            return new ModelAndView();
        }
    }
    /* Fin Bloque telefono */

    private ModelAndView preparingFormUsuario(Object id,
                                              String tipoUsuarioSesion,
                                              Integer idEmpresaSesion) throws Exception {
        ModelAndView model = new ModelAndView(Constante.FORM_PERSONA);
        try {
            List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
            List<CatalogoConstraintModel> listCatalogoConstraintModelTipoDocumento;
            List<GenericModel> listGenericModelVinculados = new ArrayList<GenericModel>();
            List<GenericModel> listGenericModel = null;
            List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();

            //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            PersonaModel personaModel = new PersonaModel();
            if (!StringUtil.isEmpty(id)) {
                final Integer idPersona = StringUtil.toInteger(id);
                personaModel = personaInterface.findPersonaByIdModel(idPersona);
                personaModel.setEdicion(Constante.MODO_EDICION);

                if (!StringUtil.isEmpty(personaModel.getListEmpresa())) {
                    listGenericModelVinculados = new ArrayList<GenericModel>();

                    for (EmpresaModel empresaModel : personaModel.getListEmpresa()) {
                        GenericModel genericModel = new GenericModel();
                        genericModel.setId(StringUtil.toStr(empresaModel.getIdPersona()));
                        genericModel.setDescripcion(empresaModel.getRazonSocial());
                        if (StringUtil.equiv(tipoUsuarioSesion, Constante.TIPO_USUARIO_SUPER_ADMIN)) {
                            listGenericModelVinculados.add(genericModel);
                        } else {
                            if (StringUtil.equiv(empresaModel.getIdEmpresa(), idEmpresaSesion)) {
                                listGenericModelVinculados.add(genericModel);
                            }
                        }
                    }
                }
            } else {
                personaModel.setEdicion(Constante.MODO_NUEVO);
            }
            personaModel.setTipoUsuarioSesion(tipoUsuarioSesion);
            personaModel.setIdEmpresaSesion(idEmpresaSesion);

            // listar todas las empresas
            listPersonaModel = personaInterface.obtenerAllEmpresas(tipoUsuarioSesion, idEmpresaSesion);

            if (!StringUtil.isEmpty(listPersonaModel)) {
                listGenericModel = new ArrayList<GenericModel>();

                for (PersonaModel personaModelTemp : listPersonaModel) {
                    GenericModel genericModel = new GenericModel();
                    genericModel.setId(StringUtil.toStr(personaModelTemp.getIdPersona()));
                    genericModel.setDescripcion(personaModelTemp.getRazonSocial());
                    listGenericModel.add(genericModel);
                }
            }

            String combo = construirComboSearch(Constante.SELECCION_MULTIPLE,
                                                listGenericModel,
                                                "empresa",
                                                "Seleccione Empresas..",
                                                listGenericModelVinculados);

            listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA,
                                                                                                                    Constante.ESTADO_REGISTRO);
            listCatalogoConstraintModelTipoDocumento = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA,
                                                                                                                   Constante.TIPO_DOC);

            model.addObject("personaModel", personaModel);
            //model.addObject("usuario", user.getUsername());
            model.addObject("comboEmpresas", combo);
            model.addObject("listEstadoRegistro", listCatalogoConstraintModelEstadoRegistro);
            model.addObject("listTipoDocumento", listCatalogoConstraintModelTipoDocumento);
            return model;
        } catch (Exception e) {
            return model;
        }
    }

    private ModelAndView busqueda(String busqueda,
                                  String busquedaTipoPersona,
                                  String izquierda,
                                  String derecha,
                                  String pagina,
                                  String fin,
                                  CrudPersonaModel crudPersonaModel) throws Exception {
        ModelAndView mav = new ModelAndView(Constante.LISTA_PERSONAS);
        CrudPersonaModel crudPersonaModelPaginado;
        try {
            String personeria = "";
            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            if (StringUtil.isEmpty(busquedaTipoPersona)) {
                busquedaTipoPersona = "Todos";
            }

            switch (busquedaTipoPersona) {
                case "todos":
                    personeria = "%";
                    break;
                case "persona natural":
                    personeria = "N";
                    break;
                case "empresa":
                    personeria = "J";
                    break;
                default:
                    break;
            }
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            crudPersonaModelPaginado = personaInterface.listPersonaByNombrePaginado(busqueda,
                                                                                    personeria,
                                                                                    user.getUsername(),
                                                                                    crudPersonaModel.getTipoUsuarioSession(),
                                                                                    crudPersonaModel.getIdEmpresaSession(),
                                                                                    paginadoModel.getPaginaActual(),
                                                                                    Constante.PAGINADO_5_ROWS);
            crudPersonaModelPaginado.setBusqueda(busqueda);
            crudPersonaModelPaginado.setBusquedaTipoPersona(busquedaTipoPersona);

            if (paginadoModel.isMovIzquierda()) {
                crudPersonaModelPaginado.setCodigoError(ConstantesError.ERROR_1);
                crudPersonaModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                     "No hay más Registros a la Izquierda",
                                                                     Constante.MENSAJE_INFORMATIVO));
            } else if (paginadoModel.isMovDerecha()) {
                crudPersonaModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                     "No hay más Registros a la Derecha",
                                                                     Constante.MENSAJE_INFORMATIVO));
                crudPersonaModelPaginado.setCodigoError(ConstantesError.ERROR_1);
            } else {
                if (!StringUtil.isEmpty(crudPersonaModel.getCodigoError())) {
                    if (StringUtil.equiv(crudPersonaModel.getCodigoError(), ConstantesError.ERROR_0)) {
                        crudPersonaModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                             "La Operacion se Realizó Satisfactoriamente",
                                                                             Constante.MENSAJE_SATISFACTORIO));
                    } else if (StringUtil.equiv(crudPersonaModel.getCodigoError(), ConstantesError.ERROR_1)) {
                        crudPersonaModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                             "Ocurrió un error en la operación",
                                                                             Constante.MENSAJE_ERROR));
                    } else {
                        crudPersonaModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                             crudPersonaModel.getMensajeError(),
                                                                             Constante.MENSAJE_ERROR));
                    }
                }
            }

            crudPersonaModelPaginado.setPaginaActual(paginadoModel.getPaginaActual());

            mav.addObject("crudPersonaModel", crudPersonaModelPaginado);

            return mav;
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping("/validarExistencia")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody PersonaModel personaModel, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();
        try {
            if (errors.hasErrors()) {

                result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
                return ResponseEntity.badRequest().body(result);

            }

            List<PersonaModel> personaModelResponse = new ArrayList<PersonaModel>();

            personaModelResponse = personaInterface.obtenerPersonasPorNumeroDocumento(personaModel.getTipoDocumento(),
                                                                                      personaModel.getNumeroDocumento());

            if (personaModelResponse.isEmpty()) {
                result.setMsg("N");
            } else {
                result.setMsg("S");
            }
            result.setResult(personaModelResponse);
        } catch (Exception e) {
            result.setMsg("N");
        }
        return ResponseEntity.ok(result);

    }
}
