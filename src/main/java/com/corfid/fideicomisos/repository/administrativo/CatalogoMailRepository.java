package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.CatalogoMail;

@Repository("catalogoMailRepository")
public interface CatalogoMailRepository extends JpaRepository<CatalogoMail, Serializable> {
    @Query(value = "select d from CatalogoMail d where d.codigo = :codigo")
                  public CatalogoMail findCatalogoMailByCodigo(@Param("codigo") String codigo);
}
