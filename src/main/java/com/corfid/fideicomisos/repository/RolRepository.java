package com.corfid.fideicomisos.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corfid.fideicomisos.entity.seguridad.Rol;

public interface RolRepository extends JpaRepository<Rol, Serializable> {

	// public abstract Usuario findByUsuario(String idRol);
}
