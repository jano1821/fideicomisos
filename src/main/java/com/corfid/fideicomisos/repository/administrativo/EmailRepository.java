package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Email;

@Repository("emailRepository")
public interface EmailRepository extends JpaRepository<Email, Serializable> {

    @Query(value = "select d from Email d where d.persona.idPersona = :idPersona",
      countQuery = "select count(d) from Email d where d.persona.idPersona = :idPersona")
    public Page<Email> findEmailByIdPersona(@Param("idPersona") Integer idPersona, 
                                            Pageable pageable);
    
    public Email findByIdMail(Integer idEmail);
    
    @Query(value = "select d from Email d where d.persona.idPersona = :idPersona AND d.estadoRegistro = 'S' ")
    public List<Email> findEmailVigenteByIdPersona(@Param("idPersona") Integer idPersona);
}
