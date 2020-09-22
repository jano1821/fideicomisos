package com.corfid.fideicomisos.model.utilities;

import java.util.Date;

public class ParametrosAuditoriaModel {

	private Date fechaInsercion;

	private String usuarioInsercion;

	private String ipInsercion;

	private Date fechaModificacion;

	private String usuarioModificacion;

	private String ipModificacion;

	public Date getFechaInsercion() {
		return fechaInsercion;
	}

	public void setFechaInsercion(Date fechaInsercion) {
		this.fechaInsercion = fechaInsercion;
	}

	public String getUsuarioInsercion() {
		return usuarioInsercion;
	}

	public void setUsuarioInsercion(String usuarioInsercion) {
		this.usuarioInsercion = usuarioInsercion;
	}

	public String getIpInsercion() {
		return ipInsercion;
	}

	public void setIpInsercion(String ipInsercion) {
		this.ipInsercion = ipInsercion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getIpModificacion() {
		return ipModificacion;
	}

	public void setIpModificacion(String ipModificacion) {
		this.ipModificacion = ipModificacion;
	}

}
