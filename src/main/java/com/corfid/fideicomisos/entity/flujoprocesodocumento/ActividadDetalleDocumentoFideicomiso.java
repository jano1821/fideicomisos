package com.corfid.fideicomisos.entity.flujoprocesodocumento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "flumacdf")
public class ActividadDetalleDocumentoFideicomiso extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idacdf", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorActividadDetalleDocumentoFideicomiso;

	@Column(name = "c_desact", nullable = false, insertable = true, updatable = true, length = 100)
	private String descripcionActividad;

	@Column(name = "c_tipdoc", nullable = false, insertable = true, updatable = true, length = 2)
	private String tipoDocumento;

	@Column(name = "n_nivser", nullable = false, insertable = true, updatable = true, precision = 2)
	private Integer nivelServicio;
	
	@Column(name = "n_ordvis", nullable = true, insertable = true, updatable = true, precision = 11, scale = 0)
    private Integer ordenVisualizacion;

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

	public ActividadDetalleDocumentoFideicomiso(Integer identificadorActividadDetalleDocumentoFideicomiso,
			String descripcionActividad, String tipoDocumento, Integer nivelServicio) {
		super();
		this.identificadorActividadDetalleDocumentoFideicomiso = identificadorActividadDetalleDocumentoFideicomiso;
		this.descripcionActividad = descripcionActividad;
		this.tipoDocumento = tipoDocumento;
		this.nivelServicio = nivelServicio;
	}

	public ActividadDetalleDocumentoFideicomiso() {
	}

}
