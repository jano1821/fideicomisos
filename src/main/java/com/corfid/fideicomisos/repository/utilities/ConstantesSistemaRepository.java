package com.corfid.fideicomisos.repository.utilities;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.utilities.ConstantesSistema;

@Repository("constantesSistemaRepository")
public interface ConstantesSistemaRepository extends JpaRepository<ConstantesSistema, Serializable> {

    @Query(value = "SELECT c FROM ConstantesSistema c WHERE c.nombreConstraint = :nombreConstante ")
    public abstract ConstantesSistema findByNombreConstraint(@Param("nombreConstante") String nombreConstante);
}
