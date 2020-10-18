package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Direccion;

@Repository("direccionRepository")
public interface DireccionRepository extends JpaRepository<Direccion, Serializable> {

    @Query(value = "select d from Direccion d where d.persona.idPersona = :idPersona",
      countQuery = "select count(d) from Direccion d where d.persona.idPersona = :idPersona")
    public Page<Direccion> findDireccionByIdPersona(@Param("idPersona") Integer idPersona, 
                                                    Pageable pageable);
    
    public Direccion findByIdDireccion(Integer idDireccion);
}
