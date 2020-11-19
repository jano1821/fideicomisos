package com.corfid.fideicomisos.controller.flujoprocesodocumento;

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

import com.corfid.fideicomisos.model.banco.CuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.model.banco.FideicomisoModel;
import com.corfid.fideicomisos.model.banco.MovimientoCuentaEntidadFinancieraModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.DocumentoFideicomisoModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.FlujoProcesoDocumentoFideicomisoModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.service.flujoprocesodocumento.DocumentoFideicomisoInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/contrato")
public class ContratoController extends InitialController {

	@Autowired
	@Qualifier("fideicomisoServiceImpl")
	private FideicomisoInterface fideicomisoInterface;

	@Autowired
	@Qualifier("documentoFideicomisoServiceImpl")
	private DocumentoFideicomisoInterface documentoFideicomisoInterface;

	@GetMapping("/getListContratos")
	public ModelAndView getListContratos(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales) {

		FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel = new FlujoProcesoDocumentoFideicomisoModel();

		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(Constante.CONST_VACIA, numeroDocumento, Constante.CONST_CERO, Constante.CONST_CERO,
				Constante.PAGINA_INICIAL, Constante.CONST_CERO, flujoProcesoDocumentoFideicomisoModel);

	}

	@PostMapping(value = "/buscarFideicomiso", params = { "findRow", "busqueda" })
	public ModelAndView buscarFideicomiso(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel,
			final BindingResult bindingResult, final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.CONST_CERO, Constante.CONST_CERO,
				Constante.PAGINA_INICIAL, Constante.CONST_CERO, flujoProcesoDocumentoFideicomisoModel);

	}

	@PostMapping(value = "/buscarFideicomiso", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel,
			final BindingResult bindingResult, final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.CONST_CERO, Constante.DERECHA, paginaActual,
				paginaFinal, flujoProcesoDocumentoFideicomisoModel);
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
			FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel,
			final BindingResult bindingResult, final HttpServletRequest request) {

		String cadenaBusqueda = request.getParameter("busqueda");
		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");

		String numeroDocumento = datosGenerales.getRucEmpresa();

		return busqueda(cadenaBusqueda, numeroDocumento, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual,
				paginaFinal, flujoProcesoDocumentoFideicomisoModel);
	}

	private ModelAndView busqueda(String cadenaBusqueda, String numeroDocumento, String izquierda, String derecha,
			String pagina, String fin, FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel) {

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_CONTRATO);

		flujoProcesoDocumentoFideicomisoModel = documentoFideicomisoInterface.getListFideicomisoDocumento(
				cadenaBusqueda, numeroDocumento, paginadoModel.getPaginaActual(), Constante.PAGINADO_5_ROWS);

		if (paginadoModel.isMovIzquierda()) {
			flujoProcesoDocumentoFideicomisoModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
		} else if (paginadoModel.isMovDerecha()) {
			flujoProcesoDocumentoFideicomisoModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
		} else {
			flujoProcesoDocumentoFideicomisoModel.setResult(Constante.CONST_VACIA);
		}

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		flujoProcesoDocumentoFideicomisoModel.setBusqueda(cadenaBusqueda);
		flujoProcesoDocumentoFideicomisoModel.setPaginaActual(paginadoModel.getPaginaActual());

		modelAndView.addObject("nombreFideicomisario", flujoProcesoDocumentoFideicomisoModel.getNombreFideicomisario());
		modelAndView.addObject("flujoProcesoDocumentoFideicomisoModel", flujoProcesoDocumentoFideicomisoModel);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

	@PostMapping(value = "/buscarFideicomiso", params = { "detailRow" })
	public ModelAndView visualizarDetalleContratos(
			DocumentoFideicomisoModel documentoFideicomisoConvenioRetribucionModel,
			DocumentoFideicomisoModel documentoFideicomisoContratoFideicomisoModel, 
			final BindingResult bindingResult,
			final HttpServletRequest request) {

		String nombreFideicomiso = null;

		FideicomisoModel fideicomisoModel = new FideicomisoModel();

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_DETALLE_CONTRATO);

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(Constante.PAGINA_INICIAL, Constante.CONST_CERO,
				Constante.CONST_CERO, Constante.CONST_CERO);

		Integer identificadorFideicomiso = StringUtil.toInteger(request.getParameter("detailRow"));

		fideicomisoModel = fideicomisoInterface.getFideicomisoByIdFideicomisoModel(identificadorFideicomiso);

		nombreFideicomiso = fideicomisoModel.getNombreFideicomiso();

		documentoFideicomisoConvenioRetribucionModel = documentoFideicomisoInterface
				.getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(identificadorFideicomiso,
						Constante.DOCUMENTO_FIDEICOMISO_CONVENIO_RETRIBUCION, paginadoModel.getPaginaActual(),
						Constante.PAGINADO_5_ROWS);

		documentoFideicomisoConvenioRetribucionModel.setIdentificadorFideicomiso(identificadorFideicomiso);
		documentoFideicomisoConvenioRetribucionModel.setResult(Constante.CONST_VACIA);
		documentoFideicomisoConvenioRetribucionModel.setPaginaActual(paginadoModel.getPaginaActual());

		documentoFideicomisoContratoFideicomisoModel = documentoFideicomisoInterface
				.getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(identificadorFideicomiso,
						Constante.DOCUMENTO_FIDEICOMISO_CONTRATO_FIDEICOMISO, paginadoModel.getPaginaActual(),
						Constante.PAGINADO_5_ROWS);

		documentoFideicomisoContratoFideicomisoModel.setIdentificadorFideicomiso(identificadorFideicomiso);
		documentoFideicomisoContratoFideicomisoModel.setResult(Constante.CONST_VACIA);
		documentoFideicomisoContratoFideicomisoModel.setPaginaActual(paginadoModel.getPaginaActual());

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		modelAndView.addObject("documentoFideicomisoConvenioRetribucionModel",
				documentoFideicomisoConvenioRetribucionModel);
		modelAndView.addObject("documentoFideicomisoContratoFideicomisoModel",
				documentoFideicomisoContratoFideicomisoModel);
		modelAndView.addObject("nombreFideicomiso", nombreFideicomiso);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

}
