package com.corfid.fideicomisos.controller.administrativo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.administrativo.CatalogoMailModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.cruds.CrudUsuarioModel;
import com.corfid.fideicomisos.model.utilities.AjaxResponseBody;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.GenericModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.CatalogoMailInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.RolInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.service.utilities.EnvioMailInterface;
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
    @Qualifier("envioEmail")
    EnvioMailInterface envioMailInterface;

    @Autowired
    @Qualifier("catalogoMailServiceImpl")
    CatalogoMailInterface catalogoMailInterface;

    @Autowired
    private Environment environment;

    public String obtenerIp() {
        return environment.getProperty("local.server.port");
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
    public ModelAndView showListaUsuario(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                         Model model) {
        CrudUsuarioModel crudUsuarioModel = new CrudUsuarioModel();

        crudUsuarioModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
        crudUsuarioModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
        datosGenerales.setModulo(Constante.MODULO_USUARIO);

        return busqueda(Constante.CONST_VACIA,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudUsuarioModel);
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "findRow", "busqueda" })
    public ModelAndView buscarUsuario(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      CrudUsuarioModel crudUsuarioModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {
        String caja = req.getParameter("busqueda");
        crudUsuarioModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
        crudUsuarioModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

        return busqueda(caja,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudUsuarioModel);
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerecha(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      CrudUsuarioModel crudUsuarioModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) {
        String caja = req.getParameter("busqueda");
        String paginaActual = req.getParameter("paginaActual");
        String paginaFinal = req.getParameter("paginaFinal");
        crudUsuarioModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
        crudUsuarioModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

        return busqueda(caja, Constante.CONST_CERO, Constante.DERECHA, paginaActual, paginaFinal, crudUsuarioModel);
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                        CrudUsuarioModel crudUsuarioModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) {
        String caja = req.getParameter("busqueda");
        String paginaActual = req.getParameter("paginaActual");
        String paginaFinal = req.getParameter("paginaFinal");
        crudUsuarioModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
        crudUsuarioModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());

        return busqueda(caja, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual, paginaFinal, crudUsuarioModel);
    }

    @PostMapping(value = "/addusuario", params = { "cancelar" })
    public String cancelar(final CrudPersonaModel crudPersonaModel,
                           final BindingResult bindingResult,
                           final HttpServletRequest req) throws Exception {
        return "redirect:" + Constante.URL_LISTA_USUARIOS;
    }

    @PostMapping(value = "/addusuario", params = { "saveRow" })
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
            usuarioModel.setIdEmpresaSesion(datosGenerales.getIdEmpresa());
            usuarioModel.setIdUsuarioSesion(datosGenerales.getIdUsuario());

            if (StringUtil.isEmpty(usuarioModel.getEstadoActividad())) {
                usuarioModel.setEstadoActividad("1");
            }

            usuarioModel = usuarioInterface.addUsuario(usuarioModel, getParametrosAuditoriaModel());
            if (!StringUtil.isEmpty(usuarioModel) && StringUtil.equiv(usuarioModel.getCodigoError(),
                                                                      ConstantesError.ERROR_0)) {
                crudUsuarioModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                crudUsuarioModel.setCodigoError(usuarioModel.getCodigoError());
                crudUsuarioModel.setMensajeError(usuarioModel.getDescripcionError());
            }
            crudUsuarioModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
            crudUsuarioModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
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
    public ModelAndView addUsuario(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                   final CrudUsuarioModel crudUsuarioModel,
                                   final BindingResult bindingResult) {

        return preparingFormUsuario(null, datosGenerales.getTipoUsuarioSession(), datosGenerales.getIdEmpresa());
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "editRow" })
    public ModelAndView editUsuario(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                    final CrudUsuarioModel crudUsuarioModel,
                                    final BindingResult bindingResult,
                                    final HttpServletRequest req) {

        return preparingFormUsuario(req.getParameter("editRow"),
                                    datosGenerales.getTipoUsuarioSession(),
                                    datosGenerales.getIdEmpresa());
    }

    @PostMapping(value = "/crudAccionesListaUsuarios", params = { "removeRow" })
    public ModelAndView removeUsuario(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      CrudUsuarioModel crudUsuarioModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {

        if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
            crudUsuarioModel = usuarioInterface.removeUsuario(StringUtil.toInteger(req.getParameter("removeRow")),
                                                              datosGenerales.getIdUsuario(),
                                                              datosGenerales.getIdEmpresa());
        } else {
            crudUsuarioModel.setCodigoError(ConstantesError.ERROR_1);
        }

        crudUsuarioModel.setTipoUsuarioSession(datosGenerales.getTipoUsuarioSession());
        crudUsuarioModel.setIdEmpresaSession(datosGenerales.getIdEmpresa());
        return busqueda(Constante.CONST_VACIA,
                        Constante.CONST_CERO,
                        Constante.CONST_CERO,
                        Constante.PAGINA_INICIAL,
                        Constante.CONST_CERO,
                        crudUsuarioModel);

    }

    private ModelAndView preparingFormUsuario(Object id, String tipoUsuarioSesion, Integer idEmpresaSesion) {
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
            UsuarioModel usuarioModel = new UsuarioModel();

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!StringUtil.isEmpty(id)) {
                final Integer idUsuario = StringUtil.toInteger(id);
                usuarioModel = usuarioInterface.findUsuarioByIdModel(idUsuario);
                usuarioModel.setEdicion(Constante.MODO_EDICION);
            }

            /* Este bloque es para obtener los usuarios asignados o por asignar a un usuario */
            listPersonaModel = personaInterface.obtenerPersonasNoVinculadasUsuarios();
            listGenericPersonaModel = new ArrayList<GenericModel>();
            if (!StringUtil.isEmpty(listPersonaModel)) {
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
            listRolModel = rolInterface.listAllRolesByEstadoRegistro(Constante.ESTADO_REGISTRO_VIGENTE,
                                                                     tipoUsuarioSesion,
                                                                     idEmpresaSesion);

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

            if (StringUtil.equiv(tipoUsuarioSesion, Constante.TIPO_USUARIO_SUPER_ADMIN)) {
                listCatalogoConstraintModelTipoUsuario = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO,
                                                                                                                     Constante.TIPO_USUARIO);
            } else {
                listCatalogoConstraintModelTipoUsuario = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO_ADM,
                                                                                                                     Constante.TIPO_USUARIO);
            }

            usuarioModel.setTipoUsuarioSesion(tipoUsuarioSesion);

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
            CrudUsuarioModel crudUsuarioModelPaginado;

            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            crudUsuarioModelPaginado = usuarioInterface.listUsuarioByUsernamePaginado(busqueda,
                                                                                      crudUsuarioModel.getTipoUsuarioSession(),
                                                                                      user.getUsername(),
                                                                                      crudUsuarioModel.getIdEmpresaSession(),
                                                                                      paginadoModel.getPaginaActual(),
                                                                                      Constante.PAGINADO_5_ROWS);

            if (paginadoModel.isMovIzquierda()) {
                crudUsuarioModelPaginado.setCodigoError(ConstantesError.ERROR_1);
                crudUsuarioModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                     "No hay más Registros a la Izquierda",
                                                                     Constante.MENSAJE_INFORMATIVO));
            } else if (paginadoModel.isMovDerecha()) {
                crudUsuarioModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                     "No hay más Registros a la Derecha",
                                                                     Constante.MENSAJE_INFORMATIVO));
                crudUsuarioModelPaginado.setCodigoError(ConstantesError.ERROR_1);
            } else {
                if (!StringUtil.isEmpty(crudUsuarioModel.getCodigoError())) {
                    if (StringUtil.equiv(crudUsuarioModel.getCodigoError(), ConstantesError.ERROR_0)) {
                        crudUsuarioModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                             "La Operacion se Realizó Satisfactoriamente",
                                                                             Constante.MENSAJE_SATISFACTORIO));
                    } else if (StringUtil.equiv(crudUsuarioModel.getCodigoError(), ConstantesError.ERROR_1)) {
                        crudUsuarioModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                             "Ocurrió un error en la operación",
                                                                             Constante.MENSAJE_ERROR));
                    } else {
                        crudUsuarioModelPaginado.setMensaje(construirMensaje("Aviso",
                                                                             crudUsuarioModel.getMensajeError(),
                                                                             Constante.MENSAJE_ERROR));
                    }
                    crudUsuarioModelPaginado.setCodigoError(crudUsuarioModel.getCodigoError());
                }
            }

            crudUsuarioModelPaginado.setPaginaActual(paginadoModel.getPaginaActual());
            crudUsuarioModelPaginado.setUsuario(user.getUsername());

            mav.addObject("crudUsuarioModel", crudUsuarioModelPaginado);
            mav.addObject("usuario", user.getUsername());

            return mav;
        } catch (Exception e) {
            return new ModelAndView(Constante.SITIO_EN_CONSTRUCCION);
        }
    }

    @PostMapping("/buscarNumeroDocumentoPersona")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody PersonaModel personaModel, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();
        try {
            if (errors.hasErrors()) {

                result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
                return ResponseEntity.badRequest().body(result);

            }

            PersonaModel personaModelResponse = new PersonaModel();
            personaModelResponse = personaInterface.findPersonaByIdModel(personaModel.getIdPersona());

            if (!StringUtil.isEmpty(personaModelResponse)) {
                result.setMsg(personaModelResponse.getTipoDocumento()+personaModelResponse.getNumeroDocumento());
            } else {
                result.setMsg(Constante.CONST_VACIA);
            }
        } catch (Exception e) {
            result.setMsg(Constante.CONST_VACIA);
        }
        return ResponseEntity.ok(result);

    }

    @PostMapping("/enviarMailCredenciales")
    public ResponseEntity<?> sendMailCredenciales(@Valid @RequestBody String idPersona,
                                                  Errors errors) throws Exception {

        AjaxResponseBody result = new AjaxResponseBody();
        UsuarioModel usuarioModel = null;
        String cuerpoMail;
        Map<String, String> output = new HashMap<String, String>();
        try {
            idPersona = idPersona.replace("\"", "");
            if (errors.hasErrors()) {
                result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
                return ResponseEntity.badRequest().body(result);
            }

            /*
             * PersonaModel personaModel = new PersonaModel(); personaModel = personaInterface.findPersonaByIdModel(StringUtil.toInteger(idPersona));
             */

            usuarioModel = usuarioInterface.findUsuarioByPersona(StringUtil.toInteger(idPersona));

            output = usuarioInterface.actualizarAndDevolverContrasenia(usuarioModel.getIdUsuario());

            if (StringUtil.equiv(StringUtil.toStr(output.get("error")), ConstantesError.ERROR_0)) {
                CatalogoMailModel catalogoMailModel = catalogoMailInterface.obtenerCorreoPorCodigo(Constante.CODIGO_EMAIL_PASSWORD);

                cuerpoMail = catalogoMailModel.getContenido().replace("NOMBRE_COMPLETO",
                                                                      usuarioModel.getNombreCompleto());
                cuerpoMail = cuerpoMail.replace("PASSWORD", StringUtil.toStr(output.get("mensaje")));

                envioMailInterface.sendEmail(usuarioModel.getCorreo(), catalogoMailModel.getAsunto(), cuerpoMail);

                result.setMsg(ConstantesError.ERROR_0);
            } else {
                result.setMsg(ConstantesError.ERROR_1);
            }
        } catch (Exception e) {
            result.setMsg(ConstantesError.ERROR_1);
        }
        return ResponseEntity.ok(result);

    }
}
