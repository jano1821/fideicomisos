package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admemcli")
public class ClienteEmpresa extends Auditoria {
	@EmbeddedId
	private ClienteEmpresaId clienteEmpresaId;

	/*@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("idCliente")
	@JoinColumn(name = "n_idclie", nullable = false, insertable = false, updatable = false)
	private Cliente cliente;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId("idEmpresa")
	@JoinColumn(name = "n_idempr", nullable = false, insertable = false, updatable = false)
	private Empresa empresa;*/

	public ClienteEmpresaId getClienteEmpresaId() {
		return clienteEmpresaId;
	}

	public void setClienteEmpresaId(ClienteEmpresaId clienteEmpresaId) {
		this.clienteEmpresaId = clienteEmpresaId;
	}

	/*public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}*/

	public ClienteEmpresa(ClienteEmpresaId clienteEmpresaId/*, Cliente cliente, Empresa empresa*/) {
		super();
		this.clienteEmpresaId = clienteEmpresaId;
		/*this.cliente = cliente;
		this.empresa = empresa;*/
	}

	public ClienteEmpresa() {

	}
}
