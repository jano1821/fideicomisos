package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admempre")
public class Empresa extends Auditoria {
	@Id
	@GeneratedValue
	@Column(name = "n_idempr", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idEmpresa;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idpers", nullable = false, insertable = true, updatable = true)
	private Persona persona;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idclie", nullable = false, insertable = true, updatable = true)
	private Cliente cliente;

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa(Integer idEmpresa, Persona persona, Cliente cliente) {
		super();
		this.idEmpresa = idEmpresa;
		this.persona = persona;
		this.cliente = cliente;
	}

	public Empresa() {
	}

}
