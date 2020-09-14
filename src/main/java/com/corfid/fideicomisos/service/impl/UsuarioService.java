package com.corfid.fideicomisos.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.entity.seguridad.Rol;
import com.corfid.fideicomisos.entity.seguridad.Usuario;
import com.corfid.fideicomisos.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService {
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByUsuario(username);
		
		List<GrantedAuthority> authorities = buildAuthorities(usuario.getRoles());

		return buildUser(usuario, authorities);
	}

	private User buildUser(Usuario usuario, List<GrantedAuthority> authorities) {

		return new User(usuario.getUsuario(), usuario.getPassword(), usuario.isEstadoActividadUsuario(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildAuthorities(Set<Rol> roles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

		for (Rol rol : roles) {
			auths.add(new SimpleGrantedAuthority(rol.getDescripcion()));
		}

		return new ArrayList<GrantedAuthority>(auths);
	}
}
