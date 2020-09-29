package com.corfid.fideicomisos.service.administrativo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Menu;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.cruds.CrudMenuModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface MenuInterface {
	public List<MenuModel> listAllMenus();

	public CrudMenuModel listMenuByDescripcionPaginado(String descripcion, Integer pagina, Integer cant);

	public Menu findMenuById(Integer id);

	public MenuModel findMenuByIdModel(Integer id);

	public MenuModel addMenu(MenuModel menuModel, ParametrosAuditoriaModel parametrosAuditoriaModel);

	public void removeMenu(Integer id);
	
	public List<MenuModel> obtenerMenuByRol(Collection<Integer> roles);
	
	public List<MenuModel> obtenerMenusPadre();
}
