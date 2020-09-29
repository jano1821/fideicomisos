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
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.cruds.CrudRolModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.RolInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/rol")
public class RolController  extends InitialController {
	@Autowired
	@Qualifier("rolServiceImpl")
	private RolInterface rolInterface;

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

		return "redirect:" + Constante.URL_LISTA_ROLES;
	}

	@GetMapping("/lista_roles")
	public ModelAndView showListaRoles(Model model, @RequestParam(name = "result", required = false) String result) {
		CrudRolModel crudRolModel = new CrudRolModel();
		crudRolModel.setResult(result);
		return busqueda(Constante.CONST_VACIA, Constante.CONST_CERO, Constante.CONST_CERO, Constante.PAGINA_INICIAL,
				Constante.CONST_CERO, crudRolModel);
	}
	
	@PostMapping("/addrol")
	public String registrarRol(@ModelAttribute(name = "rolModel") RolModel rolModel, Model model) {
		String result;
		Date fechaAndHoraActual = getFechaAndHoraActual();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		setParametrosAuditoriaModel(fechaAndHoraActual, user.getUsername(), obtenerIp(), fechaAndHoraActual,
				user.getUsername(), obtenerIp());

		if (null != rolInterface.addRol(rolModel, getParametrosAuditoriaModel())) {
			result = "1";
		} else {
			result = "0";
		}

		return "redirect:" + Constante.URL_LISTA_ROLES + "?result=" + result;
	}
	
	@PostMapping(value = "/crudAccionesListaRoles", params = { "findRow",
	"busqueda" })
	public ModelAndView buscarRol(CrudRolModel crudRolModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");

		return busqueda(caja, Constante.CONST_CERO, Constante.CONST_CERO, Constante.PAGINA_INICIAL,
				Constante.CONST_CERO, crudRolModel);
	}

	@PostMapping(value = "/crudAccionesListaRoles", params = { "rightRow",
			"busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(CrudRolModel crudRolModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");
		String paginaActual = req.getParameter("paginaActual");
		String paginaFinal = req.getParameter("paginaFinal");

		return busqueda(caja, Constante.CONST_CERO, Constante.DERECHA, paginaActual, paginaFinal, crudRolModel);
	}

	@PostMapping(value = "/crudAccionesListaRoles", params = { "leftRow", "busqueda",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(CrudRolModel crudRolModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");
		String paginaActual = req.getParameter("paginaActual");
		String paginaFinal = req.getParameter("paginaFinal");

		return busqueda(caja, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual, paginaFinal, crudRolModel);
	}

	@PostMapping(value = "/crudAccionesListaRoles", params = { "addRow" })
	public ModelAndView addRol(final CrudRolModel crudRolModel, final BindingResult bindingResult) {
		return preparingFormRol(null);
	}

	@PostMapping(value = "/crudAccionesListaRoles", params = { "editRow" })
	public ModelAndView editRol(final CrudRolModel crudRolModel, final BindingResult bindingResult,
			final HttpServletRequest req) {

		return preparingFormRol(req.getParameter("editRow"));
	}

	@PostMapping(value = "/crudAccionesListaRoles", params = { "removeRow" })
	public String removeRol(final CrudRolModel crudRolModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String result = "";

		if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
			rolInterface.removeRol(StringUtil.toInteger(req.getParameter("removeRow")));
			result = "1";
		} else {
			result = "0";
		}
		return "redirect:" + Constante.URL_LISTA_ROLES + "?result=" + result;

	}
	
	private ModelAndView preparingFormRol(Object id) {
		ModelAndView model = new ModelAndView(Constante.FORM_ROL);
		List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		RolModel rolModel = new RolModel();
		if (!StringUtil.isEmpty(id)) {
			final Integer idRol = StringUtil.toInteger(id);
			rolModel = rolInterface.findRolByIdModel(idRol);
		}

		listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_ROLES, Constante.ESTADO_REGISTRO);

		model.addObject("rolModel", rolModel);
		model.addObject("usuario", user.getUsername());
		model.addObject("listEstado", listCatalogoConstraintModelEstadoRegistro);
		return model;
	}

	private ModelAndView busqueda(String busqueda, String izquierda, String derecha, String pagina, String fin,
			CrudRolModel crudRolModel) {
		ModelAndView mav = new ModelAndView(Constante.LISTA_ROLES);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		crudRolModel = rolInterface.listRolByDescripcionPaginado(busqueda, paginadoModel.getPaginaActual(),
				Constante.PAGINADO_5_ROWS);

		if (paginadoModel.isMovIzquierda()) {
			crudRolModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
		} else if (paginadoModel.isMovDerecha()) {
			crudRolModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
		} else {
			crudRolModel.setResult(Constante.CONST_VACIA);
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
