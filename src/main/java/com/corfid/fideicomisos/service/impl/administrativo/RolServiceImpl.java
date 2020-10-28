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
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

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
    public CrudRolModel listRolByDescripcionPaginado(String descripcion,
                                                     Integer idEmpresaSesion,
                                                     String tipoUsuarioSesion,
                                                     Integer pagina,
                                                     Integer cant) throws Exception {
        List<Rol> listRol;
        List<RolModel> listRolModel = new ArrayList<RolModel>();
        Page<Rol> pageRol;
        CrudRolModel crudRolModel = null;

        try {
            crudRolModel = new CrudRolModel();
            String cadenaRol = Constante.COMODIN_LIKE + descripcion + Constante.COMODIN_LIKE;

            if (StringUtil.equiv(tipoUsuarioSesion, Constante.TIPO_USUARIO_SUPER_ADMIN)) {
                pageRol = rolRepository.listRolByDescripcionPaginado(cadenaRol,
                                                                     obtenerIndexPorPagina(pagina,
                                                                                           cant,
                                                                                           "descripcion",
                                                                                           true,
                                                                                           false));
            } else {
                pageRol = rolRepository.listRolByDescripcionAndUsuarioSesionPaginado(cadenaRol,
                                                                                     idEmpresaSesion,
                                                                                     obtenerIndexPorPagina(pagina,
                                                                                                           cant,
                                                                                                           "descripcion",
                                                                                                           true,
                                                                                                           false));
            }

            listRol = pageRol.getContent();
            crudRolModel.setPaginaFinal(pageRol.getTotalPages());
            crudRolModel.setCantidadRegistros(_toInteger(pageRol.getTotalElements()));

            for (Rol rol : listRol) {
                listRolModel.add(rolConverter.convertRolToRolModel(rol));
            }

            crudRolModel.setRows(listRolModel);

            return crudRolModel;
        } catch (Exception e) {
            crudRolModel.setCodigoError(ConstantesError.ERROR_17);
            crudRolModel.setMensajeError(obtenerMensajeError(ConstantesError.ERROR_17));
            return crudRolModel;
        }
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
    public RolModel addRol(RolModel rolModel, ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        try {
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
        } catch (Exception e) {
            rolModel.setCodigoError(ConstantesError.ERROR_19);
            rolModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_19));
            return rolModel;
        }
    }

    @Override
    public String updateMenuRol(String[] idMenu,
                                Integer idRol,
                                ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        List<String> listIdMenuActual = new ArrayList<String>();
        Integer existeNuevo = 0;

        try {
            //flush();
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

            return ConstantesError.ERROR_0;
        } catch (Exception e) {
            return ConstantesError.ERROR_20;
        }
    }

    @Override
    public Boolean removeRol(Integer id) throws Exception {
        Rol rol = null;
        try {
            rol = findRolById(id);
            if (null != rol) {
                rolRepository.delete(rol);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<RolModel> listAllRolesByEstadoRegistro(String estadoRegistro,
                                                       String tipoUsuarioSesion,
                                                       Integer idEmpresaSesion) throws Exception {
        List<RolModel> listRolModel = null;
        try {
            List<Rol> listRol;
            if (_equiv(tipoUsuarioSesion, Constante.TIPO_USUARIO_SUPER_ADMIN)) {
                listRol = rolRepository.findByEstadoRegistro(estadoRegistro);
            } else {
                listRol = rolRepository.listRolByEstadoRegistroAndEmpresaSesion(estadoRegistro, idEmpresaSesion);
            }
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

    public List<RolModel> listRolesByEmpresa(List<RolModel> listAllRolesUsuario,
                                             Integer idEmpresaSesion) throws Exception {
        List<RolModel> listRolModel = null;
        try {
            if (!_isEmpty(listAllRolesUsuario)) {
                listRolModel = new ArrayList<RolModel>();
                for (RolModel rolModel : listAllRolesUsuario) {
                    if (_equiv(rolModel.getIdEmpresa(), idEmpresaSesion)) {
                        listRolModel.add(rolModel);
                    }
                }
            }
            return listRolModel;
        } catch (Exception e) {
            return listRolModel;
        }
    }
}
