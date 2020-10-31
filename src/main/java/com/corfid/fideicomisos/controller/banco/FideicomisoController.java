package com.corfid.fideicomisos.controller.banco;

import java.util.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.banco.CuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.model.banco.FideicomisoModel;
import com.corfid.fideicomisos.model.banco.MovimientoCuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.model.banco.PosicionBancoModel;
import com.corfid.fideicomisos.model.banco.SaldoTotalMonedaModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.banco.CuentaEntidadFinancieraInterface;
import com.corfid.fideicomisos.service.banco.FideicomisarioInterface;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.service.banco.MovimientoCuentaEntidadFinancieraInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/fideicomiso")
public class FideicomisoController extends InitialController {

	@Autowired
	@Qualifier("fideicomisoServiceImpl")
	private FideicomisoInterface fideicomisoInterface;

	@Autowired
	@Qualifier("fideicomisarioServiceImpl")
	private FideicomisarioInterface fideicomisarioInterface;

	@Autowired
	@Qualifier("movimientoCuentaEntidadFinancieraServiceImpl")
	private MovimientoCuentaEntidadFinancieraInterface movimientoCuentaEntidadFinancieraInterface;

	@Autowired
	@Qualifier("cuentaEntidadFinancieraServiceImpl")
	private CuentaEntidadFinancieraInterface cuentaEntidadFinancieraInterface;

	@GetMapping("/getListFideicomisos")
	public ModelAndView getListFideicomisos(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();
		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(Constante.CONST_VACIA, numeroDocumento, Constante.CONST_VACIA, Constante.CONST_CERO,
				Constante.CONST_CERO, Constante.PAGINA_INICIAL, Constante.CONST_CERO, posicionBancoModel);
	}

	@GetMapping("/getListFideicomisosSoles")
	public ModelAndView getListFideicomisosSoles(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();
		String numeroDocumento = datosGenerales.getRucEmpresa();
		String codigoMoneda = Constante.CODIGO_MONEDA_SOLES;

		return busqueda(Constante.CONST_VACIA, numeroDocumento, codigoMoneda, Constante.CONST_CERO,
				Constante.CONST_CERO, Constante.PAGINA_INICIAL, Constante.CONST_CERO, posicionBancoModel);
	}

