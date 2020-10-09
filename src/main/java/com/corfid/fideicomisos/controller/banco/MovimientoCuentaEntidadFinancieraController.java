package com.corfid.fideicomisos.controller.banco;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.banco.MovimientoCuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.banco.FideicomisarioInterface;
import com.corfid.fideicomisos.service.banco.MovimientoCuentaEntidadFinancieraInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("movimientoCuentaEntidadFinanciera")
public class MovimientoCuentaEntidadFinancieraController extends InitialController {

	@Autowired
	@Qualifier("fideicomisarioServiceImpl")
	private FideicomisarioInterface fideicomisarioInterface;

	@Autowired
	@Qualifier("movimientoCuentaEntidadFinancieraServiceImpl")
	private MovimientoCuentaEntidadFinancieraInterface movimientoCuentaEntidadFinancieraInterface;

	@PostMapping(value = "/buscarMovimientoCuenta", params = { "rightRow", "identificadorCuentaEntidadFinanciera",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			@SessionAttribute("datosGenerales") DatosGenerales datosGenerales, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		Integer identificadorCuentaEntidadFinanciera = StringUtil
				.toInteger(request.getParameter("identificadorCuentaEntidadFinanciera"));

		return busqueda(identificadorCuentaEntidadFinanciera, numeroDocumento, Constante.CONST_CERO, Constante.DERECHA,
				paginaActual, paginaFinal, movimientoCuentaEntidadFinancieraModel);
	}

	@PostMapping(value = "/buscarMovimientoCuenta", params = { "leftRow", "identificadorCuentaEntidadFinanciera",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			@SessionAttribute("datosGenerales") DatosGenerales datosGenerales, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		Integer identificadorCuentaEntidadFinanciera = StringUtil
				.toInteger(request.getParameter("identificadorCuentaEntidadFinanciera"));

		return busqueda(identificadorCuentaEntidadFinanciera, numeroDocumento, Constante.IZQUIERDA,
				Constante.CONST_CERO, paginaActual, paginaFinal, movimientoCuentaEntidadFinancieraModel);
	}

	private ModelAndView busqueda(Integer identificadorCuentaEntidadFinanciera, String numeroDocumento,
			String izquierda, String derecha, String pagina, String fin,
			MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel) {

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_MOVIMIENTOS_CUENTA);

		String nombreFideicomisario = fideicomisarioInterface.getFideicomisarioByNumeroDocumento(numeroDocumento)
				.getNombreFideicomisario();

		movimientoCuentaEntidadFinancieraModel = movimientoCuentaEntidadFinancieraInterface
				.getMovimientoCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera,
						paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);

		if (paginadoModel.isMovIzquierda()) {
			movimientoCuentaEntidadFinancieraModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
		} else if (paginadoModel.isMovDerecha()) {
			movimientoCuentaEntidadFinancieraModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
		} else {
			movimientoCuentaEntidadFinancieraModel.setResult(Constante.CONST_VACIA);
		}

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		movimientoCuentaEntidadFinancieraModel
				.setIdentificadorCuentaEntidadFinanciera(identificadorCuentaEntidadFinanciera);
		movimientoCuentaEntidadFinancieraModel.setPaginaActual(paginadoModel.getPaginaActual());

		modelAndView.addObject("movimientoCuentaEntidadFinancieraModel", movimientoCuentaEntidadFinancieraModel);
		modelAndView.addObject("nombreFideicomisario", nombreFideicomisario);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

}
