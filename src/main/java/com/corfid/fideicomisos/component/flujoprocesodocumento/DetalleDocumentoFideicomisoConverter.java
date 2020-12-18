package com.corfid.fideicomisos.component.flujoprocesodocumento;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.flujoprocesodocumento.DetalleDocumentoFideicomiso;
import com.corfid.fideicomisos.model.flujoprocesodocumento.DetalleDocumentoFideicomisoModel;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("detalleDocumentoFideicomisoConverter")
public class DetalleDocumentoFideicomisoConverter {

	public DetalleDocumentoFideicomisoModel convertToDetalleDocumentoFideicomisoModel(
			DetalleDocumentoFideicomiso detalleDocumentoFideicomiso) {

		DetalleDocumentoFideicomisoModel detalleDocumentoFideicomisoModel = new DetalleDocumentoFideicomisoModel();

		String fechaInicioActividad = null;
		String fechaFinActividad = null;
		
		String nombreResponsable = null;
		
		String strDateFormat = "dd-MM-yyyy HH:mm";
		SimpleDateFormat oSimpleDateFormat = new SimpleDateFormat(strDateFormat);
		
		detalleDocumentoFideicomisoModel.setIdentificadorDetalleDocumentoFideicomiso(
				detalleDocumentoFideicomiso.getIdentificadorDetalleDocumentoFideicomiso());
		detalleDocumentoFideicomisoModel.setIdentificadorDocumentoFideicomiso(
				detalleDocumentoFideicomiso.getDocumentoFideicomiso().getIdentificadorDocumentoFideicomiso());
		detalleDocumentoFideicomisoModel.setIdentificadorActividadDetalleDocumentoFideicomiso(
				detalleDocumentoFideicomiso.getActividadDetalleDocumentoFideicomiso()
						.getIdentificadorActividadDetalleDocumentoFideicomiso());
		
		if(!StringUtil.isEmpty(detalleDocumentoFideicomiso.getFechaInicioActividad())) {
			fechaInicioActividad = oSimpleDateFormat.format(detalleDocumentoFideicomiso.getFechaInicioActividad());
		}else {
			fechaInicioActividad = "";
		}			
		detalleDocumentoFideicomisoModel.setFechaInicioActividad(fechaInicioActividad);
		
		if(!StringUtil.isEmpty(detalleDocumentoFideicomiso.getFechaFinActividad())) {
			fechaFinActividad = oSimpleDateFormat.format(detalleDocumentoFideicomiso.getFechaFinActividad());
		}else {
			fechaFinActividad = "";
		}	
		detalleDocumentoFideicomisoModel.setFechaFinActividad(fechaFinActividad);
				
		detalleDocumentoFideicomisoModel
				.setCodigoEstadoActividad(detalleDocumentoFideicomiso.getCodigoEstadoActividad());
		detalleDocumentoFideicomisoModel
				.setDescripcionEstadoActividad(detalleDocumentoFideicomiso.getDescripcionEstadoActividad());
		detalleDocumentoFideicomisoModel.setCodigoResponsable(detalleDocumentoFideicomiso.getCodigoResponsable());
		
		if(!StringUtil.isEmpty(detalleDocumentoFideicomiso.getNombreResponsable())) {
			nombreResponsable = detalleDocumentoFideicomiso.getNombreResponsable();
		}else {
			nombreResponsable = "";
		}						
		detalleDocumentoFideicomisoModel.setNombreResponsable(nombreResponsable);
		
		detalleDocumentoFideicomisoModel.setFlagAtencion(detalleDocumentoFideicomiso.getFlagAtencion());
		detalleDocumentoFideicomisoModel.setOrdenVisualizacion(detalleDocumentoFideicomiso.getOrdenVisualizacion());
		detalleDocumentoFideicomisoModel.setObservacionActividad(detalleDocumentoFideicomiso.getObservacionActividad());
		detalleDocumentoFideicomisoModel.setDescripcionActividad(
				detalleDocumentoFideicomiso.getActividadDetalleDocumentoFideicomiso().getDescripcionActividad());

		return detalleDocumentoFideicomisoModel;

	}

}