	@GetMapping("/getListFideicomisosDolares")
	public ModelAndView getListFideicomisosDolares(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales) {

		PosicionBancoModel posicionBancoModel = new PosicionBancoModel();
		String numeroDocumento = datosGenerales.getRucEmpresa();
		String codigoMoneda = Constante.CODIGO_MONEDA_DOLARES;

		return busqueda(Constante.CONST_VACIA, numeroDocumento, codigoMoneda, Constante.CONST_CERO,
				Constante.CONST_CERO, Constante.PAGINA_INICIAL, Constante.CONST_CERO, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "findRow", "busqueda" })
	public ModelAndView buscarFideicomiso(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.CONST_VACIA, Constante.CONST_CERO,
				Constante.CONST_CERO, Constante.PAGINA_INICIAL, Constante.CONST_CERO, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.CONST_VACIA, Constante.CONST_CERO, Constante.DERECHA,
				paginaActual, paginaFinal, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.CONST_VACIA, Constante.IZQUIERDA,
				Constante.CONST_CERO, paginaActual, paginaFinal, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomisoSoles", params = { "findRow", "busqueda" })
	public ModelAndView buscarFideicomisoSoles(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String numeroDocumento = datosGenerales.getRucEmpresa();
		String codigoMoneda = Constante.CODIGO_MONEDA_SOLES;

		return busqueda(cadenaBusqueda, numeroDocumento, codigoMoneda, Constante.CONST_CERO, Constante.CONST_CERO,
				Constante.PAGINA_INICIAL, Constante.CONST_CERO, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomisoSoles", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaDerechaS(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();
		String codigoMoneda = Constante.CODIGO_MONEDA_SOLES;

		return busqueda(cadenaBusqueda, numeroDocumento, codigoMoneda, Constante.CONST_CERO, Constante.DERECHA,
				paginaActual, paginaFinal, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomisoSoles", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierdaS(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();
		String codigoMoneda = Constante.CODIGO_MONEDA_SOLES;

		return busqueda(cadenaBusqueda, numeroDocumento, codigoMoneda, Constante.IZQUIERDA, Constante.CONST_CERO,
				paginaActual, paginaFinal, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomisoDolares", params = { "findRow", "busqueda" })
	public ModelAndView buscarFideicomisoDolares(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String numeroDocumento = datosGenerales.getRucEmpresa();
		String codigoMoneda = Constante.CODIGO_MONEDA_DOLARES;

		return busqueda(cadenaBusqueda, numeroDocumento, codigoMoneda, Constante.CONST_CERO, Constante.CONST_CERO,
				Constante.PAGINA_INICIAL, Constante.CONST_CERO, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomisoDolares", params = { "rightRow", "busqueda", "paginaActual",
			"paginaFinal" })
	public ModelAndView paginaDerechaD(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();
		String codigoMoneda = Constante.CODIGO_MONEDA_DOLARES;

		return busqueda(cadenaBusqueda, numeroDocumento, codigoMoneda, Constante.CONST_CERO, Constante.DERECHA,
				paginaActual, paginaFinal, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomisoDolares", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierdaD(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			PosicionBancoModel posicionBancoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();
		String codigoMoneda = Constante.CODIGO_MONEDA_DOLARES;

		return busqueda(cadenaBusqueda, numeroDocumento, codigoMoneda, Constante.IZQUIERDA, Constante.CONST_CERO,
				paginaActual, paginaFinal, posicionBancoModel);
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "detailRow" })
	public ModelAndView visualizarMovimientoCuenta(
			MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String nombreFideicomiso = null;

		FideicomisoModel fideicomisoModel = new FideicomisoModel();

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_MOVIMIENTOS_CUENTA);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(Constante.PAGINA_INICIAL, Constante.CONST_CERO,
				Constante.CONST_CERO, Constante.CONST_CERO);

		Integer identificadorCuentaEntidadFinanciera = StringUtil.toInteger(request.getParameter("detailRow"));

		fideicomisoModel = fideicomisoInterface.getFideicomisoModel(identificadorCuentaEntidadFinanciera);

		nombreFideicomiso = fideicomisoModel.getNombreFideicomiso();

		movimientoCuentaEntidadFinancieraModel = movimientoCuentaEntidadFinancieraInterface
				.getMovimientoCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera,
						paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);

		cuentaEntidadFinancieraModel = cuentaEntidadFinancieraInterface
				.getCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera);

		cuentaEntidadFinancieraModel.setFormularioOrigen(Constante.FORMULARIO_FIDEICOMISO);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		movimientoCuentaEntidadFinancieraModel
				.setIdentificadorCuentaEntidadFinanciera(identificadorCuentaEntidadFinanciera);
		movimientoCuentaEntidadFinancieraModel.setResult(Constante.CONST_VACIA);
		movimientoCuentaEntidadFinancieraModel.setPaginaActual(paginadoModel.getPaginaActual());

		modelAndView.addObject("movimientoCuentaEntidadFinancieraModel", movimientoCuentaEntidadFinancieraModel);
		modelAndView.addObject("cuentaEntidadFinancieraModel", cuentaEntidadFinancieraModel);
		modelAndView.addObject("nombreFideicomiso", nombreFideicomiso);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

	@PostMapping(value = "/buscarFideicomisoSoles", params = { "detailRow" })
	public ModelAndView visualizarMovimientoCuentaS(
			MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String nombreFideicomiso = null;

		FideicomisoModel fideicomisoModel = new FideicomisoModel();

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_MOVIMIENTOS_CUENTA);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(Constante.PAGINA_INICIAL, Constante.CONST_CERO,
				Constante.CONST_CERO, Constante.CONST_CERO);

		Integer identificadorCuentaEntidadFinanciera = StringUtil.toInteger(request.getParameter("detailRow"));

		fideicomisoModel = fideicomisoInterface.getFideicomisoModel(identificadorCuentaEntidadFinanciera);

		nombreFideicomiso = fideicomisoModel.getNombreFideicomiso();

		movimientoCuentaEntidadFinancieraModel = movimientoCuentaEntidadFinancieraInterface
				.getMovimientoCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera,
						paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);

		cuentaEntidadFinancieraModel = cuentaEntidadFinancieraInterface
				.getCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera);

		cuentaEntidadFinancieraModel.setFormularioOrigen(Constante.FORMULARIO_FIDEICOMISO_SOLES);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		movimientoCuentaEntidadFinancieraModel
				.setIdentificadorCuentaEntidadFinanciera(identificadorCuentaEntidadFinanciera);
		movimientoCuentaEntidadFinancieraModel.setResult(Constante.CONST_VACIA);
		movimientoCuentaEntidadFinancieraModel.setPaginaActual(paginadoModel.getPaginaActual());

		modelAndView.addObject("movimientoCuentaEntidadFinancieraModel", movimientoCuentaEntidadFinancieraModel);
		modelAndView.addObject("cuentaEntidadFinancieraModel", cuentaEntidadFinancieraModel);
		modelAndView.addObject("nombreFideicomiso", nombreFideicomiso);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

	@PostMapping(value = "/buscarFideicomisoDolares", params = { "detailRow" })
	public ModelAndView visualizarMovimientoCuentaD(
			MovimientoCuentaEntidadFinancieraModel movimientoCuentaEntidadFinancieraModel,
			CuentaEntidadFinancieraModel cuentaEntidadFinancieraModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String nombreFideicomiso = null;

		FideicomisoModel fideicomisoModel = new FideicomisoModel();

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_MOVIMIENTOS_CUENTA);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(Constante.PAGINA_INICIAL, Constante.CONST_CERO,
				Constante.CONST_CERO, Constante.CONST_CERO);

		Integer identificadorCuentaEntidadFinanciera = StringUtil.toInteger(request.getParameter("detailRow"));

		fideicomisoModel = fideicomisoInterface.getFideicomisoModel(identificadorCuentaEntidadFinanciera);

		nombreFideicomiso = fideicomisoModel.getNombreFideicomiso();

		movimientoCuentaEntidadFinancieraModel = movimientoCuentaEntidadFinancieraInterface
				.getMovimientoCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera,
						paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);

