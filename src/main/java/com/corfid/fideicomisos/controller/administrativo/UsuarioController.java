package com.corfid.fideicomisos.controller.administrativo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.utilities.CatalogoConstraintModel;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends InitialController {
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioInterface usuarioInterface;
	
	@Autowired
	@Qualifier("catalogoConstraintServiceImpl")
	private CatalogoConstraintInterface catalogoConstraintInterface;
	
	@GetMapping("/cancel")
	public String cancel() {

		return "redirect:/menu/showmenu";
	}

	@GetMapping("/lista_usuarios")
	public ModelAndView showListaUsuario() {
		ModelAndView mav = new ModelAndView(Constante.LISTA_USUARIOS);
		
		User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		mav.addObject("usuario",user.getUsername());
		mav.addObject("usuarios", usuarioInterface.listAllUsuarios());

		return mav;
	}
	
	@GetMapping("/usuarioform")
	private String redirectConctactForm(Model model, @RequestParam(name = "id", required = false) Integer id) {
		List<CatalogoConstraintModel> listCatalogoConstraintModelEstadoRegistro;
		List<CatalogoConstraintModel> listCatalogoConstraintModelActividad;
		List<CatalogoConstraintModel> listCatalogoConstraintModelTipoUsuario;
		User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UsuarioModel usuarioModel = new UsuarioModel();
		if (id != 0) {
			usuarioModel = usuarioInterface.findUsuarioByIdModel(id);
		}
		
		listCatalogoConstraintModelEstadoRegistro = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO, Constante.ESTADO_REGISTRO);
		listCatalogoConstraintModelActividad = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO, Constante.INDICADOR_ACTIVIDAD);
		listCatalogoConstraintModelTipoUsuario = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_USUARIO, Constante.TIPO_USUARIO);
		
		model.addAttribute("usuarioModel", usuarioModel);
		model.addAttribute("usuario",user.getUsername());
		model.addAttribute("listActividad",listCatalogoConstraintModelActividad);
		model.addAttribute("listEstado",listCatalogoConstraintModelEstadoRegistro);
		model.addAttribute("listTipoUsuario",listCatalogoConstraintModelTipoUsuario);
		return Constante.FORM_USUARIO;
	}
	
	@PostMapping("/addusuario")
	public String addUsuario(@ModelAttribute(name = "usuarioModel") UsuarioModel usuarioModel, Model model) {
		Date fechaAndHoraActual = getFechaAndHoraActual();
		User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		setParametrosAuditoriaModel(fechaAndHoraActual, user.getUsername(), "192.168.1.1", fechaAndHoraActual, user.getUsername(), "192.168.1.1");
		
		//getParametrosAuditoriaModel();
		if (null != usuarioInterface.addUsuario(usuarioModel, getParametrosAuditoriaModel())) {
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}

		return "redirect:/usuario/lista_usuarios";
	}
	
	@GetMapping("/removeusuario")
	public ModelAndView removeContact(@RequestParam(name = "id", required = true) int id) {
		/*contactService.removeContact(id);*/
		return showListaUsuario();
	}
}
