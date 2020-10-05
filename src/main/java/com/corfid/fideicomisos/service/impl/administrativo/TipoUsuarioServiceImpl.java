package com.corfid.fideicomisos.service.impl.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.entity.administrativo.TipoUsuario;
import com.corfid.fideicomisos.repository.administrativo.TipoUsuarioRepository;
import com.corfid.fideicomisos.service.administrativo.TipoUsuarioInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
@Service("tipoUsuarioServiceImpl")
public class TipoUsuarioServiceImpl  extends AbstractService implements TipoUsuarioInterface {
    @Autowired
    @Qualifier("tipoUsuarioRepository")
    private TipoUsuarioRepository tipoUsuarioRepository;
    
    @Override
    public TipoUsuario findRolById(String id) {
        return tipoUsuarioRepository.findByIdTipoUsuario(id);
    }
}
