package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.UsuarioConverter;
import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.UsuarioRepository;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl extends AbstractService implements UsuarioInterface {
	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;
	
	@Override
	public List<UsuarioModel> listAllUsuarios() {
		List<Usuario> listUsuario = usuarioRepository.findAll();
		List<UsuarioModel> listUsuarioModel = new ArrayList<UsuarioModel>();

		for (Usuario usuario : listUsuario) {
			listUsuarioModel.add(usuarioConverter.convertUsuarioToUsuarioModel(usuario));
		}

		return listUsuarioModel;
	}
	
	@Override
	public Usuario findUsuarioById(Integer id) {
		return usuarioRepository.findByIdUsuario(id);
	}
	
	@Override
	public UsuarioModel findUsuarioByIdModel(Integer id) {
		return usuarioConverter.convertUsuarioToUsuarioModel(findUsuarioById(id));
	}

	@Override
	public UsuarioModel addUsuario(UsuarioModel usuarioModel, ParametrosAuditoriaModel parametrosAuditoriaModel) {
		Usuario usuario = findUsuarioById(usuarioModel.getIdUsuario());
		
		if (_isEmpty(usuario)) {
			usuario = usuarioConverter.convertUsuarioModelToUsuario(usuarioModel);
			setInsercionAuditoria(usuario, parametrosAuditoriaModel);
		} else {
			usuario = usuarioConverter.convertUsuarioModelToUsuarioExistente(usuario, usuarioModel);
			setModificacionAuditoria(usuario, parametrosAuditoriaModel);
		}
		
		usuario = usuarioRepository.save(usuario);
		
		return usuarioConverter.convertUsuarioToUsuarioModel(usuario);
	}
}
