package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.TipoUsuario;

@Repository("tipoUsuarioRepository")
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Serializable> {
    
    public abstract TipoUsuario findByIdTipoUsuario(String idTipoUsuario);

}
