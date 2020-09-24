package com.corfid.fideicomisos.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.utilities.Constante;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@GetMapping("/cancel")
	public String cancel() {

		return "redirect:/menu/showmenu";
	}

	@GetMapping("/showmenu")
	public ModelAndView showMenu() {
		ModelAndView mav = new ModelAndView(Constante.MENU);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		mav.addObject("usuario", user.getUsername());

		return mav;

	}
}
