package com.corfid.fideicomisos.controller.banco;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.banco.PosicionBancoModel;
import com.corfid.fideicomisos.model.cruds.CrudMenuModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;

@Controller
@RequestMapping("/fideicomiso")
public class FideicomisoController extends InitialController {

	@Autowired
	@Qualifier("fideicomisoServiceImpl")
	private FideicomisoInterface fideicomisoInterface;
		
	@GetMapping("/getListFideicomisos")
	public ModelAndView getListFideicomisos(
			@SessionAttribute("datosGenerales") DatosGenerales datosGenerales) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();
		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(Constante.CONST_VACIA, numeroDocumento, Constante.CONST_CERO, Constante.CONST_CERO,
				Constante.PAGINA_INICIAL, Constante.CONST_CERO, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "findRow", "busqueda" })
	public ModelAndView buscarFideicomiso(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.CONST_CERO, Constante.CONST_CERO,
				Constante.PAGINA_INICIAL, Constante.CONST_CERO, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.CONST_CERO, Constante.DERECHA, paginaActual,
				paginaFinal, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual,
				paginaFinal, posicionBancoModel);
	}

	private ModelAndView busqueda(String cadenaBusqueda, String numeroDocumento, String izquierda, String derecha,
			String pagina, String fin, PosicionBancoModel posicionBancoModel) {

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_FIDEICOMISO);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		posicionBancoModel = fideicomisoInterface.getListaFideicomiso(cadenaBusqueda, numeroDocumento,
				paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);

		if (paginadoModel.isMovIzquierda()) {
			posicionBancoModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
		} else if (paginadoModel.isMovDerecha()) {
			posicionBancoModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
		} else {
			posicionBancoModel.setResult(Constante.CONST_VACIA);
		}

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		posicionBancoModel.setBusqueda(cadenaBusqueda);
		posicionBancoModel.setPaginaActual(paginadoModel.getPaginaActual());

		modelAndView.addObject("posicionBancoModel", posicionBancoModel);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

}