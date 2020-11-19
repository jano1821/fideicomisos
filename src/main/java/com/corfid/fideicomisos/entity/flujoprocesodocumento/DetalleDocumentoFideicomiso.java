package com.corfid.fideicomisos.entity.flujoprocesodocumento;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "fludacdo")
public class DetalleDocumentoFideicomiso extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_iddoac", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorDetalleDocumentoFideicomiso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "d_fecini", nullable = true, insertable = true, updatable = true, length = 7)
	private Date fechaInicioActividad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "d_fecfin", nullable = true, insertable = true, updatable = true, length = 7)
	private Date fechaFinActividad;

	@Column(name = "c_codest", nullable = false, insertable = true, updatable = true, length = 2)
	private String codigoEstadoActividad;

	@Column(name = "c_desest", nullable = false, insertable = true, updatable = true, length = 50)
	private String descripcionEstadoActividad;

	@Column(name = "c_codres", nullable = true, insertable = true, updatable = true, length = 10)
	private String codigoResponsable;

	@Column(name = "c_nomres", nullable = true, insertable = true, updatable = true, length = 50)
	private String nombreResponsable;

	@Column(name = "c_flagat", nullable = false, insertable = true, updatable = true, length = 1)
	private String flagAtencion;

	@Column(name = "n_ordvis", nullable = false, insertable = true, updatable = true, length = 2)
	private Integer ordenVisualizacion;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_iddofi", nullable = false, insertable = true, updatable = true)
	private DocumentoFideicomiso documentoFideicomiso;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idacdf", nullable = false, insertable = true, updatable = true)
	private ActividadDetalleDocumentoFideicomiso actividadDetalleDocumentoFideicomiso;

	public Integer getIdentificadorDetalleDocumentoFideicomiso() {
		return identificadorDetalleDocumentoFideicomiso;
	}

	public void setIdentificadorDetalleDocumentoFideicomiso(Integer identificadorDetalleDocumentoFideicomiso) {
		this.identificadorDetalleDocumentoFideicomiso = identificadorDetalleDocumentoFideicomiso;
	}

	public Date getFechaInicioActividad() {
		return fechaInicioActividad;
	}

	public void setFechaInicioActividad(Date fechaInicioActividad) {
		this.fechaInicioActividad = fechaInicioActividad;
	}

	public Date getFechaFinActividad() {
		return fechaFinActividad;
	}

	public void setFechaFinActividad(Date fechaFinActividad) {
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

	public DocumentoFideicomiso getDocumentoFideicomiso() {
		return documentoFideicomiso;
	}

	public void setDocumentoFideicomiso(DocumentoFideicomiso documentoFideicomiso) {
		this.documentoFideicomiso = documentoFideicomiso;
	}

	public ActividadDetalleDocumentoFideicomiso getActividadDetalleDocumentoFideicomiso() {
		return actividadDetalleDocumentoFideicomiso;
	}

	public void setActividadDetalleDocumentoFideicomiso(
			ActividadDetalleDocumentoFideicomiso actividadDetalleDocumentoFideicomiso) {
		this.actividadDetalleDocumentoFideicomiso = actividadDetalleDocumentoFideicomiso;
	}

	public DetalleDocumentoFideicomiso(Integer identificadorDetalleDocumentoFideicomiso, Date fechaInicioActividad,
			Date fechaFinActividad, String codigoEstadoActividad, String descripcionEstadoActividad,
			String codigoResponsable, String nombreResponsable, String flagAtencion, Integer ordenVisualizacion,
			DocumentoFideicomiso documentoFideicomiso,
			ActividadDetalleDocumentoFideicomiso actividadDetalleDocumentoFideicomiso) {
		super();
		this.identificadorDetalleDocumentoFideicomiso = identificadorDetalleDocumentoFideicomiso;
		this.fechaInicioActividad = fechaInicioActividad;
		this.fechaFinActividad = fechaFinActividad;
		this.codigoEstadoActividad = codigoEstadoActividad;
		this.descripcionEstadoActividad = descripcionEstadoActividad;
		this.codigoResponsable = codigoResponsable;
		this.nombreResponsable = nombreResponsable;
		this.flagAtencion = flagAtencion;
		this.ordenVisualizacion = ordenVisualizacion;
		this.documentoFideicomiso = documentoFideicomiso;
		this.actividadDetalleDocumentoFideicomiso = actividadDetalleDocumentoFideicomiso;
	}

	public DetalleDocumentoFideicomiso() {
	}

}
