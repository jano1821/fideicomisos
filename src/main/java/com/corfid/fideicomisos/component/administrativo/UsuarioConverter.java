package com.corfid.fideicomisos.component.administrativo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("usuarioConverter")
public class UsuarioConverter {
	public Usuario convertUsuarioModelToUsuario(UsuarioModel usuarioModel) {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(usuarioModel.getIdUsuario());
		usuario.setUsuario(usuarioModel.getUsuario());
		usuario.setTipoUsuario(usuarioModel.getTipoUsuario());
		if (StringUtil.equiv(usuarioModel.getEstadoActividad(), "1")) {
			usuario.setEstadoActividadUsuario(true);
		} else {
			usuario.setEstadoActividadUsuario(false);
		}

		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		usuario.setPassword(pe.encode(usuarioModel.getPassword()));
		usuario.setEstadoRegistro(usuarioModel.getEstadoRegistro());

		return usuario;

	}

	public Usuario convertUsuarioModelToUsuarioExistente(Usuario usuario, UsuarioModel usuarioModel) {
		usuario.setIdUsuario(usuarioModel.getIdUsuario());
		usuario.setUsuario(usuarioModel.getUsuario());
		usuario.setTipoUsuario(usuarioModel.getTipoUsuario());
		if (StringUtil.equiv(usuarioModel.getEstadoActividad(), "1")) {
			usuario.setEstadoActividadUsuario(true);
		} else {
			usuario.setEstadoActividadUsuario(false);
		}

		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		usuario.setPassword(pe.encode(usuarioModel.getPassword()));
		usuario.setEstadoRegistro(usuarioModel.getEstadoRegistro());

		return usuario;

	}

	public UsuarioModel convertUsuarioToUsuarioModel(Usuario usuario) {
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setIdUsuario(usuario.getIdUsuario());
		usuarioModel.setUsuario(usuario.getUsuario());
		usuarioModel.setPassword(usuario.getPassword());
		usuarioModel.setTipoUsuario(usuario.getTipoUsuario());
		usuarioModel.setEstadoActividadUsuario(usuario.isEstadoActividadUsuario());
		usuarioModel.setEstadoRegistro(usuario.getEstadoRegistro());
		if(!StringUtil.isEmpty(usuario.getPersona())) {
			usuarioModel.setIdPersona(usuario.getPersona().getIdPersona());
		}

		if (StringUtil.equiv(usuario.getTipoUsuario(), "S")) {
			usuarioModel.setDescTipoUsuario("Super Administrador");
		} else if (StringUtil.equiv(usuario.getTipoUsuario(), "A")) {
			usuarioModel.setDescTipoUsuario("Administrador");
		} else if (StringUtil.equiv(usuario.getTipoUsuario(), "B")) {
			usuarioModel.setDescTipoUsuario("Estandar");
		} else {
			usuarioModel.setDescTipoUsuario("Desconocido");
		}

		if (usuario.isEstadoActividadUsuario() == true) {
			usuarioModel.setDescEstadoActividad("Activo");
			usuarioModel.setEstadoActividad("1");
		} else {
			usuarioModel.setDescEstadoActividad("Bloqueado");
			usuarioModel.setEstadoActividad("0");
		}

		if (StringUtil.equiv(usuario.getEstadoRegistro(), "S")) {
			usuarioModel.setDescEstadoRegistro("Vigente");
		} else if (StringUtil.equiv(usuario.getEstadoRegistro(), "N")) {
			usuarioModel.setDescEstadoRegistro("No Vigente");
		} else {
			usuarioModel.setDescEstadoRegistro("Desconocido");
		}

		return usuarioModel;
	}
}
