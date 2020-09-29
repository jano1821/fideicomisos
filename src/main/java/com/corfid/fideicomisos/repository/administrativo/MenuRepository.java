package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Menu;

@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<Menu, Serializable> {
	public abstract Menu findByIdMenu(Integer id);

	@Query(value = "select m from Menu m where m.descripcion like :descripcion", countQuery = "select count(m) from Menu m where m.descripcion like :descripcion")
	public abstract Page<Menu> listMenuByDescripcionPaginado(@Param("descripcion") String descripcion,
			Pageable pageable);

	@Query(value = "SELECT m FROM Menu m INNER JOIN MenuRol r ON m.idMenu = r.menu.idMenu WHERE r.rol.idRol IN (:roles) AND m.estadoRegistro='S' ORDER BY m.idMenuPadre, m.idMenu ")
	public abstract List<Menu> listMenuPorRoles(@Param("roles") Collection<Integer> roles);

	@Query(value = "SELECT m FROM Menu m WHERE m.estadoRegistro='S' AND m.tipoMenu = 'P' ORDER BY m.idMenuPadre")
	public abstract List<Menu> listMenuPadres();
}
