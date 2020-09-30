package com.corfid.fideicomisos.controller.banco;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;

@Controller
@RequestMapping("/fideicomiso")
public class FideicomisoController extends InitialController {

	@GetMapping("/getListAllFideicomisoFromFideicomisario")
	public ModelAndView getListAllFideicomisoFromFideicomisario(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales) {
		datosGenerales.getRucEmpresa();
		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_FIDEICOMISO);
		
		return modelAndView;
	}

}
