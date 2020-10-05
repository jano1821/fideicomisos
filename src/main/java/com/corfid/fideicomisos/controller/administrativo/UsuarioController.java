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
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudUsuarioModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.GenericModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.RolInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends InitialController {

    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioInterface usuarioInterface;

    @Autowired
    @Qualifier("personaServiceImpl")
    private PersonaInterface personaInterface;

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    @Autowired
    @Qualifier("rolServiceImpl")
    private RolInterface rolInterface;

    @Autowired
    private Environment environment;

    public String obtenerIp() {
        return environment.getProperty("local.server.port");
    }

    @PostMapping("/cancel")
    public String cancel() {

        return "redirect:" + Constante.URL_LISTA_USUARIOS;
    }

    @GetMapping("/crudAccionesListaUsuarios")
    public String interceptor() {
        return "redirect:" + Constante.URL_SELECCION;
    }

    @GetMapping("/addusuario")
    public String interceptorCrud() {
        return "redirect:" + Constante.URL_SELECCION;
    }

    @GetMapping("/lista_usuarios")
    public ModelAndView showListaUsuario(Model model, @RequestParam(name = "result", required = false) String result) {
        CrudUsuarioModel crudUsuarioModel = new CrudUsuarioModel();
        crudUsuarioModel.setResult(result);
        return busqueda(Constante.CONST_VACIA,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudUsuarioModel);
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "findRow", "busqueda" })
    public ModelAndView buscarUsuario(CrudUsuarioModel crudUsuarioModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {
        String caja = req.getParameter("busqueda");

        return busqueda(caja,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudUsuarioModel);
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerecha(CrudUsuarioModel crudUsuarioModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {
        String caja = req.getParameter("busqueda");
        String paginaActual = req.getParameter("paginaActual");
        String paginaFinal = req.getParameter("paginaFinal");

        return busqueda(caja, Constante.CONST_CERO, Constante.DERECHA, paginaActual, paginaFinal, crudUsuarioModel);
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierda(CrudUsuarioModel crudUsuarioModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) {
        String caja = req.getParameter("busqueda");
        String paginaActual = req.getParameter("paginaActual");
        String paginaFinal = req.getParameter("paginaFinal");

        return busqueda(caja, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual, paginaFinal, crudUsuarioModel);
    }

    @PostMapping("/addusuario")
    public ModelAndView registrarUsuario(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                         @ModelAttribute(name = "usuarioModel") UsuarioModel usuarioModel,
                                         Model model) throws Exception {
        CrudUsuarioModel crudUsuarioModel = new CrudUsuarioModel();
        try {
            Date fechaAndHoraActual = getFechaAndHoraActual();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            setParametrosAuditoriaModel(fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp(),
                                        fechaAndHoraActual,
                                        user.getUsername(),
                                        obtenerIp());

            usuarioModel.setIdUsuarioRegistro(datosGenerales.getIdUsuario());
            usuarioModel.setTipoUsuarioSesion(datosGenerales.getTipoUsuarioSession());
            usuarioModel = usuarioInterface.addUsuario(usuarioModel, getParametrosAuditoriaModel());
            if (!StringUtil.isEmpty(usuarioModel) && StringUtil.equiv(usuarioModel.getCodigoError(),
                                                                      ConstantesError.ERROR_0)) {
                crudUsuarioModel.setResult(Constante.RESULT_CORRECTO);
            } else {
                crudUsuarioModel.setResult(Constante.RESULT_ERROR);
                crudUsuarioModel.setCodigoError(usuarioModel.getCodigoError());
                crudUsuarioModel.setMensajeError(usuarioModel.getDescripcionError());
            }
            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudUsuarioModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_USUARIOS);
        }
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "addRow" })
    public ModelAndView addUsuario(final CrudUsuarioModel crudUsuarioModel, final BindingResult bindingResult) {
        return preparingFormUsuario(null);
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "editRow" })
    public ModelAndView editUsuario(final CrudUsuarioModel crudUsuarioModel,
                                    final BindingResult bindingResult,
                                    final HttpServletRequest req) {

        return preparingFormUsuario(req.getParameter("editRow"));
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "removeRow" })
    public ModelAndView removeUsuario(final CrudUsuarioModel crudUsuarioModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {

        if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
            usuarioInterface.removeUsuario(StringUtil.toInteger(req.getParameter("removeRow")));
            crudUsuarioModel.setResult(Constante.RESULT_CORRECTO);
        } else {
            crudUsuarioModel.setResult(Constante.RESULT_ERROR);
        }

        return busqueda(Constante.CONST_VACIA,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudUsuarioModel);

    }

    private ModelAndView preparingFormUsuario(Object id) {
        ModelAndView model = new ModelAndView(Constante.FORM_USUARIO);
        try {
            List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
            List<CatalogoConstraintModel> listCatalogoConstraintModelActividad;
            List<CatalogoConstraintModel> listCatalogoConstraintModelTipoUsuario;
            List<PersonaModel> listPersonaModel;
            PersonaModel personaModel;
            List<GenericModel> listGenericPersonaModelVinculado = null;
            List<GenericModel> listGenericPersonaModel = null;
            List<RolModel> listRolModel;
            List<RolModel> listRolModelVinculado;
            List<GenericModel> listGenericRolModelVinculado = null;
            List<GenericModel> listGenericRolModel = null;
            GenericModel genericModel;

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            UsuarioModel usuarioModel = new UsuarioModel();
            if (!StringUtil.isEmpty(id)) {
                final Integer idUsuario = StringUtil.toInteger(id);
                usuarioModel = usuarioInterface.findUsuarioByIdModel(idUsuario);
                usuarioModel.setEdicion(Constante.MODO_EDICION);
            }

            /* Este bloque es para obtener los usuarios asignados o por asignar a un usuario */
            listPersonaModel = personaInterface.obtenerPersonasNoVinculadasUsuarios();

            if (!StringUtil.isEmpty(listPersonaModel)) {
                listGenericPersonaModel = new ArrayList<GenericModel>();

                for (PersonaModel personaModelTemp : listPersonaModel) {
                    genericModel = new GenericModel();
                    genericModel.setId(StringUtil.toStr(personaModelTemp.getIdPersona()));
                    genericModel.setDescripcion(personaModelTemp.getNombreCompleto());
                    listGenericPersonaModel.add(genericModel);
                }
            }
            if (StringUtil.equiv(usuarioModel.getEdicion(), Constante.MODO_EDICION)) {
                if (!StringUtil.isEmpty(usuarioModel.getIdPersona())) {
                    personaModel = personaInterface.findPersonaByIdModel(usuarioModel.getIdPersona());
                    if (!StringUtil.isEmpty(personaModel)) {
                        listGenericPersonaModelVinculado = new ArrayList<GenericModel>();

                        genericModel = new GenericModel();
                        genericModel.setId(StringUtil.toStr(personaModel.getIdPersona()));
                        genericModel.setDescripcion(personaModel.getNombreCompleto());
                        listGenericPersonaModelVinculado.add(genericModel);
                        listGenericPersonaModel.add(genericModel);
                    }
                }
            }

            String comboPersonas = construirComboSearch(Constante.SELECCION_SIMPLE,
                                                        listGenericPersonaModel,
                                                        "persona",
                                                        "Seleccione Persona...",
                                                        listGenericPersonaModelVinculado);

            /* Este bloque es para obtener los roles de un usuario */
            listRolModel = rolInterface.listAllRolesByEstadoRegistro(Constante.ESTADO_REGISTRO_VIGENTE);

            if (!StringUtil.isEmpty(listRolModel)) {
                listGenericRolModel = new ArrayList<GenericModel>();

                for (RolModel rolModelTemp : listRolModel) {
                    genericModel = new GenericModel();
                    genericModel.setId(StringUtil.toStr(rolModelTemp.getIdRol()));
                    genericModel.setDescripcion(rolModelTemp.getDescripcion());
                    listGenericRolModel.add(genericModel);
                }
            }
            if (StringUtil.equiv(usuarioModel.getEdicion(), Constante.MODO_EDICION)) {
                //if (!StringUtil.isEmpty(usuarioModel.getIdPersona())) {
                listRolModelVinculado = usuarioModel.getListRoles();
                if (!StringUtil.isEmpty(listRolModelVinculado)) {
                    listGenericRolModelVinculado = new ArrayList<GenericModel>();

                    for (RolModel rolModelVinculado : listRolModelVinculado) {
                    genericModel = new GenericModel();
                    genericModel.setId(StringUtil.toStr(rolModelVinculado.getIdRol()));
                    genericModel.setDescripcion(rolModelVinculado.getDescripcion());
                    listGenericRolModelVinculado.add(genericModel);
                    }
                }
                //}
            }

            String comboRoles = construirComboSearch(Constante.SELECCION_MULTIPLE,
                                                     listGenericRolModel,
                                                     "rol",
                                                     "Seleccione Roles...",
                                                     listGenericRolModelVinculado);

            listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO,
                                                                                                                    Constante.ESTADO_REGISTRO);
            listCatalogoConstraintModelActividad = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO,
                                                                                                               Constante.INDICADOR_ACTIVIDAD);
            listCatalogoConstraintModelTipoUsuario = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO,
                                                                                                                 Constante.TIPO_USUARIO);

            model.addObject("usuarioModel", usuarioModel);
            model.addObject("usuario", user.getUsername());
            model.addObject("comboPersonas", comboPersonas);
            model.addObject("comboRoles", comboRoles);
            model.addObject("listActividad", listCatalogoConstraintModelActividad);
            model.addObject("listEstado", listCatalogoConstraintModelEstadoRegistro);
            model.addObject("listTipoUsuario", listCatalogoConstraintModelTipoUsuario);

            return model;
        } catch (Exception e) {
            return new ModelAndView(Constante.SITIO_EN_CONSTRUCCION);
        }
    }

    private ModelAndView busqueda(String busqueda,
                                  String izquierda,
                                  String derecha,
                                  String pagina,
                                  String fin,
                                  CrudUsuarioModel crudUsuarioModel) {
        ModelAndView mav = new ModelAndView(Constante.LISTA_USUARIOS);
        try {
            String result, mensajeError;

            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            result = crudUsuarioModel.getResult();
            mensajeError = crudUsuarioModel.getMensajeError();

            crudUsuarioModel = usuarioInterface.listUsuarioByUsernamePaginado(busqueda,
                                                                              paginadoModel.getPaginaActual(),
                                                                              Constante.PAGINADO_5_ROWS);
            crudUsuarioModel.setBusqueda(busqueda);

            if (StringUtil.equiv(result, Constante.RESULT_ERROR)) {
                crudUsuarioModel.setMensaje(construirMensaje("Aviso", mensajeError, Constante.MENSAJE_ERROR));
            } else if (StringUtil.equiv(result, Constante.RESULT_CORRECTO)) {
                crudUsuarioModel.setMensaje(construirMensaje("Aviso",
                                                             "La Operacion se Realizó Satisfactoriamente",
                                                             Constante.MENSAJE_SATISFACTORIO));
            }

            crudUsuarioModel.setResult(result);

            if (paginadoModel.isMovIzquierda()) {
                crudUsuarioModel.setMensaje(construirMensaje("Aviso",
                                                             "No hay más Registros a la Izquierda",
                                                             Constante.MENSAJE_INFORMATIVO));
                crudUsuarioModel.setResult(Constante.RESULT_ERROR);
            } else if (paginadoModel.isMovDerecha()) {
                crudUsuarioModel.setMensaje(construirMensaje("Aviso",
                                                             "No hay más Registros a la Derecha",
                                                             Constante.MENSAJE_INFORMATIVO));
                crudUsuarioModel.setResult(Constante.RESULT_ERROR);
            }

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            crudUsuarioModel.setPaginaActual(paginadoModel.getPaginaActual());
            crudUsuarioModel.setUsuario(user.getUsername());

            mav.addObject("crudUsuarioModel", crudUsuarioModel);
            mav.addObject("usuario", user.getUsername());

            return mav;
        } catch (Exception e) {
            return new ModelAndView(Constante.SITIO_EN_CONSTRUCCION);
        }
    }
}
