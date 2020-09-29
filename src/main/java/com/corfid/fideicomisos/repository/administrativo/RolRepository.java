package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.corfid.fideicomisos.entity.administrativo.Rol;

public interface RolRepository extends JpaRepository<Rol, Serializable> {

	public abstract Rol findByIdRol(Integer id);
	
	@Query(value = "select r from Rol r where r.descripcion like :descripcion",
			countQuery = "select count(r) from Rol r where r.descripcion like :descripcion")
	public abstract Page<Rol> listRolByDescripcionPaginado (
		      @Param("descripcion") String descripcion, 
		      Pageable pageable);
}
