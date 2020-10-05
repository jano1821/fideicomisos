package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.RolConverter;
import com.corfid.fideicomisos.entity.administrativo.Rol;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.cruds.CrudRolModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.RolRepository;
import com.corfid.fideicomisos.service.administrativo.MenuRolInterface;
import com.corfid.fideicomisos.service.administrativo.RolInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;

@Service("rolServiceImpl")
public class RolServiceImpl extends AbstractService implements RolInterface {

    @Autowired
    @Qualifier("rolRepository")
    private RolRepository rolRepository;

    @Autowired
    @Qualifier("rolConverter")
    private RolConverter rolConverter;

    @Autowired
    @Qualifier("menuRolServiceImpl")
    MenuRolInterface menuRolInterface;

    @Override
    public List<RolModel> listAllRoles() {
        List<Rol> listRol = rolRepository.findAll();
        List<RolModel> listRolModel = new ArrayList<RolModel>();

        for (Rol rol : listRol) {
            listRolModel.add(rolConverter.convertRolToRolModel(rol));
        }

        return listRolModel;
    }

    @Override
    public CrudRolModel listRolByDescripcionPaginado(String descripcion, Integer pagina, Integer cant) {
        List<Rol> listRol;
        List<RolModel> listRolModel = new ArrayList<RolModel>();
        Page<Rol> pageRol;
        CrudRolModel crudRolModel = new CrudRolModel();

        String cadenaRol = Constante.COMODIN_LIKE + descripcion + Constante.COMODIN_LIKE;

        pageRol = rolRepository.listRolByDescripcionPaginado(cadenaRol,
                                                             obtenerIndexPorPagina(pagina,
                                                                                   cant,
                                                                                   "descripcion",
                                                                                   true,
                                                                                   false));

        listRol = pageRol.getContent();
        crudRolModel.setPaginaFinal(pageRol.getTotalPages());
        crudRolModel.setCantidadRegistros(_toInteger(pageRol.getTotalElements()));

        for (Rol rol : listRol) {
            listRolModel.add(rolConverter.convertRolToRolModel(rol));
        }

        crudRolModel.setRows(listRolModel);

        return crudRolModel;
    }

    @Override
    public Rol findRolById(Integer id) {
        return rolRepository.findByIdRol(id);
    }

    @Override
    public RolModel findRolByIdModel(Integer id) {
        return rolConverter.convertRolToRolModel(findRolById(id));
    }

    @Override
    public RolModel addRol(RolModel rolModel, ParametrosAuditoriaModel parametrosAuditoriaModel) {
        Rol rol = findRolById(rolModel.getIdRol());

        if (_isEmpty(rol)) {
            rol = rolConverter.convertRolModelToRol(rolModel);
            setInsercionAuditoria(rol, parametrosAuditoriaModel);
        } else {
            rol = rolConverter.convertRolModelToRolExistente(rol, rolModel);
            setModificacionAuditoria(rol, parametrosAuditoriaModel);
        }

        rol = rolRepository.save(rol);

        return rolConverter.convertRolToRolModel(rol);
    }

    @Override
    public boolean updateMenuRol(String[] idMenu, Integer idRol, ParametrosAuditoriaModel parametrosAuditoriaModel) {
        List<String> listIdMenuActual = new ArrayList<String>();
        Integer existeNuevo = 0;

        try {

            listIdMenuActual = menuRolInterface.obtenerMenuByRol(idRol);

            for (int i = 0; i < idMenu.length; i++) {
                existeNuevo = 0;
                if (!_isEmpty(listIdMenuActual)) {
                    for (String idMenuActual : listIdMenuActual) {
                        if (_equiv(idMenu[i], idMenuActual)) {
                            existeNuevo++;
                        }
                    }
                }
                if (existeNuevo == 0) {
                    menuRolInterface.addMenuRol(idRol, _toInteger(idMenu[i]), parametrosAuditoriaModel);
                }
            }

            if (!_isEmpty(listIdMenuActual)) {
                for (String idMenuActual : listIdMenuActual) {
                    existeNuevo = 0;
                    for (int i = 0; i < idMenu.length; i++) {
                        if (_equiv(idMenuActual, idMenu[i])) {
                            existeNuevo++;
                        }
                    }
                    if (existeNuevo == 0) {
                        menuRolInterface.removeMenuRol(idRol, _toInteger(idMenuActual), parametrosAuditoriaModel);
                    }
                }
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void removeRol(Integer id) {
        Rol rol = findRolById(id);
        if (null != rol) {
            rolRepository.delete(rol);
        }
    }

    @Override
    public List<RolModel> listAllRolesByEstadoRegistro(String estadoRegistro) throws Exception {
        List<RolModel> listRolModel = null;
        try {
            List<Rol> listRol = rolRepository.findByEstadoRegistro(estadoRegistro);
            if (!_isEmpty(listRol)) {
                listRolModel = new ArrayList<RolModel>();
                for (Rol rol : listRol) {
                    listRolModel.add(rolConverter.convertRolToRolModel(rol));
                }
            }
            return listRolModel;
        } catch (Exception e) {
            return listRolModel;
        }
    }
}
