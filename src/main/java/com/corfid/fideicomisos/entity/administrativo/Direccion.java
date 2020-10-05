package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admdirec")
public class Direccion extends Auditoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_iddire", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idDireccion;

	@Column(name = "c_direcc", nullable = false, length = 200, insertable = true, updatable = true)
	private String direccion;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idpers", nullable = false, insertable = true, updatable = true)
	private Persona persona;

	public Integer getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Direccion(Integer idDireccion, String direccion, Persona persona) {
		super();
		this.idDireccion = idDireccion;
		this.direccion = direccion;
		this.persona = persona;
	}

	public Direccion() {
	}

}
