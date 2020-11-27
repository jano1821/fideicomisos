package com.corfid.fideicomisos.controller.flujoprocesodocumento;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.banco.FideicomisoModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.ActividadDetalleDocumentoFideicomisoModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.DetalleDocumentoFideicomisoModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.DocumentoFideicomisoModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.FlujoProcesoDocumentoFideicomisoModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.service.flujoprocesodocumento.ActividadDetalleDocumentoFideicomisoInterface;
import com.corfid.fideicomisos.service.flujoprocesodocumento.DetalleDocumentoFideicomisoInterface;
import com.corfid.fideicomisos.service.flujoprocesodocumento.DocumentoFideicomisoInterface;
import com.corfid.fideicomisos.service.utilities.RestClientEstadosFinancierosInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/detalleContrato")
public class DetalleContratoController extends InitialController {

	@Autowired
	@Qualifier("fideicomisoServiceImpl")
	private FideicomisoInterface fideicomisoInterface;

	@Autowired
	@Qualifier("documentoFideicomisoServiceImpl")
	private DocumentoFideicomisoInterface documentoFideicomisoInterface;

	@Autowired
	@Qualifier("detalleDocumentoFideicomisoServiceImpl")
	private DetalleDocumentoFideicomisoInterface detalleDocumentoFideicomisoInterface;

	@Autowired
	@Qualifier("actividadDetalleDocumentoFideicomisoServiceImpl")
	private ActividadDetalleDocumentoFideicomisoInterface actividadDetalleDocumentoFideicomisoInterface;
	
	@Autowired
    @Qualifier("restClientEstadosFinancierosServiceImpl")
    RestClientEstadosFinancierosInterface restClientEstadosFinancierosInterface;

