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
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
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
		crudUsuarioModel.setResult(result);
		return busqueda(Constante.CONST_VACIA, Constante.CONST_CERO, Constante.CONST_CERO, Constante.PAGINA_INICIAL,
				Constante.CONST_CERO, crudUsuarioModel);
	}

	@PostMapping(value = "/crudAccionesListaPersonas", params = { "findRow",
	"busqueda" })
	public ModelAndView buscarUsuario(CrudPersonaModel crudPersonaModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");

		return busqueda(caja, Constante.CONST_CERO, Constante.CONST_CERO, Constante.PAGINA_INICIAL,
				Constante.CONST_CERO, crudPersonaModel);
	}

	@PostMapping(value = "/crudAccionesListaPersonas", params = { "rightRow",
			"busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(CrudPersonaModel crudPersonaModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");
		String paginaActual = req.getParameter("paginaActual");
		String paginaFinal = req.getParameter("paginaFinal");

		return busqueda(caja, Constante.CONST_CERO, Constante.DERECHA, paginaActual, paginaFinal, crudPersonaModel);
	}

	@PostMapping(value = "/crudAccionesListaPersonas", params = { "leftRow", "busqueda",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(CrudPersonaModel crudPersonaModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");
		String paginaActual = req.getParameter("paginaActual");
		String paginaFinal = req.getParameter("paginaFinal");

		return busqueda(caja, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual, paginaFinal, crudPersonaModel);
	}

	@PostMapping("/addpersona")
	public String registrarPersona(@ModelAttribute(name = "personaModel") PersonaModel personaModel, Model model) {
		String result;
		Date fechaAndHoraActual = getFechaAndHoraActual();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		setParametrosAuditoriaModel(fechaAndHoraActual, user.getUsername(), obtenerIp(), fechaAndHoraActual,
				user.getUsername(), obtenerIp());

		if (null != personaInterface.addPersona(personaModel, getParametrosAuditoriaModel())) {
			result = "1";
		} else {
			result = "0";
		}

		return "redirect:" + Constante.URL_LISTA_PERSONAS + "?result=" + result;
	}

	@PostMapping(value = "/crudAccionesListaPersonas", params = { "addRow" })
	public ModelAndView addUsuario(final CrudPersonaModel crudPersonaModel, final BindingResult bindingResult) {
		return preparingFormUsuario(null);
	}

	@PostMapping(value = "/crudAccionesListaPersonas", params = { "editRow" })
	public ModelAndView editUsuario(final CrudPersonaModel crudPersonaModel, final BindingResult bindingResult,
			final HttpServletRequest req) {

		return preparingFormUsuario(req.getParameter("editRow"));
	}

	@PostMapping(value = "/crudAccionesListaPersonas", params = { "removeRow" })
	public String removeUsuario(final CrudPersonaModel crudPersonaModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String result = "";

		if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
			personaInterface.removePersona(StringUtil.toInteger(req.getParameter("removeRow")));
			result = "1";
		} else {
			result = "0";
		}
		return "redirect:" + Constante.URL_LISTA_PERSONAS + "?result=" + result;

	}

	private ModelAndView preparingFormUsuario(Object id) {
		ModelAndView model = new ModelAndView(Constante.FORM_PERSONA);
		List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
		List<CatalogoConstraintModel> listCatalogoConstraintModelTipoPersona;
		List<CatalogoConstraintModel> listCatalogoConstraintModelTipoDocumento;
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		PersonaModel personaModel = new PersonaModel();
		if (!StringUtil.isEmpty(id)) {
			final Integer idPersona = StringUtil.toInteger(id);
			personaModel = personaInterface.findPersonaByIdModel(idPersona);
		}

		listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA, Constante.ESTADO_REGISTRO);
		listCatalogoConstraintModelTipoPersona = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA, Constante.TIPO_PERSONA);
		listCatalogoConstraintModelTipoDocumento = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_PERSONA, Constante.TIPO_DOC);

		model.addObject("personaModel", personaModel);
		model.addObject("usuario", user.getUsername());
		model.addObject("listEstadoRegistro", listCatalogoConstraintModelEstadoRegistro);
		model.addObject("listTipoPersona", listCatalogoConstraintModelTipoPersona);
		model.addObject("listTipoDocumento", listCatalogoConstraintModelTipoDocumento);
		return model;
	}

	private ModelAndView busqueda(String busqueda, String izquierda, String derecha, String pagina, String fin,
			CrudPersonaModel crudPersonaModel) {
		ModelAndView mav = new ModelAndView(Constante.LISTA_PERSONAS);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		crudPersonaModel = personaInterface.listPersonaByNombrePaginado(busqueda, paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);
		crudPersonaModel.setBusqueda(busqueda);

		if (paginadoModel.isMovIzquierda()) {
			crudPersonaModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
		} else if (paginadoModel.isMovDerecha()) {
			crudPersonaModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
		} else {
			crudPersonaModel.setResult(Constante.CONST_VACIA);
		}
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		crudPersonaModel.setPaginaActual(paginadoModel.getPaginaActual());
		crudPersonaModel.setUsuario(user.getUsername());

		mav.addObject("crudPersonaModel", crudPersonaModel);
		mav.addObject("usuario", user.getUsername());

		return mav;
	}
}
