package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Telefono;

@Repository("telefonoRepository")
public interface TelefonoRepository extends JpaRepository<Telefono, Serializable> {

    @Query(value = "select d from Telefono d where d.persona.idPersona = :idPersona", countQuery = "select count(d) from Telefono d where d.persona.idPersona = :idPersona")
    public Page<Telefono> findTelefonoByIdPersona(@Param("idPersona") Integer idPersona, Pageable pageable);

    public Telefono findByIdTelefono(Integer idTelefono);

    @Query(value = "select d from Telefono d where d.persona.idPersona = :idPersona AND d.estadoRegistro = 'S'  ")
    public List<Telefono> findTelefonoVigenteByIdPersona(@Param("idPersona") Integer idPersona);
}
