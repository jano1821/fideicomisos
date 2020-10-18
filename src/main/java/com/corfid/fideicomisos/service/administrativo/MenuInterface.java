package com.corfid.fideicomisos.service.administrativo;

import java.util.Collection;
import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Menu;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.cruds.CrudMenuModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface MenuInterface {

    public List<MenuModel> listAllMenus(String idTipoUsuarioSesion) throws Exception;

    public CrudMenuModel listMenuByDescripcionPaginado(String descripcion,
                                                       Integer pagina,
                                                       Integer cant) throws Exception;

    public Menu findMenuById(Integer id);

    public MenuModel findMenuByIdModel(Integer id);

    public MenuModel addMenu(MenuModel menuModel, ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public Boolean removeMenu(Integer id) throws Exception;

    public List<MenuModel> obtenerMenuByRol(Collection<Integer> roles);

    public List<MenuModel> obtenerMenusPadre(String tipoUsuarioSesion);

    public List<MenuModel> obtenerAllMenuByRol(Collection<Integer> roles);

    List<MenuModel> obtenerMenuByRolAndEmpresaSeleccionada(Collection<Integer> roles, Integer idEmpresaSesion);
}
