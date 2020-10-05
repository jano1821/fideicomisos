package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admtipus")
public class TipoUsuario extends Auditoria {
	@Id
	@Column(name = "c_idtius", nullable = false, length = 1, insertable = true, updatable = true)
	private String idTipoUsuario;

	@Column(name = "c_descri", nullable = false, length = 45, insertable = true, updatable = true)
	private String descripcion;

	@Column(name = "n_ordpri", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer ordenPrioridad;

	public String getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(String idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getOrdenPrioridad() {
		return ordenPrioridad;
	}

	public void setOrdenPrioridad(Integer ordenPrioridad) {
		this.ordenPrioridad = ordenPrioridad;
	}

	public TipoUsuario(String idTipoUsuario, String descripcion, Integer ordenPrioridad) {
		super();
		this.idTipoUsuario = idTipoUsuario;
		this.descripcion = descripcion;
		this.ordenPrioridad = ordenPrioridad;
	}

	public TipoUsuario() {
	}

}
