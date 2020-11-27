package com.corfid.fideicomisos.model.flujoprocesodocumento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetalleDocumentoFideicomisoModel {

	private Integer identificadorDetalleDocumentoFideicomiso;
	private Integer identificadorDocumentoFideicomiso;
	private Integer identificadorActividadDetalleDocumentoFideicomiso;
	private String fechaInicioActividad;
	private String fechaFinActividad;
	private String codigoEstadoActividad;
	private String descripcionEstadoActividad;
	private String codigoResponsable;
	private String nombreResponsable;
	private String flagAtencion;
	private Integer ordenVisualizacion;
	private String descripcionActividad;
	private String cadenaActividadDetalleDocumentoFideicomiso;

	public List<DetalleDocumentoFideicomisoModel> rows = new ArrayList<DetalleDocumentoFideicomisoModel>();
	public List<ActividadDetalleDocumentoFideicomisoModel> listActividadDetalleDocumentoFideicomisoModel = new ArrayList<ActividadDetalleDocumentoFideicomisoModel>();

	public Integer getIdentificadorDetalleDocumentoFideicomiso() {
		return identificadorDetalleDocumentoFideicomiso;
	}

	public void setIdentificadorDetalleDocumentoFideicomiso(Integer identificadorDetalleDocumentoFideicomiso) {
		this.identificadorDetalleDocumentoFideicomiso = identificadorDetalleDocumentoFideicomiso;
	}

	public Integer getIdentificadorDocumentoFideicomiso() {
		return identificadorDocumentoFideicomiso;
	}

	public void setIdentificadorDocumentoFideicomiso(Integer identificadorDocumentoFideicomiso) {
		this.identificadorDocumentoFideicomiso = identificadorDocumentoFideicomiso;
	}

	public Integer getIdentificadorActividadDetalleDocumentoFideicomiso() {
		return identificadorActividadDetalleDocumentoFideicomiso;
	}

	public void setIdentificadorActividadDetalleDocumentoFideicomiso(
			Integer identificadorActividadDetalleDocumentoFideicomiso) {
		this.identificadorActividadDetalleDocumentoFideicomiso = identificadorActividadDetalleDocumentoFideicomiso;
	}

	public String getFechaInicioActividad() {
		return fechaInicioActividad;
	}

	public void setFechaInicioActividad(String fechaInicioActividad) {
		this.fechaInicioActividad = fechaInicioActividad;
	}

	public String getFechaFinActividad() {
		return fechaFinActividad;
	}

	public void setFechaFinActividad(String fechaFinActividad) {
		this.fechaFinActividad = fechaFinActividad;
	}

	public String getCodigoEstadoActividad() {
		return codigoEstadoActividad;
	}

	public void setCodigoEstadoActividad(String codigoEstadoActividad) {
		this.codigoEstadoActividad = codigoEstadoActividad;
	}

	public String getDescripcionEstadoActividad() {
		return descripcionEstadoActividad;
	}

	public void setDescripcionEstadoActividad(String descripcionEstadoActividad) {
		this.descripcionEstadoActividad = descripcionEstadoActividad;
	}

	public String getCodigoResponsable() {
		return codigoResponsable;
	}

	public void setCodigoResponsable(String codigoResponsable) {
		this.codigoResponsable = codigoResponsable;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getFlagAtencion() {
		return flagAtencion;
	}

	public void setFlagAtencion(String flagAtencion) {
		this.flagAtencion = flagAtencion;
	}

	public Integer getOrdenVisualizacion() {
		return ordenVisualizacion;
	}

	public void setOrdenVisualizacion(Integer ordenVisualizacion) {
		this.ordenVisualizacion = ordenVisualizacion;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getCadenaActividadDetalleDocumentoFideicomiso() {
		return cadenaActividadDetalleDocumentoFideicomiso;
	}

	public void setCadenaActividadDetalleDocumentoFideicomiso(String cadenaActividadDetalleDocumentoFideicomiso) {
		this.cadenaActividadDetalleDocumentoFideicomiso = cadenaActividadDetalleDocumentoFideicomiso;
	}

	public List<DetalleDocumentoFideicomisoModel> getRows() {
		return rows;
	}

	public void setRows(List<DetalleDocumentoFideicomisoModel> rows) {
		this.rows = rows;
	}

	public List<ActividadDetalleDocumentoFideicomisoModel> getListActividadDetalleDocumentoFideicomisoModel() {
		return listActividadDetalleDocumentoFideicomisoModel;
	}

	public void setListActividadDetalleDocumentoFideicomisoModel(
			List<ActividadDetalleDocumentoFideicomisoModel> listActividadDetalleDocumentoFideicomisoModel) {
		this.listActividadDetalleDocumentoFideicomisoModel = listActividadDetalleDocumentoFideicomisoModel;
	}

}