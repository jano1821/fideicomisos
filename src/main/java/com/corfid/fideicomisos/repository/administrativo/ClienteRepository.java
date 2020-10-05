package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Cliente;

@Repository("clienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Serializable> {

	public abstract Cliente findByIdCliente(Integer idCliente);

}
