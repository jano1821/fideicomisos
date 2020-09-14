package com.corfid.fideicomisos.model;

public class UsuarioModel {
	private Integer idUsuario;
	private String usuario;
	private String password;
	private String tipoUsuario;
	private int idPersona;
	private boolean estadoActividadUsuario;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public boolean isEstadoActividadUsuario() {
		return estadoActividadUsuario;
	}

	public void setEstadoActividadUsuario(boolean estadoActividadUsuario) {
		this.estadoActividadUsuario = estadoActividadUsuario;
	}

}
