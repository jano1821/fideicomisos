package com.corfid.fideicomisos.entity.administrativo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admroles")
public class Rol extends Auditoria {

	@Id
	@GeneratedValue
	@Column(name = "n_iderol", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idRol;

	@Column(name = "c_descri", nullable = false, length = 100, insertable = true, updatable = true)
	private String descripcion;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
 	private Set<Usuario> usuarios = new HashSet<Usuario>();

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

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Rol(Integer idRol, String descripcion) {
		super();
		this.idRol = idRol;
		this.descripcion = descripcion;
	}

	public Rol() {

	}

}
