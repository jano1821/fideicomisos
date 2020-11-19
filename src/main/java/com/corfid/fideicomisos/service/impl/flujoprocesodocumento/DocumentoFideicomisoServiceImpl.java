package com.corfid.fideicomisos.service.impl.flujoprocesodocumento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.flujoprocesodocumento.DocumentoFideicomisoConverter;
import com.corfid.fideicomisos.component.flujoprocesodocumento.FlujoProcesoDocumentoFideicomisoConverter;
import com.corfid.fideicomisos.entity.eeff.EstadosFinancieros;
import com.corfid.fideicomisos.entity.flujoprocesodocumento.DocumentoFideicomiso;
import com.corfid.fideicomisos.model.banco.FideicomisarioModel;
import com.corfid.fideicomisos.model.eeff.EstadosFinancierosModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.DocumentoFideicomisoModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.FlujoProcesoDocumentoFideicomisoModel;
import com.corfid.fideicomisos.repository.banco.FideicomisoRepository;
import com.corfid.fideicomisos.repository.flujoprocesodocumento.DocumentoFideicomisoRepository;
import com.corfid.fideicomisos.service.banco.FideicomisarioInterface;
import com.corfid.fideicomisos.service.flujoprocesodocumento.DocumentoFideicomisoInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.StringUtil;

@Service("documentoFideicomisoServiceImpl")
public class DocumentoFideicomisoServiceImpl extends AbstractService implements DocumentoFideicomisoInterface {

	@Autowired
	@Qualifier("fideicomisoRepository")
	private FideicomisoRepository fideicomisoRepository;

	@Autowired
	@Qualifier("documentoFideicomisoRepository")
	private DocumentoFideicomisoRepository documentoFideicomisoRepository;

	@Autowired
	@Qualifier("fideicomisarioServiceImpl")
	private FideicomisarioInterface fideicomisarioInterface;

	@Autowired
	@Qualifier("documentoFideicomisoConverter")
	private DocumentoFideicomisoConverter documentoFideicomisoConverter;

	@Autowired
	@Qualifier("flujoProcesoDocumentoFideicomisoConverter")
	private FlujoProcesoDocumentoFideicomisoConverter flujoProcesoDocumentoFideicomisoConverter;

	public FlujoProcesoDocumentoFideicomisoModel getListFideicomisoDocumento(String cadenaBusqueda,
			String numeroDocumento, Integer pagina, Integer cantRegistros) {

		FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel = new FlujoProcesoDocumentoFideicomisoModel();

		String cadenaFideicomiso = null;
		String nombreFideicomisario = null;

		Integer identificadorFideicomisario = null;

		FideicomisarioModel fideicomisarioModel = new FideicomisarioModel();

		fideicomisarioModel = fideicomisarioInterface.getFideicomisarioByNumeroDocumento(numeroDocumento);
		identificadorFideicomisario = fideicomisarioModel.getIdentificadorFideicomisario();
		nombreFideicomisario = fideicomisarioModel.getNombreFideicomisario();

		if (StringUtil.isEmpty(cadenaBusqueda)) {
			flujoProcesoDocumentoFideicomisoModel = getListFideicomisoDocumentoByIdFideicomisario(
					identificadorFideicomisario, pagina, cantRegistros);
		} else {
			cadenaFideicomiso = Constante.COMODIN_LIKE + cadenaBusqueda + Constante.COMODIN_LIKE;
			flujoProcesoDocumentoFideicomisoModel = getListFideicomisoDocumentoByIdFideicomisarioNombreFideicomiso(
					identificadorFideicomisario, cadenaFideicomiso, pagina, cantRegistros);
		}

		flujoProcesoDocumentoFideicomisoModel.setNombreFideicomisario(nombreFideicomisario);

		return flujoProcesoDocumentoFideicomisoModel;
	}

	public FlujoProcesoDocumentoFideicomisoModel getListFideicomisoDocumentoByIdFideicomisario(
			Integer identificadorFideicomisario, Integer pagina, Integer cantRegistros) {

		List<Object> listFideicomisoDocumento;
		List<FlujoProcesoDocumentoFideicomisoModel> listFlujoProcesoDocumentoFideicomisoModel;

		Page<Object> pageFideicomisoDocumento;

		FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel = new FlujoProcesoDocumentoFideicomisoModel();

		pageFideicomisoDocumento = fideicomisoRepository.getListFideicomisoDocumentoByIdFideicomisario(
				identificadorFideicomisario, obtenerIndexPorPagina(pagina, cantRegistros, null, false, false));

		flujoProcesoDocumentoFideicomisoModel.setPaginaFinal(pageFideicomisoDocumento.getTotalPages());
		flujoProcesoDocumentoFideicomisoModel
				.setCantidadRegistros(_toInteger(pageFideicomisoDocumento.getTotalElements()));

		listFideicomisoDocumento = pageFideicomisoDocumento.getContent();

		listFlujoProcesoDocumentoFideicomisoModel = new ArrayList<FlujoProcesoDocumentoFideicomisoModel>();

		for (int i = 0; i < listFideicomisoDocumento.size(); i++) {
			Object[] objectFideicomisoDocumento = (Object[]) listFideicomisoDocumento.get(i);
			listFlujoProcesoDocumentoFideicomisoModel.add(flujoProcesoDocumentoFideicomisoConverter
					.convertToFlujoProcesoDocumentoFideicomisoModel(objectFideicomisoDocumento));
		}

		flujoProcesoDocumentoFideicomisoModel.setRows(listFlujoProcesoDocumentoFideicomisoModel);

		return flujoProcesoDocumentoFideicomisoModel;

	}

