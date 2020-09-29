package com.corfid.fideicomisos.entity.administrativo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admclien")
public class Cliente extends Auditoria {

	@Id
	@GeneratedValue
	@Column(name = "n_idclie", nullable = false, insertable = true, updatable = true, precision = 11, scale = 0)
	private Integer idCliente;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "n_idpers", nullable = false, insertable = true, updatable = true)
	private Persona persona;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Empresa> lstEmpresa = new ArrayList<Empresa>(0);

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Empresa> getLstEmpresa() {
		return lstEmpresa;
	}

	public void setLstEmpresa(List<Empresa> lstEmpresa) {
		this.lstEmpresa = lstEmpresa;
	}

	public Cliente(Integer idCliente, Persona persona, List<Empresa> lstEmpresa) {
		super();
		this.idCliente = idCliente;
		this.persona = persona;
		this.lstEmpresa = lstEmpresa;
	}

	public Cliente() {

	}
}
