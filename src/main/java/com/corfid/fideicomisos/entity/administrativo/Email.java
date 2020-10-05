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
@Table(name = "admemail")
public class Email extends Auditoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_idmail", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idMail;

	@Column(name = "c_demail", nullable = false, length = 200, insertable = true, updatable = true)
	private String direccionMail;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idpers", nullable = false, insertable = true, updatable = true)
	private Persona persona;

	public Integer getIdMail() {
		return idMail;
	}

	public void setIdMail(Integer idMail) {
		this.idMail = idMail;
	}

	public String getDireccionMail() {
		return direccionMail;
	}

	public void setDireccionMail(String direccionMail) {
		this.direccionMail = direccionMail;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Email(Integer idMail, String direccionMail, Persona persona) {
		super();
		this.idMail = idMail;
		this.direccionMail = direccionMail;
		this.persona = persona;
	}

	public Email() {
	}

}
