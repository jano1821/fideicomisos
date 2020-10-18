package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.ClienteEmpresa;
import com.corfid.fideicomisos.entity.administrativo.ClienteEmpresaId;

@Repository("clienteEmpresaRepository")
public interface ClienteEmpresaRepository extends JpaRepository<ClienteEmpresa, Serializable> {

    public abstract ClienteEmpresa findByClienteEmpresaId(ClienteEmpresaId clienteEmpresaId);
    
    @Query(value = "SELECT emcli FROM ClienteEmpresa emcli " + 
                    "INNER JOIN Persona perso " + 
                    "ON perso.idPersona = emcli.clienteEmpresaId.idCliente " + 
                    "INNER JOIN Usuario usuar " + 
                    "ON usuar.persona.idPersona = perso.idPersona " + 
                    "WHERE usuar.idUsuario = :idUsuario "+
                    "AND emcli.clienteEmpresaId.idEmpresa NOT IN (:idEmpresaSesion)")
    public abstract List<ClienteEmpresa> findByIdempresaAndIdUsuario(Integer idUsuario, Integer idEmpresaSesion);
}
