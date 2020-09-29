package com.corfid.fideicomisos.controller.administrativo;

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
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudUsuarioModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends InitialController {
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioInterface usuarioInterface;

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

		return "redirect:" + Constante.URL_LISTA_USUARIOS;
	}

	@GetMapping("/lista_usuarios")
	public ModelAndView showListaUsuario(Model model, @RequestParam(name = "result", required = false) String result) {
		CrudUsuarioModel crudUsuarioModel = new CrudUsuarioModel();
		crudUsuarioModel.setResult(result);
		return busqueda(Constante.CONST_VACIA, Constante.CONST_CERO, Constante.CONST_CERO, Constante.PAGINA_INICIAL,
				Constante.CONST_CERO, crudUsuarioModel);
	}

	@PostMapping(value = "/crudAccionesListaUsuarios", params = { "findRow",
	"busqueda" })
	public ModelAndView buscarUsuario(CrudUsuarioModel crudUsuarioModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");

		return busqueda(caja, Constante.CONST_CERO, Constante.CONST_CERO, Constante.PAGINA_INICIAL,
				Constante.CONST_CERO, crudUsuarioModel);
	}

	@PostMapping(value = "/crudAccionesListaUsuarios", params = { "rightRow",
			"busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(CrudUsuarioModel crudUsuarioModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");
		String paginaActual = req.getParameter("paginaActual");
		String paginaFinal = req.getParameter("paginaFinal");

		return busqueda(caja, Constante.CONST_CERO, Constante.DERECHA, paginaActual, paginaFinal, crudUsuarioModel);
	}

	@PostMapping(value = "/crudAccionesListaUsuarios", params = { "leftRow", "busqueda",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(CrudUsuarioModel crudUsuarioModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");
		String paginaActual = req.getParameter("paginaActual");
		String paginaFinal = req.getParameter("paginaFinal");

		return busqueda(caja, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual, paginaFinal, crudUsuarioModel);
	}

	@PostMapping("/addusuario")
	public String registrarUsuario(@ModelAttribute(name = "usuarioModel") UsuarioModel usuarioModel, Model model) {
		String result;
		Date fechaAndHoraActual = getFechaAndHoraActual();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		setParametrosAuditoriaModel(fechaAndHoraActual, user.getUsername(), obtenerIp(), fechaAndHoraActual,
				user.getUsername(), obtenerIp());

		if (null != usuarioInterface.addUsuario(usuarioModel, getParametrosAuditoriaModel())) {
			result = "1";
		} else {
			result = "0";
		}

		return "redirect:" + Constante.URL_LISTA_USUARIOS + "?result=" + result;
	}

	@PostMapping(value = "/crudAccionesListaUsuarios", params = { "addRow" })
	public ModelAndView addUsuario(final CrudUsuarioModel crudUsuarioModel, final BindingResult bindingResult) {
		return preparingFormUsuario(null);
	}

	@PostMapping(value = "/crudAccionesListaUsuarios", params = { "editRow" })
	public ModelAndView editUsuario(final CrudUsuarioModel crudUsuarioModel, final BindingResult bindingResult,
			final HttpServletRequest req) {

		return preparingFormUsuario(req.getParameter("editRow"));
	}

	@PostMapping(value = "/crudAccionesListaUsuarios", params = { "removeRow" })
	public String removeUsuario(final CrudUsuarioModel crudUsuarioModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String result = "";

		if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
			usuarioInterface.removeUsuario(StringUtil.toInteger(req.getParameter("removeRow")));
			result = "1";
		} else {
			result = "0";
		}
		return "redirect:" + Constante.URL_LISTA_USUARIOS + "?result=" + result;

	}

	private ModelAndView preparingFormUsuario(Object id) {
		ModelAndView model = new ModelAndView(Constante.FORM_USUARIO);
		List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
		List<CatalogoConstraintModel> listCatalogoConstraintModelActividad;
		List<CatalogoConstraintModel> listCatalogoConstraintModelTipoUsuario;
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		UsuarioModel usuarioModel = new UsuarioModel();
		if (!StringUtil.isEmpty(id)) {
			final Integer idUsuario = StringUtil.toInteger(id);
			usuarioModel = usuarioInterface.findUsuarioByIdModel(idUsuario);
		}

		listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO, Constante.ESTADO_REGISTRO);
		listCatalogoConstraintModelActividad = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO, Constante.INDICADOR_ACTIVIDAD);
		listCatalogoConstraintModelTipoUsuario = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO, Constante.TIPO_USUARIO);

		model.addObject("usuarioModel", usuarioModel);
		model.addObject("usuario", user.getUsername());
		model.addObject("listActividad", listCatalogoConstraintModelActividad);
		model.addObject("listEstado", listCatalogoConstraintModelEstadoRegistro);
		model.addObject("listTipoUsuario", listCatalogoConstraintModelTipoUsuario);
		return model;
	}

	private ModelAndView busqueda(String busqueda, String izquierda, String derecha, String pagina, String fin,
			CrudUsuarioModel crudUsuarioModel) {
		ModelAndView mav = new ModelAndView(Constante.LISTA_USUARIOS);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		crudUsuarioModel = usuarioInterface.listUsuarioByUsernamePaginado(busqueda, paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);
		crudUsuarioModel.setBusqueda(busqueda);

		if (paginadoModel.isMovIzquierda()) {
			crudUsuarioModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
		} else if (paginadoModel.isMovDerecha()) {
			crudUsuarioModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
		} else {
			crudUsuarioModel.setResult(Constante.CONST_VACIA);
		}
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		crudUsuarioModel.setPaginaActual(paginadoModel.getPaginaActual());
		crudUsuarioModel.setUsuario(user.getUsername());

		mav.addObject("crudUsuarioModel", crudUsuarioModel);
		mav.addObject("usuario", user.getUsername());

		return mav;
	}
}
