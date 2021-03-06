package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.MenuConverter;
import com.corfid.fideicomisos.entity.administrativo.Menu;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.cruds.CrudMenuModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.MenuRepository;
import com.corfid.fideicomisos.service.administrativo.MenuInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;

@Service("menuServiceImpl")
public class MenuServiceImpl extends AbstractService implements MenuInterface {

    @Autowired
    @Qualifier("menuRepository")
    private MenuRepository menuRepository;

    @Autowired
    @Qualifier("menuConverter")
    private MenuConverter menuConverter;

    @Override
    public List<MenuModel> listAllMenus(String idTipoUsuarioSesion) throws Exception {

        List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
        List<Menu> listMenu = null;

        if (_equiv(idTipoUsuarioSesion, Constante.TIPO_USUARIO_SUPER_ADMIN)) {
            listMenu = menuRepository.findAll();
        } else {
            listMenu = menuRepository.listAllMenuByNivel(idTipoUsuarioSesion);
        }

        for (Menu menu : listMenu) {
            listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
        }

        return listMenuModel;
    }

    @Override
    public CrudMenuModel listMenuByDescripcionPaginado(String descripcion,
                                                       Integer pagina,
                                                       Integer cant) throws Exception {
        List<Menu> listMenu;
        List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
        Page<Menu> pageMenu;
        CrudMenuModel crudMenuModel = new CrudMenuModel();

        try {
            String cadenaMenu = Constante.COMODIN_LIKE + descripcion + Constante.COMODIN_LIKE;

            pageMenu = menuRepository.listMenuByDescripcionPaginado(cadenaMenu,
                                                                    obtenerIndexPorPagina(pagina,
                                                                                          cant,
                                                                                          "descripcion",
                                                                                          true,
                                                                                          false));

            listMenu = pageMenu.getContent();
            crudMenuModel.setPaginaFinal(pageMenu.getTotalPages());
            crudMenuModel.setCantidadRegistros(_toInteger(pageMenu.getTotalElements()));

            for (Menu menu : listMenu) {
                listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
            }

            crudMenuModel.setRows(listMenuModel);
            crudMenuModel.setCodigoError(ConstantesError.ERROR_0);

            return crudMenuModel;
        } catch (Exception e) {
            crudMenuModel.setCodigoError(ConstantesError.ERROR_18);
            crudMenuModel.setMensajeError(obtenerMensajeError(ConstantesError.ERROR_18));
            return crudMenuModel;
        }
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuRepository.findByIdMenu(id);
    }

    @Override
    public MenuModel findMenuByIdModel(Integer id) {
        return menuConverter.convertMenuToMenuModel(findMenuById(id));
    }

    @Override
    public MenuModel addMenu(MenuModel menuModel, ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        Menu menu;

        try {
            menu = findMenuById(menuModel.getIdMenu());
            if (_isEmpty(menu)) {
                menu = menuConverter.convertMenuModelToMenu(menuModel);
                setInsercionAuditoria(menu, parametrosAuditoriaModel);
            } else {
                menu = menuConverter.convertMenuModelToMenuExistente(menu, menuModel);
                setModificacionAuditoria(menu, parametrosAuditoriaModel);
            }

            menu = menuRepository.save(menu);
            if (_isEmpty(menu)) {
                menuModel.setCodigoError(ConstantesError.ERROR_24);
            } else {
                menuModel.setCodigoError(ConstantesError.ERROR_0);
                menuModel = menuConverter.convertMenuToMenuModel(menu);
            }
            return menuModel;
        } catch (Exception e) {
            menuModel.setCodigoError(ConstantesError.ERROR_24);
            return menuModel;
        }
    }

    @Override
    public Boolean removeMenu(Integer id) throws Exception {
        Menu menu = null;
        try {
            menu = findMenuById(id);
            if (null != menu) {
                menuRepository.delete(menu);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Este método obtienen los menus vigentes en base a una coleccion de id de roles ordenados
    @Override
    public List<MenuModel> obtenerMenuByRol(Collection<Integer> roles) {
        List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
        List<Menu> listMenu = new ArrayList<Menu>();

        listMenu = menuRepository.listMenuPorRoles(roles);

        for (Menu menu : listMenu) {
            listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
        }
        return listMenuModel;
    }

    @Override
    public List<MenuModel> obtenerMenuByRolAndEmpresaSeleccionada(Collection<Integer> roles, Integer idEmpresaSesion) {
        List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
        List<Menu> listMenu = new ArrayList<Menu>();

        listMenu = menuRepository.listMenuPorRolesAndEmpresaSeleccionada(roles, idEmpresaSesion);
        if (!_isEmpty(listMenu)) {
            for (Menu menu : listMenu) {
                listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
            }
        } else {
            listMenuModel = obtenerMenuByRolSinEmpresa(roles);
        }

        return listMenuModel;
    }

    @Override
    public List<MenuModel> obtenerMenusPadre(String tipoUsuarioSesion) {
        List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
        List<Menu> listMenu = new ArrayList<Menu>();

        if (_equiv(tipoUsuarioSesion, Constante.TIPO_USUARIO_SUPER_ADMIN)) {
            tipoUsuarioSesion = "%";
        }
        listMenu = menuRepository.listMenuPadres(tipoUsuarioSesion);

        for (Menu menu : listMenu) {
            listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
        }
        return listMenuModel;
    }

    //Este método obtiene todos los menus en base a una coleccion de id de roles sin importar su estado de registro
    @Override
    public List<MenuModel> obtenerAllMenuByRol(Collection<Integer> roles) {
        List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
        List<Menu> listMenu = new ArrayList<Menu>();

        listMenu = menuRepository.listAllMenuPorRoles(roles);

        for (Menu menu : listMenu) {
            listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
        }
        return listMenuModel;
    }

    //Obtiene los menus si tiene un rol asignado sin empresa
    @Override
    public List<MenuModel> obtenerMenuByRolSinEmpresa(Collection<Integer> roles) {
        List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
        List<Menu> listMenu = new ArrayList<Menu>();

        listMenu = menuRepository.listMenuPorRolesSinEmpresa(roles);

        for (Menu menu : listMenu) {
            listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
        }
        return listMenuModel;
    }
}
