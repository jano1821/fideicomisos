package com.corfid.fideicomisos.entity.administrativo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;

@Entity
@Table(name = "admemcli")
public class ClienteEmpresa extends Auditoria {

    @EmbeddedId
    private ClienteEmpresaId clienteEmpresaId;

    public ClienteEmpresaId getClienteEmpresaId() {
        return clienteEmpresaId;
    }

    public void setClienteEmpresaId(ClienteEmpresaId clienteEmpresaId) {
        this.clienteEmpresaId = clienteEmpresaId;
    }

    public ClienteEmpresa(ClienteEmpresaId clienteEmpresaId) {
        super();
        this.clienteEmpresaId = clienteEmpresaId;
    }

    public ClienteEmpresa() {

    }
}
