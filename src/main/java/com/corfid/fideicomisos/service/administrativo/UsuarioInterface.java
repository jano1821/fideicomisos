package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.cruds.CrudUsuarioModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface UsuarioInterface {
	public abstract UsuarioModel addUsuario(UsuarioModel usuarioModel,
			ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

	public abstract List<UsuarioModel> listAllUsuarios() throws Exception;

	public CrudUsuarioModel listUsuarioByUsernamePaginado(String userName, Integer pagina, Integer cant)
			throws Exception;

	public Usuario findUsuarioById(Integer id);

	public UsuarioModel findUsuarioByUsuario(String userName) throws Exception;

	public abstract UsuarioModel findUsuarioByIdModel(Integer id) throws Exception;

	public abstract void removeUsuario(Integer id);

	public UsuarioModel findUsuarioByIdPersona(Persona persona)
			throws Exception;
}
