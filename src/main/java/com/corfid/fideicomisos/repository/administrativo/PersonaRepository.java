package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Menu;
import com.corfid.fideicomisos.entity.administrativo.Persona;

@Repository("personaRepository")
public interface PersonaRepository extends JpaRepository<Persona, Serializable> {
	public abstract Persona findByIdPersona(Integer idPersona);
	
	@Query(value = "select u from Persona u where u.nombreCompleto like :nombres and u.tipoPersona like :tipoPersona",
           countQuery = "select count(u) from Persona u where u.nombreCompleto like :nombres and u.tipoPersona like :tipoPersona")
    public abstract Page<Persona> listPersonaByNombrePaginado(@Param("nombres") String nombres,
                                                              @Param("tipoPersona") String tipoPersona,
                                                              Pageable pageable);

    @Query(value = "SELECT m1 from Persona m1 " + 
                   "INNER JOIN Empresa e " +
                   "ON m1.idPersona = e.idEmpresa " +
                   "INNER JOIN ClienteEmpresa ce " + 
                   "ON ce.clienteEmpresaId.idEmpresa = e.idEmpresa " +
                   "INNER JOIN Cliente c " + 
                   "ON c.idCliente = ce.clienteEmpresaId.idCliente " + 
                   "WHERE  c.idCliente = :idPersona")
    public abstract List<Persona> listEmpresaByPersona(@Param("idPersona") Integer idPersona);

    @Query(value = "SELECT m FROM Persona m WHERE m.permiteVinculacion = 'S' ")
    public abstract List<Persona> listAllEmpresas();

    @Query(value = "SELECT m FROM Persona m " +
                    "WHERE m.estadoRegistro = 'S' " + 
                    "AND m.permiteVinculacionCliente = 'S' " + 
                    "AND m.idPersona NOT IN (select u.persona.idPersona FROM Usuario u WHERE u.persona.idPersona = m.idPersona) ")
    public abstract List<Persona> listPersonasSinVinculacion();
    
    @Query(value = "SELECT m FROM Persona m " +
                    "WHERE m.estadoRegistro = 'S' " + 
                    "AND m.tipoDocumento = :tipoDocumento " + 
                    "AND m.numeroDocumento = :numeroDocumento ")
    public abstract List<Persona> listPersonasByNumeroDocumento(@Param("tipoDocumento") String tipoDocumento,
                                                                @Param("numeroDocumento") String numeroDocumento);
}
