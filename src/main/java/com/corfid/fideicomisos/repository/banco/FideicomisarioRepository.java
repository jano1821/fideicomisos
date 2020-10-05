package com.corfid.fideicomisos.repository.banco;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.banco.Fideicomisario;

@Repository("fideicomisarioRepository")
public interface FideicomisarioRepository extends JpaRepository<Fideicomisario, Serializable>{

	public abstract Fideicomisario findByNumeroDocumento(String numeroDocumento);
	
}
