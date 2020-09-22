package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corfid.fideicomisos.entity.administrativo.Rol;

public interface RolRepository extends JpaRepository<Rol, Serializable> {

	// public abstract Usuario findByUsuario(String idRol);
}
