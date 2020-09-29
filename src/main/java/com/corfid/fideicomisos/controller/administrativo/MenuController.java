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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.cruds.CrudMenuModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.administrativo.MenuInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
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

	@Autowired
	private Environment environment;

	public String obtenerIp() {
		return environment.getProperty("local.server.port");
	}

	@PostMapping("/cancel")
	public String cancel() {

		return "redirect:/menu/lista_menus";
	}

	@PostMapping("/showmenu")
	public ModelAndView showMenu(@ModelAttribute("datosGenerales") DatosGenerales datosGenerales) {
		ModelAndView mav = new ModelAndView(Constante.MENU);

		String empresa = datosGenerales.getRucEmpresa();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		mav.addObject("usuario", user.getUsername());

		return mav;

	}

	@GetMapping("/lista_menus")
	public ModelAndView showListaMenus(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,Model model, @RequestParam(name = "result", required = false) String result) {
		CrudMenuModel crudMenuModel = new CrudMenuModel();
		String empresa = datosGenerales.getRucEmpresa();
		crudMenuModel.setResult(result);
		return busqueda(Constante.CONST_VACIA, Constante.CONST_CERO, Constante.CONST_CERO, Constante.PAGINA_INICIAL,
				Constante.CONST_CERO, crudMenuModel);
		
	}

	@PostMapping("/addmenu")
	public String registrarMenu(@ModelAttribute(name = "menuModel") MenuModel menuModel, Model model) {
		String result;
		Date fechaAndHoraActual = getFechaAndHoraActual();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		setParametrosAuditoriaModel(fechaAndHoraActual, user.getUsername(), obtenerIp(), fechaAndHoraActual,
				user.getUsername(), obtenerIp());

		if (null != menuInterface.addMenu(menuModel, getParametrosAuditoriaModel())) {
			result = "1";
		} else {
			result = "0";
		}

		return "redirect:" + Constante.URL_LISTA_MENUS + "?result=" + result;
	}

	@PostMapping(value = "/crudAccionesListaMenus", params = { "findRow", "busqueda" })
	public ModelAndView buscarMenu(CrudMenuModel crudMenuModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");

		return busqueda(caja, Constante.CONST_CERO, Constante.CONST_CERO, Constante.PAGINA_INICIAL,
				Constante.CONST_CERO, crudMenuModel);
	}

	@PostMapping(value = "/crudAccionesListaMenus", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(CrudMenuModel crudMenuModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");
		String paginaActual = req.getParameter("paginaActual");
		String paginaFinal = req.getParameter("paginaFinal");

		return busqueda(caja, Constante.CONST_CERO, Constante.DERECHA, paginaActual, paginaFinal, crudMenuModel);
	}

	@PostMapping(value = "/crudAccionesListaMenus", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(CrudMenuModel crudMenuModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String caja = req.getParameter("busqueda");
		String paginaActual = req.getParameter("paginaActual");
		String paginaFinal = req.getParameter("paginaFinal");

		return busqueda(caja, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual, paginaFinal, crudMenuModel);
	}

	@PostMapping(value = "/crudAccionesListaMenus", params = { "addRow" })
	public ModelAndView addRol(final CrudMenuModel crudMenuModel, final BindingResult bindingResult) {
		return preparingFormMenu(null);
	}

	@PostMapping(value = "/crudAccionesListaMenus", params = { "editRow" })
	public ModelAndView editRol(final CrudMenuModel crudMenuModel, final BindingResult bindingResult,
			final HttpServletRequest req) {

		return preparingFormMenu(req.getParameter("editRow"));
	}

	@PostMapping(value = "/crudAccionesListaMenus", params = { "removeRow" })
	public String removeRol(final CrudMenuModel crudMenuModel, final BindingResult bindingResult,
			final HttpServletRequest req) {
		String result = "";

		if (!StringUtil.isEmpty(req.getParameter("removeRow"))) {
			menuInterface.removeMenu(StringUtil.toInteger(req.getParameter("removeRow")));
			result = "1";
		} else {
			result = "0";
		}
		return "redirect:" + Constante.URL_LISTA_MENUS + "?result=" + result;

	}

	private ModelAndView preparingFormMenu(Object id) {
		ModelAndView model = new ModelAndView(Constante.FORM_MENU);
		List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
		List<CatalogoConstraintModel> listCatalogoConstraintModelTipoMenu;
		MenuModel menuModel = new MenuModel();
		List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		
		if (!StringUtil.isEmpty(id)) {
			final Integer idMenu = StringUtil.toInteger(id);
			menuModel = menuInterface.findMenuByIdModel(idMenu);
		}
		
		listMenuModel = menuInterface.obtenerMenusPadre();

		listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_MENU, Constante.ESTADO_REGISTRO);
		listCatalogoConstraintModelTipoMenu = catalogoConstraintInterface
				.findByNombreTablaAndNombreCampo(Constante.TABLA_MENU, Constante.TIPO_MENU);

		model.addObject("listMenuPadres", listMenuModel);
		model.addObject("menuModel", menuModel);
		model.addObject("usuario", user.getUsername());
		model.addObject("listEstado", listCatalogoConstraintModelEstadoRegistro);
		model.addObject("listTipoMenu", listCatalogoConstraintModelTipoMenu);
		return model;
	}

	private ModelAndView busqueda(String busqueda, String izquierda, String derecha, String pagina, String fin,
			CrudMenuModel crudMenuModel) {
		ModelAndView mav = new ModelAndView(Constante.LISTA_MENUS);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		crudMenuModel = menuInterface.listMenuByDescripcionPaginado(busqueda, paginadoModel.getPaginaActual(),
				Constante.PAGINADO_5_ROWS);

		if (paginadoModel.isMovIzquierda()) {
			crudMenuModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
		} else if (paginadoModel.isMovDerecha()) {
			crudMenuModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
		} else {
			crudMenuModel.setResult(Constante.CONST_VACIA);
		}

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		crudMenuModel.setBusqueda(busqueda);
		crudMenuModel.setPaginaActual(paginadoModel.getPaginaActual());
		crudMenuModel.setUsuario(user.getUsername());

		mav.addObject("crudMenuModel", crudMenuModel);
		mav.addObject("usuario", user.getUsername());

		return mav;
	}
}
