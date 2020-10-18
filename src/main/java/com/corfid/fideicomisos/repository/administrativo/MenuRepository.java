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

    @Query(value = "SELECT m FROM Menu m INNER JOIN MenuRol r ON m.idMenu = r.menu.idMenu WHERE r.rol.idRol IN (:roles) AND m.estadoRegistro='S' ORDER BY m.idMenuPadre, m.tipoMenu desc, m.idMenu ")
    public abstract List<Menu> listMenuPorRoles(@Param("roles") Collection<Integer> roles);

    @Query(value = "SELECT m FROM Menu m INNER JOIN MenuRol r ON m.idMenu = r.menu.idMenu WHERE r.rol.idRol IN (:roles)")
    public abstract List<Menu> listAllMenuPorRoles(@Param("roles") Collection<Integer> roles);

    @Query(value = "SELECT m FROM Menu m WHERE m.tipoMenu = 'P' AND m.tipoUsuario.idTipoUsuario like :tipoUsuarioSesion ORDER BY m.idMenuPadre ")
    public abstract List<Menu> listMenuPadres(@Param("tipoUsuarioSesion") String tipoUsuarioSesion);

    @Query(value = "SELECT m FROM Menu m WHERE m.tipoUsuario.idTipoUsuario IN ('A','B') ORDER BY m.idMenuPadre ")
    public abstract List<Menu> listAllMenuByNivel(@Param("tipoUsuarioSesion") String tipoUsuarioSesion);

	@Query(value = "SELECT m FROM Menu m "
	                + "INNER JOIN MenuRol r "
	                + "ON m.idMenu = r.menu.idMenu "
	                + "WHERE r.rol.idRol IN (:roles) "
	                + "AND m.estadoRegistro='S' "
	                + "AND r.rol.empresa.idEmpresa=:idEmpresaSesion "
	                + "ORDER BY m.idMenuPadre, "
	                + "m.tipoMenu desc, m.idMenu ")
    public abstract List<Menu> listMenuPorRolesAndEmpresaSeleccionada(@Param("roles") Collection<Integer> roles,
                                                                      @Param("idEmpresaSesion") Integer idEmpresaSesion);
}
