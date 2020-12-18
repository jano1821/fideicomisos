package com.corfid.fideicomisos.component.flujoprocesodocumento;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.flujoprocesodocumento.ActividadDetalleDocumentoFideicomiso;
import com.corfid.fideicomisos.model.flujoprocesodocumento.ActividadDetalleDocumentoFideicomisoModel;

@Component("actividadDetalleDocumentoFideicomisoConverter")
public class ActividadDetalleDocumentoFideicomisoConverter {

	public ActividadDetalleDocumentoFideicomisoModel convertToActividadDetalleDocumentoFideicomisoModel(
			ActividadDetalleDocumentoFideicomiso actividadDetalleDocumentoFideicomiso) {
		
		ActividadDetalleDocumentoFideicomisoModel actividadDetalleDocumentoFideicomisoModel = new ActividadDetalleDocumentoFideicomisoModel();
	
		actividadDetalleDocumentoFideicomisoModel.setIdentificadorActividadDetalleDocumentoFideicomiso(actividadDetalleDocumentoFideicomiso.getIdentificadorActividadDetalleDocumentoFideicomiso());
		actividadDetalleDocumentoFideicomisoModel.setTipoDocumento(actividadDetalleDocumentoFideicomiso.getTipoDocumento());
		actividadDetalleDocumentoFideicomisoModel.setDescripcionActividad(actividadDetalleDocumentoFideicomiso.getDescripcionActividad());
		actividadDetalleDocumentoFideicomisoModel.setNivelServicio(actividadDetalleDocumentoFideicomiso.getNivelServicio());
		actividadDetalleDocumentoFideicomisoModel.setOrdenVisualizacion(actividadDetalleDocumentoFideicomiso.getOrdenVisualizacion());
		
		return actividadDetalleDocumentoFideicomisoModel;
	
	}

}