		cuentaEntidadFinancieraModel = cuentaEntidadFinancieraInterface
				.getCuentaEntidadFinancieraByIdCuenta(identificadorCuentaEntidadFinanciera);

		cuentaEntidadFinancieraModel.setFormularioOrigen(Constante.FORMULARIO_FIDEICOMISO_DOLARES);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		movimientoCuentaEntidadFinancieraModel
				.setIdentificadorCuentaEntidadFinanciera(identificadorCuentaEntidadFinanciera);
		movimientoCuentaEntidadFinancieraModel.setResult(Constante.CONST_VACIA);
		movimientoCuentaEntidadFinancieraModel.setPaginaActual(paginadoModel.getPaginaActual());

		modelAndView.addObject("movimientoCuentaEntidadFinancieraModel", movimientoCuentaEntidadFinancieraModel);
		modelAndView.addObject("cuentaEntidadFinancieraModel", cuentaEntidadFinancieraModel);
		modelAndView.addObject("nombreFideicomiso", nombreFideicomiso);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

	@PostMapping("/obtenerSaldoTotalMoneda")
	public ResponseEntity<?> obtenerSaldoTotalMoneda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			@Valid @RequestBody SaldoTotalMonedaModel saldoTotalMonedaModel, final BindingResult bindingResult,
			final HttpServletRequest request, Errors errors) {

		String numeroDocumento = datosGenerales.getRucEmpresa();

		String msgError = null;

		Date fechaProceso = new Date();

		try {
			if (errors.hasErrors()) {
				msgError = errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
						.collect(Collectors.joining(","));
				return ResponseEntity.badRequest().body(msgError);
			}

			saldoTotalMonedaModel = fideicomisoInterface.obtenerSaldoTotalMonedaFideicomiso(
					saldoTotalMonedaModel.getBusqueda(), numeroDocumento, Constante.CONST_VACIA, fechaProceso);

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return ResponseEntity.ok(saldoTotalMonedaModel);

	}

	@PostMapping("/obtenerSaldoTotalMonedaSoles")
	public ResponseEntity<?> obtenerSaldoTotalMonedaSoles(
			@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			@Valid @RequestBody SaldoTotalMonedaModel saldoTotalMonedaModel, final BindingResult bindingResult,
			final HttpServletRequest request, Errors errors) {

		String numeroDocumento = datosGenerales.getRucEmpresa();

		String msgError = null;

		Date fechaProceso = new Date();

		try {
			if (errors.hasErrors()) {
				msgError = errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
						.collect(Collectors.joining(","));
				return ResponseEntity.badRequest().body(msgError);
			}

			saldoTotalMonedaModel = fideicomisoInterface.obtenerSaldoTotalMonedaFideicomiso(
					saldoTotalMonedaModel.getBusqueda(), numeroDocumento, Constante.CODIGO_MONEDA_SOLES, fechaProceso);

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return ResponseEntity.ok(saldoTotalMonedaModel);

	}

	@PostMapping("/obtenerSaldoTotalMonedaDolares")
	public ResponseEntity<?> obtenerSaldoTotalMonedaDolares(
			@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			@Valid @RequestBody SaldoTotalMonedaModel saldoTotalMonedaModel, final BindingResult bindingResult,
			final HttpServletRequest request, Errors errors) {

		String numeroDocumento = datosGenerales.getRucEmpresa();

		String msgError = null;

		Date fechaProceso = new Date();

		try {
			if (errors.hasErrors()) {
				msgError = errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
						.collect(Collectors.joining(","));
				return ResponseEntity.badRequest().body(msgError);
			}

			saldoTotalMonedaModel = fideicomisoInterface.obtenerSaldoTotalMonedaFideicomiso(
					saldoTotalMonedaModel.getBusqueda(), numeroDocumento, Constante.CODIGO_MONEDA_DOLARES,
					fechaProceso);

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return ResponseEntity.ok(saldoTotalMonedaModel);

	}

	private ModelAndView busqueda(String cadenaBusqueda, String numeroDocumento, String codigoMoneda, String izquierda,
			String derecha, String pagina, String fin, PosicionBancoModel posicionBancoModel) {

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		ModelAndView modelAndView = null;

		if (StringUtil.isEmpty(codigoMoneda)) {
			modelAndView = new ModelAndView(Constante.URL_LISTA_FIDEICOMISO);
		} else if (StringUtil.equiv(codigoMoneda, Constante.CODIGO_MONEDA_SOLES)) {
			modelAndView = new ModelAndView(Constante.URL_LISTA_FIDEICOMISO_SOLES);
		} else {
			modelAndView = new ModelAndView(Constante.URL_LISTA_FIDEICOMISO_DOLARES);
		}

		posicionBancoModel = fideicomisoInterface.getListaFideicomiso(cadenaBusqueda, numeroDocumento, codigoMoneda,
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

		modelAndView.addObject("nombreFideicomisario", posicionBancoModel.getNombreFideicomisario());
		modelAndView.addObject("posicionBancoModel", posicionBancoModel);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

}