package com.corfid.fideicomisos.service.flujoprocesodocumento;

import com.corfid.fideicomisos.model.flujoprocesodocumento.DocumentoFideicomisoModel;
import com.corfid.fideicomisos.model.flujoprocesodocumento.FlujoProcesoDocumentoFideicomisoModel;

public interface DocumentoFideicomisoInterface {

	public FlujoProcesoDocumentoFideicomisoModel getListFideicomisoDocumento(String cadenaBusqueda,
			String numeroDocumento, Integer pagina, Integer cantRegistros);

	public FlujoProcesoDocumentoFideicomisoModel getListFideicomisoDocumentoByIdFideicomisario(
			Integer identificadorFideicomisario, Integer pagina, Integer cantRegistros);

	public FlujoProcesoDocumentoFideicomisoModel getListFideicomisoDocumentoByIdFideicomisarioNombreFideicomiso(
			Integer identificadorFideicomisario, String nombreFideicomiso, Integer pagina, Integer cantRegistros);

	public DocumentoFideicomisoModel getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(
			Integer identificadorFideicomiso, String tipoDocumento, Integer pagina, Integer cantRegistros);

	public DocumentoFideicomisoModel getDocumentoFideicomisoModel(Integer identificadorDocumentoFideicomiso);

	public void guardarDocumentoPDF(Integer identificadorDocumentoFideicomiso, byte[] filebyte) throws Exception;

}
