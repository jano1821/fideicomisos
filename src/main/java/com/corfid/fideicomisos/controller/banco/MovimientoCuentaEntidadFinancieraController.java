package com.corfid.fideicomisos.controller.banco;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.banco.CuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.model.banco.FideicomisoModel;
import com.corfid.fideicomisos.model.banco.MovimientoCuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.banco.CuentaEntidadFinancieraInterface;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.service.banco.MovimientoCuentaEntidadFinancieraInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("movimientoCuentaEntidadFinanciera")
public class MovimientoCuentaEntidadFinancieraController extends InitialController {

	@Autowired
	@Qualifier("fideicomisoServiceImpl")
	private FideicomisoInterface fideicomisoInterface;

	@Autowired
	@Qualifier("cuentaEntidadFinancieraServiceImpl")
	private CuentaEntidadFinancieraInterface cuentaEntidadFinancieraInterface;

	@Autowired
	@Qualifier("movimientoCuentaEntidadFinancieraServiceImpl")
	private MovimientoCuentaEntidadFinancieraInterface movimientoCuentaEntidadFinancieraInterface;

	@PostMapping(value = "/buscarMovimientoCuenta", params = { "rightRow", "identificadorCuentaEntidadFinanciera",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel,
			@SessionAttribute("datosGenerales") DatosGenerales datosGenerales, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		Integer identificadorCuentaEntidadFinanciera = StringUtil
				.toInteger(request.getParameter("identificadorCuentaEntidadFinanciera"));

		return busqueda(identificadorCuentaEntidadFinanciera, numeroDocumento, Constante.CONST_CERO, Constante.DERECHA,
				paginaActual, paginaFinal, movimientoCuentaEntidadFinancieraModel, cuentaEntidadFinancieraModel);
	}

	@PostMapping(value = "/buscarMovimientoCuenta", params = { "leftRow", "identificadorCuentaEntidadFinanciera",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel,
			@SessionAttribute("datosGenerales") DatosGenerales datosGenerales, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		Integer identificadorCuentaEntidadFinanciera = StringUtil
				.toInteger(request.getParameter("identificadorCuentaEntidadFinanciera"));

		return busqueda(identificadorCuentaEntidadFinanciera, numeroDocumento, Constante.IZQUIERDA,
				Constante.CONST_CERO, paginaActual, paginaFinal, movimientoCuentaEntidadFinancieraModel,
				cuentaEntidadFinancieraModel);
	}

	private ModelAndView busqueda(Integer identificadorCuentaEntidadFinanciera, String numeroDocumento,
			String izquierda, String derecha, String pagina, String fin,
			MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel) {

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_MOVIMIENTOS_CUENTA);

		FideicomisoModel fideicomisoModel = new FideicomisoModel();
		fideicomisoModel = fideicomisoInterface.getFideicomisoModel(identificadorCuentaEntidadFinanciera);

		String nombreFideicomiso = fideicomisoModel.getNombreFideicomiso();

		movimientoCuentaEntidadFinancieraModel = movimientoCuentaEntidadFinancieraInterface
				.getMovimientoCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera,
						paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);

		cuentaEntidadFinancieraModel = cuentaEntidadFinancieraInterface
				.getCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera);

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
		modelAndView.addObject("cuentaEntidadFinancieraModel", cuentaEntidadFinancieraModel);
		modelAndView.addObject("nombreFideicomiso", nombreFideicomiso);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

	@PostMapping("/mostrarDetalleMovimiento")
	public ResponseEntity<?> obtenerDetalleMovimiento(
			@Valid @RequestBody MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			final BindingResult bindingResult, Errors errors) {

		String msgError = null;

		try {
			if (errors.hasErrors()) {
				msgError = errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
						.collect(Collectors.joining(","));
				return ResponseEntity.badRequest().body(msgError);
			}

			movimientoCuentaEntidadFinancieraModel = movimientoCuentaEntidadFinancieraInterface
					.getMovimientoCuentaEntidadFinancieraByIdMovimientoCuentaEntidadFinanciera(
							movimientoCuentaEntidadFinancieraModel.getIdentificadorMovimientoCuentaEntidadFinanciera());

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return ResponseEntity.ok(movimientoCuentaEntidadFinancieraModel);

	}

	@PostMapping(value = "/buscarMovimientoCuenta", params = { "backListaFideicomiso" })
	public String retrocederListaFideicomiso(PosicionBancoModel posicionBancoModel,
			CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel, BindingResult bindingResult) throws Exception {

		String urlOrigen = null;

		String formularioOrigen = cuentaEntidadFinancieraModel.getFormularioOrigen();

		if (StringUtil.equiv(Constante.FORMULARIO_FIDEICOMISO, formularioOrigen))
			urlOrigen = Constante.URL_FIDEICOMISO;
		else if (StringUtil.equiv(Constante.FORMULARIO_FIDEICOMISO_SOLES, formularioOrigen))
			urlOrigen = Constante.URL_FIDEICOMISO_SOLES;
		else
			urlOrigen = Constante.URL_FIDEICOMISO_DOLARES;

		return "redirect:" + urlOrigen;

	}

}
