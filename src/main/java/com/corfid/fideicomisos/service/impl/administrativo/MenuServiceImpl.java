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

@Service("menuServiceImpl")
public class MenuServiceImpl extends AbstractService implements MenuInterface {
	@Autowired
	@Qualifier("menuRepository")
	private MenuRepository menuRepository;

	@Autowired
	@Qualifier("menuConverter")
	private MenuConverter menuConverter;

	@Override
	public List<MenuModel> listAllMenus() {
		List<Menu> listMenu = menuRepository.findAll();
		List<MenuModel> listMenuModel = new ArrayList<MenuModel>();

		for (Menu menu : listMenu) {
			listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
		}

		return listMenuModel;
	}

	@Override
	public CrudMenuModel listMenuByDescripcionPaginado(String descripcion, Integer pagina, Integer cant) {
		List<Menu> listMenu;
		List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
		Page<Menu> pageMenu;
		CrudMenuModel crudMenuModel = new CrudMenuModel();

		String cadenaMenu = Constante.COMODIN_LIKE + descripcion + Constante.COMODIN_LIKE;

		pageMenu = menuRepository.listMenuByDescripcionPaginado(cadenaMenu,
				obtenerIndexPorPagina(pagina, cant, "descripcion", true, false));

		listMenu = pageMenu.getContent();
		crudMenuModel.setPaginaFinal(pageMenu.getTotalPages());
		crudMenuModel.setCantidadRegistros(_toInteger(pageMenu.getTotalElements()));

		for (Menu menu : listMenu) {
			listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
		}

		crudMenuModel.setRows(listMenuModel);

		return crudMenuModel;
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
	public MenuModel addMenu(MenuModel menuModel, ParametrosAuditoriaModel parametrosAuditoriaModel) {
		Menu menu = findMenuById(menuModel.getIdMenu());

		if (_isEmpty(menu)) {
			menu = menuConverter.convertMenuModelToMenu(menuModel);
			setInsercionAuditoria(menu, parametrosAuditoriaModel);
		} else {
			menu = menuConverter.convertMenuModelToMenuExistente(menu, menuModel);
			setModificacionAuditoria(menu, parametrosAuditoriaModel);
		}

		menu = menuRepository.save(menu);

		return menuConverter.convertMenuToMenuModel(menu);
	}

	@Override
	public void removeMenu(Integer id) {
		Menu menu = findMenuById(id);
		if (null != menu) {
			menuRepository.delete(menu);
		}
	}
	
	//Este método obtienen los menus vigentes en base a una coleccion de id de roles ordenados
	@Override
	public List<MenuModel> obtenerMenuByRol(Collection<Integer> roles){
		List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
		List<Menu> listMenu = new ArrayList<Menu>();
		
		listMenu = menuRepository.listMenuPorRoles(roles);
		
		for (Menu menu : listMenu) {
			listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
		}
		return listMenuModel;
	}
	
	@Override
	public List<MenuModel> obtenerMenusPadre(){
		List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
		List<Menu> listMenu = new ArrayList<Menu>();
		
		listMenu = menuRepository.listMenuPadres();
		
		for (Menu menu : listMenu) {
			listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
		}
		return listMenuModel;
	}
	
	//Este método obtiene todos los menus en base a una coleccion de id de roles sin importar su estado de registro
	@Override
	public List<MenuModel> obtenerAllMenuByRol(Collection<Integer> roles){
		List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
		List<Menu> listMenu = new ArrayList<Menu>();
		
		listMenu = menuRepository.listAllMenuPorRoles(roles);
		
		for (Menu menu : listMenu) {
			listMenuModel.add(menuConverter.convertMenuToMenuModel(menu));
		}
		return listMenuModel;
	}
}
