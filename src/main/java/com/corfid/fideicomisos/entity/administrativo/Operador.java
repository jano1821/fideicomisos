package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admopera")
public class Operador extends Auditoria {
	@Id
	@GeneratedValue
	@Column(name = "n_idoper", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idPersona;

	@Column(name = "c_descri", nullable = false, length = 200, insertable = true, updatable = true)
	private String descripcion;

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Operador(Integer idPersona, String descripcion) {
		super();
		this.idPersona = idPersona;
		this.descripcion = descripcion;
	}

	public Operador() {

	}

}
