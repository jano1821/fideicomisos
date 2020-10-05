package com.corfid.fideicomisos.service.impl.utilities;

public class ErrorControladoException extends Exception {

	private static final long serialVersionUID = -4843415439743056175L;
	String codigoError;
	String mensajeError;
	Boolean adicionarMensaje;

	public ErrorControladoException() {
		this.adicionarMensaje = false;
	}

	public ErrorControladoException(String codigoError) {
		super(codigoError);
		this.codigoError = codigoError;
		this.adicionarMensaje = false;
	}

	public ErrorControladoException(String codigoError, String mensajeError) {
		this(codigoError);
		this.mensajeError = mensajeError;
		this.adicionarMensaje = true;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public Boolean getAdicionarMensaje() {
		return adicionarMensaje;
	}

	public void setAdicionarMensaje(Boolean adicionarMensaje) {
		this.adicionarMensaje = adicionarMensaje;
	}

}
