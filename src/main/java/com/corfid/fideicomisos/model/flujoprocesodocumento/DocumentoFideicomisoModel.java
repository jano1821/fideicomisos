package com.corfid.fideicomisos.model.flujoprocesodocumento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.corfid.fideicomisos.model.banco.PaginacionGeneralModel;

public class DocumentoFideicomisoModel extends PaginacionGeneralModel {

	private Integer identificadorDocumentoFideicomiso;
	private Integer identificadorFideicomiso;
	private String tipoDocumento;
	private String descripcionDocumento;
	private String codigoEstado;
	private String descripcionEstado;
	private String nombreArchivo;
	private String formaAccesoArchivo;
	private String rutaUbicacionArchivo;
	private byte[] archivoFisicoAtachado;
	private String pesoArchivo;
	private String tipoArchivo;	
	private String indicadorAdenda;
	private Date fechaFirmaDocumento;

	public List<DocumentoFideicomisoModel> rows = new ArrayList<DocumentoFideicomisoModel>();

	public Integer getIdentificadorDocumentoFideicomiso() {
		return identificadorDocumentoFideicomiso;
	}

	public void setIdentificadorDocumentoFideicomiso(Integer identificadorDocumentoFideicomiso) {
		this.identificadorDocumentoFideicomiso = identificadorDocumentoFideicomiso;
	}

	public Integer getIdentificadorFideicomiso() {
		return identificadorFideicomiso;
	}

	public void setIdentificadorFideicomiso(Integer identificadorFideicomiso) {
		this.identificadorFideicomiso = identificadorFideicomiso;
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
	
	public String getPesoArchivo() {
		return pesoArchivo;
	}

	public void setPesoArchivo(String pesoArchivo) {
		this.pesoArchivo = pesoArchivo;
	}
	
	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
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

	public List<DocumentoFideicomisoModel> getRows() {
		return rows;
	}

	public void setRows(List<DocumentoFideicomisoModel> rows) {
		this.rows = rows;
	}

}
