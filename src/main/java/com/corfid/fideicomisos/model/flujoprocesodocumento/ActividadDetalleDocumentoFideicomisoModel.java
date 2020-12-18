package com.corfid.fideicomisos.model.flujoprocesodocumento;

import java.util.ArrayList;
import java.util.List;

public class ActividadDetalleDocumentoFideicomisoModel {

	private Integer identificadorActividadDetalleDocumentoFideicomiso;
	private String descripcionActividad;
	private String tipoDocumento;
	private Integer nivelServicio;
	private Integer ordenVisualizacion;

	public List<ActividadDetalleDocumentoFideicomisoModel> listActividadDetalleDocumentoFideicomisoModel = new ArrayList<ActividadDetalleDocumentoFideicomisoModel>();

	public Integer getIdentificadorActividadDetalleDocumentoFideicomiso() {
		return identificadorActividadDetalleDocumentoFideicomiso;
	}

	public void setIdentificadorActividadDetalleDocumentoFideicomiso(
			Integer identificadorActividadDetalleDocumentoFideicomiso) {
		this.identificadorActividadDetalleDocumentoFideicomiso = identificadorActividadDetalleDocumentoFideicomiso;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getNivelServicio() {
		return nivelServicio;
	}

	public void setNivelServicio(Integer nivelServicio) {
		this.nivelServicio = nivelServicio;
	}

	public Integer getOrdenVisualizacion() {
		return ordenVisualizacion;
	}

	public void setOrdenVisualizacion(Integer ordenVisualizacion) {
		this.ordenVisualizacion = ordenVisualizacion;
	}

	public List<ActividadDetalleDocumentoFideicomisoModel> getListActividadDetalleDocumentoFideicomisoModel() {
		return listActividadDetalleDocumentoFideicomisoModel;
	}

	public void setListActividadDetalleDocumentoFideicomisoModel(
			List<ActividadDetalleDocumentoFideicomisoModel> listActividadDetalleDocumentoFideicomisoModel) {
		this.listActividadDetalleDocumentoFideicomisoModel = listActividadDetalleDocumentoFideicomisoModel;
	}

}
