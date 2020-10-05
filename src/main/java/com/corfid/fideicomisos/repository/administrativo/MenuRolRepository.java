package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.MenuRol;
import com.corfid.fideicomisos.entity.administrativo.Rol;

@Repository("menuRolRepository")
public interface MenuRolRepository extends JpaRepository<MenuRol, Serializable>{
	public abstract List<MenuRol> findByRol(Rol rol);
}