	@PostMapping(value = "/buscarDetalleContrato", params = { "rightRow", "identificadorFideicomiso", "tipoDocumento",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaDerecha(DocumentoFideicomisoModel documentoFideicomisoConvenioRetribucionModel,
			DocumentoFideicomisoModel documentoFideicomisoContratoFideicomisoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");
		String tipoDocumento = request.getParameter("tipoDocumento");

		Integer identificadorFideicomiso = StringUtil.toInteger(request.getParameter("identificadorFideicomiso"));

		return busqueda(identificadorFideicomiso, tipoDocumento, documentoFideicomisoConvenioRetribucionModel,
				documentoFideicomisoContratoFideicomisoModel, Constante.CONST_CERO, Constante.DERECHA, paginaActual,
				paginaFinal);

	}

	@PostMapping(value = "/buscarDetalleContrato", params = { "leftRow", "identificadorFideicomiso", "tipoDocumento",
			"paginaActual", "paginaFinal" })
	public ModelAndView paginaIzquierda(DocumentoFideicomisoModel documentoFideicomisoConvenioRetribucionModel,
			DocumentoFideicomisoModel documentoFideicomisoContratoFideicomisoModel, final BindingResult bindingResult,
			final HttpServletRequest request) {

		String paginaActual = request.getParameter("paginaActual");
		String paginaFinal = request.getParameter("paginaFinal");
		String tipoDocumento = request.getParameter("tipoDocumento");

		Integer identificadorFideicomiso = StringUtil.toInteger(request.getParameter("identificadorFideicomiso"));

		return busqueda(identificadorFideicomiso, tipoDocumento, documentoFideicomisoConvenioRetribucionModel,
				documentoFideicomisoContratoFideicomisoModel, Constante.IZQUIERDA, Constante.CONST_CERO, paginaActual,
				paginaFinal);

	}

	private ModelAndView busqueda(Integer identificadorFideicomiso, String tipoDocumento,
			DocumentoFideicomisoModel documentoFideicomisoConvenioRetribucionModel,
			DocumentoFideicomisoModel documentoFideicomisoContratoFideicomisoModel, String izquierda, String derecha,
			String pagina, String fin) {

		PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

		ModelAndView modelAndView = new ModelAndView(Constante.URL_LISTA_DETALLE_CONTRATO);

		FideicomisoModel fideicomisoModel = new FideicomisoModel();
		fideicomisoModel = fideicomisoInterface.getFideicomisoByIdFideicomisoModel(identificadorFideicomiso);

		String nombreFideicomiso = fideicomisoModel.getNombreFideicomiso();

		if (StringUtil.equiv(Constante.DOCUMENTO_FIDEICOMISO_CONVENIO_RETRIBUCION, tipoDocumento)) {

			documentoFideicomisoConvenioRetribucionModel = documentoFideicomisoInterface
					.getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(identificadorFideicomiso,
							Constante.DOCUMENTO_FIDEICOMISO_CONVENIO_RETRIBUCION, paginadoModel.getPaginaActual(),
							Constante.PAGINADO_5_ROWS);

			documentoFideicomisoConvenioRetribucionModel.setIdentificadorFideicomiso(identificadorFideicomiso);

			if (paginadoModel.isMovIzquierda()) {
				documentoFideicomisoConvenioRetribucionModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
			} else if (paginadoModel.isMovDerecha()) {
				documentoFideicomisoConvenioRetribucionModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
			} else {
				documentoFideicomisoConvenioRetribucionModel.setResult(Constante.CONST_VACIA);
			}
			documentoFideicomisoConvenioRetribucionModel.setPaginaActual(paginadoModel.getPaginaActual());

			paginadoModel = obtenerMovimientoAndPagina(Constante.PAGINA_INICIAL, Constante.CONST_CERO,
					Constante.CONST_CERO, Constante.CONST_CERO);

			documentoFideicomisoContratoFideicomisoModel = documentoFideicomisoInterface
					.getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(identificadorFideicomiso,
							Constante.DOCUMENTO_FIDEICOMISO_CONTRATO_FIDEICOMISO, paginadoModel.getPaginaActual(),
							Constante.PAGINADO_5_ROWS);

			documentoFideicomisoContratoFideicomisoModel.setIdentificadorFideicomiso(identificadorFideicomiso);

			documentoFideicomisoContratoFideicomisoModel.setResult(Constante.CONST_VACIA);
			documentoFideicomisoContratoFideicomisoModel.setPaginaActual(paginadoModel.getPaginaActual());

		}

		if (StringUtil.equiv(Constante.DOCUMENTO_FIDEICOMISO_CONTRATO_FIDEICOMISO, tipoDocumento)) {

			documentoFideicomisoContratoFideicomisoModel = documentoFideicomisoInterface
					.getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(identificadorFideicomiso,
							Constante.DOCUMENTO_FIDEICOMISO_CONTRATO_FIDEICOMISO, paginadoModel.getPaginaActual(),
							Constante.PAGINADO_5_ROWS);

			documentoFideicomisoContratoFideicomisoModel.setIdentificadorFideicomiso(identificadorFideicomiso);

			if (paginadoModel.isMovIzquierda()) {
				documentoFideicomisoContratoFideicomisoModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_IZQUIERDA);
			} else if (paginadoModel.isMovDerecha()) {
				documentoFideicomisoContratoFideicomisoModel.setResult(Constante.NO_HAY_REGISTROS_A_LA_DERECHA);
			} else {
				documentoFideicomisoContratoFideicomisoModel.setResult(Constante.CONST_VACIA);
			}
			documentoFideicomisoContratoFideicomisoModel.setPaginaActual(paginadoModel.getPaginaActual());

			paginadoModel = obtenerMovimientoAndPagina(Constante.PAGINA_INICIAL, Constante.CONST_CERO,
					Constante.CONST_CERO, Constante.CONST_CERO);

			documentoFideicomisoConvenioRetribucionModel = documentoFideicomisoInterface
					.getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(identificadorFideicomiso,
							Constante.DOCUMENTO_FIDEICOMISO_CONVENIO_RETRIBUCION, paginadoModel.getPaginaActual(),
							Constante.PAGINADO_5_ROWS);

			documentoFideicomisoConvenioRetribucionModel.setIdentificadorFideicomiso(identificadorFideicomiso);

			documentoFideicomisoConvenioRetribucionModel.setResult(Constante.CONST_VACIA);
			documentoFideicomisoConvenioRetribucionModel.setPaginaActual(paginadoModel.getPaginaActual());
		}

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		modelAndView.addObject("documentoFideicomisoConvenioRetribucionModel",
				documentoFideicomisoConvenioRetribucionModel);
		modelAndView.addObject("documentoFideicomisoContratoFideicomisoModel",
				documentoFideicomisoContratoFideicomisoModel);
		modelAndView.addObject("nombreFideicomiso", nombreFideicomiso);
		modelAndView.addObject("usuario", user.getUsername());

		return modelAndView;
	}

    @GetMapping("/buscarDetalleContrato")
    public ResponseEntity<byte[]> descargarPDF(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                               DocumentoFideicomisoModel documentoFideicomisoConvenioRetribucionModel,
                                               DocumentoFideicomisoModel documentoFideicomisoContratoFideicomisoModel,
                                               @RequestParam(name = "id", required = false) int id) throws Exception {

        DocumentoFideicomisoModel documentoFideicomisoModel = new DocumentoFideicomisoModel();

        String nombreArchivo = null;
        String formaAccesoArchivo = null;
        String identificadorDocumentoFideicomiso = null;

        ResponseEntity<byte[]> responseEntity = null;

        byte[] bytes = null;

        try {
            identificadorDocumentoFideicomiso = StringUtil.toStr(id);

            documentoFideicomisoModel = documentoFideicomisoInterface.getDocumentoFideicomisoModel(StringUtil.toInteger(identificadorDocumentoFideicomiso));

            nombreArchivo = documentoFideicomisoModel.getNombreArchivo();
            formaAccesoArchivo = documentoFideicomisoModel.getFormaAccesoArchivo();

            if (StringUtil.equiv(Constante.FORMA_ACCESO_RUTA_DIRECTORIO, formaAccesoArchivo)) {

                String rutaDirectorio = "D:\\FTP\\" + nombreArchivo;

                File archivo = new File(rutaDirectorio);
                FileInputStream fileInputStream = new FileInputStream(archivo);

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                try {
                    for (int readNum; (readNum = fileInputStream.read(buffer)) != -1;) {
                        byteArrayOutputStream.write(buffer, 0, readNum);
                    }
                } catch (IOException ex) {
                    System.out.print(ex.getMessage());
                }

                fileInputStream.close();
                bytes = byteArrayOutputStream.toByteArray();

                documentoFideicomisoInterface.guardarDocumentoPDF(StringUtil.toInteger(identificadorDocumentoFideicomiso),
                                                                  bytes);

            } else if (StringUtil.equiv(Constante.FORMA_ACCESO_BASE_DATOS, formaAccesoArchivo)) {
                bytes = documentoFideicomisoModel.getArchivoFisicoAtachado();
            } else {
                String token = restClientEstadosFinancierosInterface.getObtenerToken();
                bytes = restClientEstadosFinancierosInterface.ObtenerArchivos(token,
                                                                              documentoFideicomisoModel.getRutaUbicacionArchivo(),
                                                                              nombreArchivo);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.add("Content-Disposition", "inline; filename= .. ");
            responseEntity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);

            return responseEntity;

        } catch (Exception e) {
            return responseEntity;
        }
    }

	@PostMapping("/mostrarDetalleDocumentoFideicomiso")
	public ResponseEntity<?> mostrarDetalleDocumentoFideicomiso(
			@Valid @RequestBody DetalleDocumentoFideicomisoModel detalleDocumentoFideicomisoModel,
			final BindingResult bindingResult, Errors errors) {

		String msgError = null;
		Integer identificadorDocumentoFideicomiso = null;

		List<ActividadDetalleDocumentoFideicomisoModel> listActividadDetalleDocumentoFideicomisoModel = new ArrayList<ActividadDetalleDocumentoFideicomisoModel>();

		try {
			if (errors.hasErrors()) {
				msgError = errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
						.collect(Collectors.joining(","));
				return ResponseEntity.badRequest().body(msgError);
			}

			identificadorDocumentoFideicomiso = detalleDocumentoFideicomisoModel.getIdentificadorDocumentoFideicomiso();

			detalleDocumentoFideicomisoModel = detalleDocumentoFideicomisoInterface
					.getListDetalleDocumentoFideicomiso(identificadorDocumentoFideicomiso);

			listActividadDetalleDocumentoFideicomisoModel = actividadDetalleDocumentoFideicomisoInterface
					.getListActividadDetalleDocumentoFideicomisoModel(identificadorDocumentoFideicomiso);

			detalleDocumentoFideicomisoModel.setCadenaActividadDetalleDocumentoFideicomiso(
					actividadDetalleDocumentoFideicomisoInterface.getCadenaActividadDetalleDocumentoFideicomiso(
							listActividadDetalleDocumentoFideicomisoModel));

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return ResponseEntity.ok(detalleDocumentoFideicomisoModel);

	}

	@PostMapping(value = "/buscarDetalleContrato", params = { "backListaContratoFideicomiso" })
	public String retrocederListaFideicomiso(
			FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel, BindingResult bindingResult,
			final HttpServletRequest request) throws Exception {

		String urlOrigen = Constante.URL_CONTRATO;

		return "redirect:" + urlOrigen;

	}

}
