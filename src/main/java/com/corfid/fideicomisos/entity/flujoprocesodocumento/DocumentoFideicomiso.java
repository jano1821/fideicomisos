package com.corfid.fideicomisos.entity.flujoprocesodocumento;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;
import com.corfid.fideicomisos.entity.banco.Fideicomiso;

@Entity
@Table(name = "flumdofi")
public class DocumentoFideicomiso extends Auditoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_iddofi", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer identificadorDocumentoFideicomiso;

	@Column(name = "c_tipdoc", nullable = false, insertable = true, updatable = true, length = 2)
	private String tipoDocumento;

	@Column(name = "c_desdoc", nullable = false, insertable = true, updatable = true, length = 20)
	private String descripcionDocumento;

	@Column(name = "c_codest", nullable = false, insertable = true, updatable = true, length = 2)
	private String codigoEstado;

	@Column(name = "c_desest", nullable = false, insertable = true, updatable = true, length = 20)
	private String descripcionEstado;

	@Column(name = "c_nomarc", nullable = false, insertable = true, updatable = true, length = 30)
	private String nombreArchivo;

	@Column(name = "c_foacar", nullable = false, insertable = true, updatable = true, length = 2)
	private String formaAccesoArchivo;

	@Column(name = "c_rutarc", nullable = true, insertable = true, updatable = true, length = 50)
	private String rutaUbicacionArchivo;

	 @Lob
	@Column(name = "c_arcfis", nullable = true, insertable = true, updatable = true)
	private byte[] archivoFisicoAtachado;

	@Column(name = "c_indade", nullable = false, insertable = true, updatable = true, length = 1)
	private String indicadorAdenda;

	@Temporal(TemporalType.DATE)
	@Column(name = "d_fecdoc", nullable = true, insertable = true, updatable = true, length = 7)
	private Date fechaFirmaDocumento;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idfico", nullable = false, insertable = true, updatable = true)
	private Fideicomiso fideicomiso;

	public Integer getIdentificadorDocumentoFideicomiso() {
		return identificadorDocumentoFideicomiso;
	}

	public void setIdentificadorDocumentoFideicomiso(Integer identificadorDocumentoFideicomiso) {
		this.identificadorDocumentoFideicomiso = identificadorDocumentoFideicomiso;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}

	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}

	public String getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getFormaAccesoArchivo() {
		return formaAccesoArchivo;
	}

	public void setFormaAccesoArchivo(String formaAccesoArchivo) {
		this.formaAccesoArchivo = formaAccesoArchivo;
	}

	public String getRutaUbicacionArchivo() {
		return rutaUbicacionArchivo;
	}

	public void setRutaUbicacionArchivo(String rutaUbicacionArchivo) {
		this.rutaUbicacionArchivo = rutaUbicacionArchivo;
	}

	public byte[] getArchivoFisicoAtachado() {
		return archivoFisicoAtachado;
	}

	public void setArchivoFisicoAtachado(byte[] archivoFisicoAtachado) {
		this.archivoFisicoAtachado = archivoFisicoAtachado;
	}

	public String getIndicadorAdenda() {
		return indicadorAdenda;
	}

	public void setIndicadorAdenda(String indicadorAdenda) {
		this.indicadorAdenda = indicadorAdenda;
	}

	public Date getFechaFirmaDocumento() {
		return fechaFirmaDocumento;
	}

	public void setFechaFirmaDocumento(Date fechaFirmaDocumento) {
		this.fechaFirmaDocumento = fechaFirmaDocumento;
	}

	public Fideicomiso getFideicomiso() {
		return fideicomiso;
	}

	public void setFideicomiso(Fideicomiso fideicomiso) {
		this.fideicomiso = fideicomiso;
	}

	public DocumentoFideicomiso(Integer identificadorDocumentoFideicomiso, String tipoDocumento,
			String descripcionDocumento, String codigoEstado, String descripcionEstado, String nombreArchivo,
			String formaAccesoArchivo, String rutaUbicacionArchivo, byte[] archivoFisicoAtachado, String indicadorAdenda,
			Date fechaFirmaDocumento, Fideicomiso fideicomiso) {
		super();
		this.identificadorDocumentoFideicomiso = identificadorDocumentoFideicomiso;
		this.tipoDocumento = tipoDocumento;
		this.descripcionDocumento = descripcionDocumento;
		this.codigoEstado = codigoEstado;
		this.descripcionEstado = descripcionEstado;
		this.nombreArchivo = nombreArchivo;
		this.formaAccesoArchivo = formaAccesoArchivo;
		this.rutaUbicacionArchivo = rutaUbicacionArchivo;
		this.archivoFisicoAtachado = archivoFisicoAtachado;
		this.indicadorAdenda = indicadorAdenda;
		this.fechaFirmaDocumento = fechaFirmaDocumento;
		this.fideicomiso = fideicomiso;
	}

	public DocumentoFideicomiso() {
	}

}