	public FlujoProcesoDocumentoFideicomisoModel getListFideicomisoDocumentoByIdFideicomisarioNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, Integer pagina, Integer cantRegistros) {

		List<Object> listFideicomisoDocumento;
		List<FlujoProcesoDocumentoFideicomisoModel> listFlujoProcesoDocumentoFideicomisoModel;

		Page<Object> pageFideicomisoDocumento;

		FlujoProcesoDocumentoFideicomisoModel flujoProcesoDocumentoFideicomisoModel = new FlujoProcesoDocumentoFideicomisoModel();

		pageFideicomisoDocumento = fideicomisoRepository.getListFideicomisoDocumentoByIdFideicomisarioNombreFideicomiso(
				identificadorFideicomisario, nombreFideicomiso,
				obtenerIndexPorPagina(pagina, cantRegistros, null, false, false));

		flujoProcesoDocumentoFideicomisoModel.setPaginaFinal(pageFideicomisoDocumento.getTotalPages());
		flujoProcesoDocumentoFideicomisoModel
				.setCantidadRegistros(_toInteger(pageFideicomisoDocumento.getTotalElements()));

		listFideicomisoDocumento = pageFideicomisoDocumento.getContent();

		listFlujoProcesoDocumentoFideicomisoModel = new ArrayList<FlujoProcesoDocumentoFideicomisoModel>();

		for (int i = 0; i < listFideicomisoDocumento.size(); i++) {
			Object[] objectFideicomisoDocumento = (Object[]) listFideicomisoDocumento.get(i);
			listFlujoProcesoDocumentoFideicomisoModel.add(flujoProcesoDocumentoFideicomisoConverter
					.convertToFlujoProcesoDocumentoFideicomisoModel(objectFideicomisoDocumento));
		}

		flujoProcesoDocumentoFideicomisoModel.setRows(listFlujoProcesoDocumentoFideicomisoModel);

		return flujoProcesoDocumentoFideicomisoModel;
	}

	public DocumentoFideicomisoModel getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(
			Integer identificadorFideicomiso, String tipoDocumento, Integer pagina, Integer cantRegistros) {

		Page<DocumentoFideicomiso> pageDocumentoFideicomiso;

		List<DocumentoFideicomiso> listDocumentoFideicomiso;
		List<DocumentoFideicomisoModel> listDocumentoFideicomisoModel;

		DocumentoFideicomisoModel documentoFideicomisoModel = new DocumentoFideicomisoModel();

		pageDocumentoFideicomiso = documentoFideicomisoRepository
				.getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(identificadorFideicomiso, tipoDocumento,
						obtenerIndexPorPagina(pagina, cantRegistros, null, false, false));

		documentoFideicomisoModel.setPaginaFinal(pageDocumentoFideicomiso.getTotalPages());
		documentoFideicomisoModel.setCantidadRegistros(_toInteger(pageDocumentoFideicomiso.getTotalElements()));

		listDocumentoFideicomiso = pageDocumentoFideicomiso.getContent();

		listDocumentoFideicomisoModel = new ArrayList<DocumentoFideicomisoModel>();

		for (DocumentoFideicomiso documentoFideicomiso : listDocumentoFideicomiso) {
			listDocumentoFideicomisoModel
					.add(documentoFideicomisoConverter.convertToDocumentoFideicomisoModel(documentoFideicomiso));
		}

		documentoFideicomisoModel.setRows(listDocumentoFideicomisoModel);

		return documentoFideicomisoModel;
	}

	public DocumentoFideicomisoModel getDocumentoFideicomisoModel(Integer identificadorDocumentoFideicomiso) {

		DocumentoFideicomiso documentoFideicomiso = new DocumentoFideicomiso();
		DocumentoFideicomisoModel documentoFideicomisoModel;

		documentoFideicomiso = documentoFideicomisoRepository
				.findByIdentificadorDocumentoFideicomiso(identificadorDocumentoFideicomiso);

		documentoFideicomisoModel = documentoFideicomisoConverter
				.convertToDocumentoFideicomisoModel(documentoFideicomiso);

		return documentoFideicomisoModel;

	}

	public void guardarDocumentoPDF(Integer identificadorDocumentoFideicomiso, byte[] filebyte) throws Exception {

		DocumentoFideicomiso documentoFideicomiso = new DocumentoFideicomiso();

		documentoFideicomiso = documentoFideicomisoRepository
				.findByIdentificadorDocumentoFideicomiso(identificadorDocumentoFideicomiso);

		documentoFideicomiso.setArchivoFisicoAtachado(filebyte);
		documentoFideicomisoRepository.save(documentoFideicomiso);
	}

}
