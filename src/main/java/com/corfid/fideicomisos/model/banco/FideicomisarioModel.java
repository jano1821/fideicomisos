package com.corfid.fideicomisos.model.banco;

public class FideicomisarioModel {

	private Integer identificadorFideicomisario;
	private String nombreFideicomisario;
	private String tipoDocumento;
	private String numeroDocumento;

	public Integer getIdentificadorFideicomisario() {
		return identificadorFideicomisario;
	}

	public void setIdentificadorFideicomisario(Integer identificadorFideicomisario) {
		this.identificadorFideicomisario = identificadorFideicomisario;
	}

	public String getNombreFideicomisario() {
		return nombreFideicomisario;
	}

	public void setNombreFideicomisario(String nombreFideicomisario) {
		this.nombreFideicomisario = nombreFideicomisario;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
