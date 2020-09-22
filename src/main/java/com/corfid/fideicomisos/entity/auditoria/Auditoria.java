package com.corfid.fideicomisos.entity.auditoria;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Auditoria {
	@Column(name = "a_estreg", nullable = false, length = 1, insertable = true, updatable = true)
	protected String estadoRegistro;

	@Column(name = "a_usuins", nullable = false, length = 100, insertable = true, updatable = true)
	protected String usuarioInsercion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "a_fecins", nullable = false, insertable = true, updatable = true, length = 7)
	protected Date fechaInsercion;

	@Column(name = "a_ipeins", nullable = false, length = 45, insertable = true, updatable = true)
	protected String ipInsercion;

	@Column(name = "a_usumod", nullable = true, length = 100, insertable = true, updatable = true)
	protected String usuarioModificacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "a_fecmod", nullable = true, insertable = true, updatable = true, length = 7)
	protected Date fechaModificacion;

	@Column(name = "a_ipemod", nullable = true, length = 45, insertable = true, updatable = true)
	protected String ipModificacion;

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public String getUsuarioInsercion() {
		return usuarioInsercion;
	}

	public void setUsuarioInsercion(String usuarioInsercion) {
		this.usuarioInsercion = usuarioInsercion;
	}

	public Date getFechaInsercion() {
		return fechaInsercion;
	}

	public void setFechaInsercion(Date fechaInsercion) {
		this.fechaInsercion = fechaInsercion;
	}

	public String getIpInsercion() {
		return ipInsercion;
	}

	public void setIpInsercion(String ipInsercion) {
		this.ipInsercion = ipInsercion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getIpModificacion() {
		return ipModificacion;
	}

	public void setIpModificacion(String ipModificacion) {
		this.ipModificacion = ipModificacion;
	}

}
