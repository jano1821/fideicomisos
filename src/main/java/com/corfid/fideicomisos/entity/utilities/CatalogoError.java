package com.corfid.fideicomisos.entity.utilities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admcater")
public class CatalogoError extends Auditoria {

	@Id
	@Column(name = "c_iderro", nullable = false, insertable = true, updatable = true, length = 10)
	private String codigoError;

	@Column(name = "c_deserr", nullable = false, insertable = true, updatable = true, length = 500)
	private String mensaje;

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public CatalogoError(String codigoError, String mensaje) {
		super();
		this.codigoError = codigoError;
		this.mensaje = mensaje;
	}

	public CatalogoError() {
	}

}
