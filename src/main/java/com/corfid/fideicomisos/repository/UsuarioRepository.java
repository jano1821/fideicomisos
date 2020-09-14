package com.corfid.fideicomisos.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.seguridad.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {

	public abstract Usuario findByUsuario(String usuario);
}
