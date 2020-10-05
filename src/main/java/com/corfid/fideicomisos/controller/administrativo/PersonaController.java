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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.administrativo.EmpresaModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.utilities.AjaxResponseBody;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.GenericModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
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
    private Environment environment;

    public String obtenerIp() {
        return environment.getProperty("local.server.port");
    }

    @PostMapping("/cancel")
    public String cancel() {

        return "redirect:" + Constante.URL_LISTA_PERSONAS;
    }

    @GetMapping("/lista_personas")
    public ModelAndView showListaUsuario(Model model, @RequestParam(name = "result", required = false) String result) {
        CrudPersonaModel crudUsuarioModel = new CrudPersonaModel();
        try {
            crudUsuarioModel.setResult(result);
            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_VACIA,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudUsuarioModel);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "findRow", "busqueda", "busquedaTipoPersona" })
    public ModelAndView buscarUsuario(CrudPersonaModel crudPersonaModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {
        try {
            String caja = req.getParameter("busqueda");
            String busquedaTipoPersona = req.getParameter("busquedaTipoPersona");

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
    public ModelAndView paginaDerecha(CrudPersonaModel crudPersonaModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {
        try {
            String caja = req.getParameter("busqueda");
            String busquedaTipoPersona = req.getParameter("busquedaTipoPersona");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");

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
    public ModelAndView paginaIzquierda(CrudPersonaModel crudPersonaModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) {
        try {
            String caja = req.getParameter("busqueda");
            String busquedaTipoPersona = req.getParameter("busquedaTipoPersona");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");

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

    @PostMapping("/addpersona")
    public ModelAndView registrarPersona(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                         @ModelAttribute(name = "personaModel") PersonaModel personaModel,
                                         Model model) {

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

            personaModel.setCliente(personaModel.getPermiteVinculacionCliente());
            personaModel.setIdUsuarioRegistro(datosGenerales.getIdUsuario());

            personaModel = personaInterface.addPersona(personaModel, getParametrosAuditoriaModel());

            if (!StringUtil.isEmpty(personaModel) && StringUtil.equiv(personaModel.getCodigoError(),
                                                                      ConstantesError.ERROR_0)) {
                crudPersonaModel.setResult(Constante.RESULT_CORRECTO);
            } else {
                crudPersonaModel.setResult(Constante.RESULT_ERROR);
                crudPersonaModel.setCodigoError(personaModel.getCodigoError());
                crudPersonaModel.setMensajeError(personaModel.getDescripcionError());
            }

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
    public ModelAndView addUsuario(final CrudPersonaModel crudPersonaModel,
                                   final BindingResult bindingResult) throws Exception {
        try {
            return preparingFormUsuario(null);
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "editRow" })
    public ModelAndView editUsuario(final CrudPersonaModel crudPersonaModel,
                                    final BindingResult bindingResult,
                                    final HttpServletRequest req) throws Exception {
        try {
            return preparingFormUsuario(req.getParameter("editRow"));
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping(value = "/crudAccionesListaPersonas", params = { "removeRow" })
    public ModelAndView removeUsuario(final CrudPersonaModel crudPersonaModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
                personaInterface.removePersona(StringUtil.toInteger(req.getParameter("removeRow")));
                crudPersonaModel.setResult(Constante.RESULT_CORRECTO);
            } else {
                crudPersonaModel.setResult(Constante.RESULT_ERROR);
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

    private ModelAndView preparingFormUsuario(Object id) throws Exception {
        ModelAndView model = new ModelAndView(Constante.FORM_PERSONA);
        try {
            List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
            List<CatalogoConstraintModel> listCatalogoConstraintModelTipoDocumento;
            List<GenericModel> listGenericModelVinculados = new ArrayList<GenericModel>();
            List<GenericModel> listGenericModel = null;
            List<PersonaModel> listPersonaModel = new ArrayList<PersonaModel>();

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // listar todas las empresas
            listPersonaModel = personaInterface.obtenerAllEmpresas();

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
                        listGenericModelVinculados.add(genericModel);
                    }
                }
            } else {
                personaModel.setEdicion(Constante.MODO_NUEVO);
            }

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
            model.addObject("usuario", user.getUsername());
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
        try {
            String result, mensajeError, personeria = "";
            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            result = crudPersonaModel.getResult();
            mensajeError = crudPersonaModel.getMensajeError();

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
            //personeria = crudPersonaModel.getBusquedaTipoPersona();

            crudPersonaModel = personaInterface.listPersonaByNombrePaginado(busqueda,
                                                                            personeria,
                                                                            paginadoModel.getPaginaActual(),
                                                                            Constante.PAGINADO_5_ROWS);
            crudPersonaModel.setBusqueda(busqueda);

            crudPersonaModel.setBusquedaTipoPersona(busquedaTipoPersona);

            if (StringUtil.equiv(result, Constante.RESULT_ERROR)) {
                crudPersonaModel.setMensaje(construirMensaje("Aviso", mensajeError, Constante.MENSAJE_ERROR));
            } else if (StringUtil.equiv(result, Constante.RESULT_CORRECTO)) {
                crudPersonaModel.setMensaje(construirMensaje("Aviso",
                                                             "La Operacion se Realizó Satisfactoriamente",
                                                             Constante.MENSAJE_SATISFACTORIO));
            }

            crudPersonaModel.setResult(result);

            if (paginadoModel.isMovIzquierda()) {
                crudPersonaModel.setMensaje(construirMensaje("Aviso",
                                                             "No hay más Registros a la Izquierda",
                                                             Constante.MENSAJE_INFORMATIVO));
                crudPersonaModel.setResult(Constante.RESULT_ERROR);
            } else if (paginadoModel.isMovDerecha()) {
                crudPersonaModel.setMensaje(construirMensaje("Aviso",
                                                             "No hay más Registros a la Derecha",
                                                             Constante.MENSAJE_INFORMATIVO));
                crudPersonaModel.setResult(Constante.RESULT_ERROR);
            }

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            crudPersonaModel.setPaginaActual(paginadoModel.getPaginaActual());
            crudPersonaModel.setUsuario(user.getUsername());

            mav.addObject("crudPersonaModel", crudPersonaModel);
            mav.addObject("usuario", user.getUsername());

            return mav;
        } catch (Exception e) {
            return new ModelAndView();
        }
    }

    @PostMapping("/validarExistencia")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody PersonaModel personaModel, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();
        try {
            //If error, just return a 400 bad request, along with the error message
            if (errors.hasErrors()) {

                result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
                return ResponseEntity.badRequest().body(result);

            }

            List<PersonaModel> personaModelResponse = new ArrayList<PersonaModel>();//userService.login(loginForm);

            personaModelResponse = personaInterface.obtenerPersonasPorNumeroDocumento(personaModel.getTipoDocumento(),
                                                               personaModel.getNumeroDocumento());

            if (personaModelResponse.isEmpty()) {
                result.setMsg("persona no encontrada");
            } else {
                result.setMsg("encontrado");
            }
            result.setResult(personaModelResponse);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ResponseEntity.ok(result);

    }
}
