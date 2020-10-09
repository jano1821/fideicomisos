package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Rol;

@Repository("rolRepository")
public interface RolRepository extends JpaRepository<Rol, Serializable> {

	public abstract Rol findByIdRol(Integer id);
	
	@Query(value = "select r from Rol r where r.descripcion like :descripcion",
			countQuery = "select count(r) from Rol r where r.descripcion like :descripcion")
	public abstract Page<Rol> listRolByDescripcionPaginado (
		      @Param("descripcion") String descripcion, 
		      Pageable pageable);
	
	@Query(value = "select r from Rol r where r.descripcion like :descripcion and r.empresa.idEmpresa = :idEmpresaSeleccionada",
	                countQuery = "select count(r) from Rol r where r.descripcion like :descripcion  and r.empresa.idEmpresa = :idEmpresaSeleccionada")
	        public abstract Page<Rol> listRolByDescripcionAndUsuarioSesionPaginado (
	                  @Param("descripcion") String descripcion, 
	                  @Param("idEmpresaSeleccionada") Integer idEmpresaSeleccionada, 
	                  Pageable pageable);
	
	public abstract List<Rol> findByEstadoRegistro(String estado);
}
