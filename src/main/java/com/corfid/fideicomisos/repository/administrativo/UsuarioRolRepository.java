package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.UsuarioRol;
import com.corfid.fideicomisos.entity.administrativo.UsuarioRolId;

@Repository("usuarioRolRepository")
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Serializable> {

    public abstract UsuarioRol findByusuarioRolId(UsuarioRolId usuarioRolId);
}
