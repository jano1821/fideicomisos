package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.CrudUsuarioModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface UsuarioInterface {
	public abstract UsuarioModel addUsuario(UsuarioModel usuarioModel,
			ParametrosAuditoriaModel parametrosAuditoriaModel);

	public abstract List<UsuarioModel> listAllUsuarios();
	
	public CrudUsuarioModel listUsuarioByUsernamePaginado(String userName, Integer pagina, Integer cant);
	
	public Usuario findUsuarioById(Integer id);

	public abstract UsuarioModel findUsuarioByIdModel(Integer id);
	
	public abstract void removeUsuario(Integer id);
}
