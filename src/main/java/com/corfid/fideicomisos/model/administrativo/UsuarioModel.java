package com.corfid.fideicomisos.model.administrativo;

public class UsuarioModel {
	private Integer idUsuario;
	private String usuario;
	private String password;
	private String tipoUsuario;
	private int idPersona;
	private boolean estadoActividadUsuario;
	private String estadoActividad;
	private String descEstadoActividad;
	private String descTipoUsuario;
	private String estadoRegistro;
	private String descEstadoRegistro;

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

	public String getEstadoActividad() {
		return estadoActividad;
	}

	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}

	public String getDescEstadoActividad() {
		return descEstadoActividad;
	}

	public void setDescEstadoActividad(String descEstadoActividad) {
		this.descEstadoActividad = descEstadoActividad;
	}

	public String getDescTipoUsuario() {
		return descTipoUsuario;
	}

	public void setDescTipoUsuario(String descTipoUsuario) {
		this.descTipoUsuario = descTipoUsuario;
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
