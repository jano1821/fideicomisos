package com.corfid.fideicomisos.service.flujoprocesodocumento;

import java.util.List;

import com.corfid.fideicomisos.model.flujoprocesodocumento.ActividadDetalleDocumentoFideicomisoModel;

public interface ActividadDetalleDocumentoFideicomisoInterface {

	public List<ActividadDetalleDocumentoFideicomisoModel> getListActividadDetalleDocumentoFideicomisoModel(
			Integer identificadorDocumentoFideicomiso);

	public String getCadenaActividadDetalleDocumentoFideicomiso(
			List<ActividadDetalleDocumentoFideicomisoModel> listActividadDetalleDocumentoFideicomisoModel);

}
