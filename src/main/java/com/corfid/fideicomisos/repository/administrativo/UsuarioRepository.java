package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Persona;
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
	
	@Query(value = "SELECT u " +
	                "FROM Usuario u " +
	                "WHERE u.idUsuario IN (" + 
	                "SELECT DISTINCT usuar.idUsuario " + 
	                "FROM Usuario usuar " + 
	                "INNER JOIN Persona perso ON usuar.persona.idPersona = perso.idPersona " +
	                "INNER JOIN ClienteEmpresa emcli ON emcli.clienteEmpresaId.idCliente = perso.idPersona " + 
	                "WHERE emcli.clienteEmpresaId.idEmpresa IN (" +
	                "SELECT DISTINCT emcl.clienteEmpresaId.idEmpresa " + 
	                "FROM ClienteEmpresa emcl " +
	                "INNER JOIN Usuario usua " +
	                "ON usua.persona.idPersona = emcl.clienteEmpresaId.idCliente " + 
	                "WHERE usua.usuario = :usuarioSesion " +
	                "AND emcl.clienteEmpresaId.idEmpresa = :idEmpresaSesion )) " +
	                "AND u.usuario like :userName ",
        	countQuery = "SELECT count(u) " + 
        	                "FROM Usuario u " + 
        	                "WHERE u.idUsuario IN (" + 
        	                "SELECT DISTINCT usuar.idUsuario " + 
        	                "FROM Usuario usuar " + 
        	                "INNER JOIN Persona perso ON usuar.persona.idPersona = perso.idPersona " + 
        	                "INNER JOIN ClienteEmpresa emcli ON emcli.clienteEmpresaId.idCliente = perso.idPersona " + 
        	                "WHERE emcli.clienteEmpresaId.idEmpresa IN (" + 
        	                "SELECT DISTINCT emcl.clienteEmpresaId.idEmpresa " + 
        	                "FROM ClienteEmpresa emcl " + 
        	                "INNER JOIN Usuario usua " +
                            "ON usua.persona.idPersona = emcl.clienteEmpresaId.idCliente " + 
                            "WHERE usua.usuario = :usuarioSesion " +
                            "AND emcl.clienteEmpresaId.idEmpresa = :idEmpresaSesion )) " +
        	                "AND u.usuario like :userName ")
	public abstract Page<Usuario> listUsuarioByUserNameAndEmpresaVinculadaPaginado (
	                                                             @Param("userName") String userName,
	                                                             @Param("usuarioSesion") String usuarioSesion,
	                                                             @Param("idEmpresaSesion") Integer idEmpresaSesion,
	                                                             Pageable pageable);
	
	public Usuario findUsuarioByPersona(Persona persona);
}
