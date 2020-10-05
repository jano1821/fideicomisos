package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.ClienteEmpresa;
import com.corfid.fideicomisos.entity.administrativo.ClienteEmpresaId;

@Repository("clienteEmpresaRepository")
public interface ClienteEmpresaRepository extends JpaRepository<ClienteEmpresa, Serializable> {

    public abstract ClienteEmpresa findByClienteEmpresaId(ClienteEmpresaId clienteEmpresaId);
}
