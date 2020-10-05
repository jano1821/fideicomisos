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
@Table(name = "admtelef")
public class Telefono extends Auditoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idtele", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idTelefono;

	@Column(name = "c_numero", nullable = false, length = 200, insertable = true, updatable = true)
	private String numero;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idpers", nullable = false, insertable = true, updatable = true)
	private Persona persona;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idoper", nullable = false, insertable = true, updatable = true)
	private Operador operador;

	public Integer getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(Integer idTelefono) {
		this.idTelefono = idTelefono;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Telefono(Integer idTelefono, String numero, Persona persona, Operador operador) {
		super();
		this.idTelefono = idTelefono;
		this.numero = numero;
		this.persona = persona;
		this.operador = operador;
	}

	public Telefono() {
	}

}
