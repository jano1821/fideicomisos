package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Persona;

@Repository("personaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Serializable> {
	public abstract Persona findByIdPersona(Integer idPersona);
	
	@Query(value = "select u from Persona u where u.nombreCompleto like :nombres",
			countQuery = "select count(u) from Persona u where u.nombreCompleto like :nombres")
	public abstract Page<Persona> listPersonaByNombrePaginado (
		      @Param("nombres") String nombres, 
		      Pageable pageable);
	
	@Query(value = "SELECT m1 from Persona m1 " +
			"INNER JOIN Empresa e " +
			"ON m1.idPersona = e.persona.idPersona " +
			"INNER JOIN Cliente c " + 
			"ON c.idCliente = e.cliente.idCliente " +
			"INNER JOIN Persona m2 " + 
			"ON c.persona.idPersona = m2.idPersona " + 
			"WHERE  m2.idPersona = :idPersona")
	public abstract List<Persona> listEmpresaByPersona (
		      @Param("idPersona") Integer idPersona);
}
