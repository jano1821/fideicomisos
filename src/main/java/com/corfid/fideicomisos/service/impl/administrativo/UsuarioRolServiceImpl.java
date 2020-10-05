package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.entity.administrativo.UsuarioRol;
import com.corfid.fideicomisos.entity.administrativo.UsuarioRolId;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.UsuarioRolRepository;
import com.corfid.fideicomisos.service.administrativo.UsuarioRolInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;

@Service("usuarioRolServiceImpl")
public class UsuarioRolServiceImpl extends AbstractService implements UsuarioRolInterface {

    @Autowired
    @Qualifier("usuarioRolRepository")
    private UsuarioRolRepository usuarioRolRepository;

    public boolean updateUsuarioRol(String[] idRol,
                                    Integer idUsuario,
                                    List<RolModel> listRolModelActual,
                                    ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        Integer existeNuevo = 0;

        try {
            for (int i = 0; i < idRol.length; i++) {
                existeNuevo = 0;
                if (!_isEmpty(listRolModelActual)) {
                    for (RolModel rolModel : listRolModelActual) {
                        if (_equiv(idRol[i], _toStr(rolModel.getIdRol()))) {
                            existeNuevo++;
                        }
                    }
                }
                if (existeNuevo == 0) {
                    registrarUsuarioRol(idUsuario, _toInteger(idRol[i]), parametrosAuditoriaModel);
                }
            }

            if (!_isEmpty(listRolModelActual)) {
                for (RolModel rolModel : listRolModelActual) {
                    existeNuevo = 0;
                    for (int i = 0; i < idRol.length; i++) {
                        if (_equiv(_toStr(rolModel.getIdRol()), idRol[i])) {
                            existeNuevo++;
                        }
                    }
                    if (existeNuevo == 0) {
                        removerUsuarioRol(idUsuario, rolModel.getIdRol());
                    }
                }
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean registrarUsuarioRol(Integer idUsuario,
                                       Integer idRol,
                                       ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        try {
            UsuarioRol usuarioRol = new UsuarioRol();
            UsuarioRolId id = new UsuarioRolId();

            id.setIdUsuario(idUsuario);
            id.setIdRol(idRol);

            usuarioRol.setUsuarioRolId(id);
            setInsercionAuditoria(usuarioRol, parametrosAuditoriaModel);
            usuarioRol.setEstadoRegistro(Constante.ESTADO_REGISTRO_VIGENTE);
            usuarioRolRepository.save(usuarioRol);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Boolean removerUsuarioRol(Integer idUsuario, Integer idRol) throws Exception {
        try {
            UsuarioRol usuarioRol = new UsuarioRol();
            UsuarioRolId id = new UsuarioRolId();

            id.setIdUsuario(idUsuario);
            id.setIdRol(idRol);

            usuarioRol = usuarioRolRepository.findByusuarioRolId(id);

            usuarioRolRepository.delete(usuarioRol);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
