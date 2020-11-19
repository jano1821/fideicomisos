package com.corfid.fideicomisos.model.flujoprocesodocumento;

public class ActividadDetalleDocumentoFideicomisoModel {

	private Integer identificadorActividadDetalleDocumentoFideicomiso;

	private String descripcionActividad;

	private String tipoDocumento;

	private Integer nivelServicio;

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

}
