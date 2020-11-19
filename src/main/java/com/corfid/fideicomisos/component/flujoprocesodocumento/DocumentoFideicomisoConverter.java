package com.corfid.fideicomisos.component.flujoprocesodocumento;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.flujoprocesodocumento.DocumentoFideicomiso;
import com.corfid.fideicomisos.model.flujoprocesodocumento.DocumentoFideicomisoModel;

@Component("documentoFideicomisoConverter")
public class DocumentoFideicomisoConverter {

	public DocumentoFideicomisoModel convertToDocumentoFideicomisoModel(DocumentoFideicomiso documentoFideicomiso) {

		DocumentoFideicomisoModel documentoFideicomisoModel = new DocumentoFideicomisoModel();
		
		documentoFideicomisoModel.setIdentificadorDocumentoFideicomiso(documentoFideicomiso.getIdentificadorDocumentoFideicomiso());
		documentoFideicomisoModel.setIdentificadorFideicomiso(documentoFideicomiso.getFideicomiso().getIdentificadorFideicomiso());
		documentoFideicomisoModel.setTipoDocumento(documentoFideicomiso.getTipoDocumento());
		documentoFideicomisoModel.setDescripcionDocumento(documentoFideicomiso.getDescripcionDocumento());
		documentoFideicomisoModel.setCodigoEstado(documentoFideicomiso.getCodigoEstado());
		documentoFideicomisoModel.setDescripcionEstado(documentoFideicomiso.getDescripcionEstado());
		documentoFideicomisoModel.setNombreArchivo(documentoFideicomiso.getNombreArchivo());
		documentoFideicomisoModel.setFormaAccesoArchivo(documentoFideicomiso.getFormaAccesoArchivo());
		documentoFideicomisoModel.setRutaUbicacionArchivo(documentoFideicomiso.getRutaUbicacionArchivo());
		documentoFideicomisoModel.setArchivoFisicoAtachado(documentoFideicomiso.getArchivoFisicoAtachado());
		documentoFideicomisoModel.setIndicadorAdenda(documentoFideicomiso.getIndicadorAdenda());
		documentoFideicomisoModel.setFechaFirmaDocumento(documentoFideicomiso.getFechaFirmaDocumento());
		
		return documentoFideicomisoModel;

	}

}
