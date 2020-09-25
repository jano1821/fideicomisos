package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable> {

	public abstract Usuario findByUsuario(String usuario);
	
	public abstract Usuario findByIdUsuario(Integer idUsuario);
	
	public abstract Boolean existsByUsuario(String usuario);

	@Query(value = "select u from Usuario u where u.usuario like :usuario",
			countQuery = "select count(u) from Usuario u where u.usuario like :usuario")
	public abstract Page<Usuario> listUsuarioByUserNamePaginado (
		      @Param("usuario") String userName, 
		      Pageable pageable);
}
