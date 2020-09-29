package com.corfid.fideicomisos.controller.administrativo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudMenuModel;
import com.corfid.fideicomisos.model.cruds.CrudPersonaModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.service.administrativo.EmpresaInterface;
import com.corfid.fideicomisos.service.administrativo.MenuInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@SessionAttributes("datosGenerales")
@RequestMapping("/seleccion")
public class SeleccionController extends InitialController {

	@Autowired
	@Qualifier("menuServiceImpl")
	MenuInterface menuInterface;

	@Autowired
	@Qualifier("personaServiceImpl")
	PersonaInterface personaInterface;

	@Autowired
	@Qualifier("usuarioServiceImpl")
	UsuarioInterface usuarioInterface;

	@Autowired
	@Qualifier("empresaServiceImpl")
	EmpresaInterface empresaInterface;

	@GetMapping({ "/seleccion" })
	public ModelAndView seleccionEmpresa(@ModelAttribute("datosGenerales") DatosGenerales datosGenerales) {
		Set<String> roles;
		ModelAndView mav = new ModelAndView(Constante.SELECCION);
		CrudPersonaModel crudPersonaModel;
		List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
		Collection<Integer> rolesSesion = new ArrayList<Integer>();
		UsuarioModel usuarioModel = new UsuarioModel();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());

		for (String rol : roles) {
			rolesSesion.add(StringUtil.toInteger(rol));
		}

		listMenuModel = menuInterface.obtenerMenuByRol(rolesSesion);
		datosGenerales.setListMenuModel(listMenuModel);
		datosGenerales.setMenu(construirMenu(listMenuModel));

		usuarioModel = usuarioInterface.findUsuarioByUsuario(user.getUsername());

		crudPersonaModel = personaInterface.obtenerEmpresaByPersona(usuarioModel.getIdPersona());

		crudPersonaModel.setUsuario(user.getUsername());

		mav.addObject("crudMenuModel", crudPersonaModel);
		mav.addObject("usuario", user.getUsername());

		return mav;
	}

	@PostMapping(value = "/accionSeleccion", params = { "selectRow", "modo" })
	public ModelAndView selectModo(@ModelAttribute("datosGenerales") DatosGenerales datosGenerales,
			final CrudMenuModel crudMenuModel, final BindingResult bindingResult, final HttpServletRequest req) {
		ModelAndView model;

		if (StringUtil.equiv(req.getParameter("modo"), Constante.FIDEICOMISARIO)) {
			model = new ModelAndView(Constante.MENU);
		} else {
			model = new ModelAndView(Constante.SITIO_EN_CONSTRUCCION);
		}

		datosGenerales.setModo(req.getParameter("modo"));
		datosGenerales.setRucEmpresa(req.getParameter("selectRow"));
		//datosGenerales.setMenu("<i class='user icon'></i> Administración");
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addObject("usuario", user.getUsername());
		return model;
	}

	@ModelAttribute("datosGenerales")
	public DatosGenerales getVisitor() {
		return new DatosGenerales();
	}
}
