package com.corfid.fideicomisos.repository.utilities;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.utilities.CatalogoConstraint;

@Repository("catalogoConstraintRepository")
public interface CatalogoConstraintRepository extends JpaRepository<CatalogoConstraint, Serializable> {

    public abstract List<CatalogoConstraint> findByNombreTabla(String nombreTabla);

    @Query(value = "SELECT c FROM CatalogoConstraint c WHERE c.nombreTabla = :nombreTabla AND c.nombreConstraint = :nombreCampo ")
    public abstract List<CatalogoConstraint> findByNombreTablaAndNombreCampo(@Param("nombreTabla") String nombreTabla,
                                                                             @Param("nombreCampo") String nombreCampo);

}
