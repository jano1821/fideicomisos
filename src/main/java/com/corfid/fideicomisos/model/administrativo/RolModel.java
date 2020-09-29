package com.corfid.fideicomisos.model.administrativo;

public class RolModel {
	private Integer idRol;

	private String descripcion;

	private String estadoRegistro;

	private String descEstadoRegistro;

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public String getDescEstadoRegistro() {
		return descEstadoRegistro;
	}

	public void setDescEstadoRegistro(String descEstadoRegistro) {
		this.descEstadoRegistro = descEstadoRegistro;
	}

}
