package com.corfid.fideicomisos.component.administrativo;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Menu;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("menuConverter")
public class MenuConverter {
	public Menu convertMenuModelToMenu(MenuModel menuModel) {
		Menu menu = new Menu();
		menu.setIdMenu(menuModel.getIdMenu());
		menu.setDescripcion(menuModel.getDescripcion());
		menu.setUrl(menuModel.getUrl());
		menu.setTipoMenu(menuModel.getTipoMenu());
		menu.setIdMenuPadre(menuModel.getIdMenuPadre());
		menu.setEstadoRegistro(menuModel.getEstadoRegistro());

		return menu;

	}

	public Menu convertMenuModelToMenuExistente(Menu menu, MenuModel menuModel) {
		menu.setIdMenu(menuModel.getIdMenu());
		menu.setDescripcion(menuModel.getDescripcion());
		menu.setUrl(menuModel.getUrl());
		menu.setTipoMenu(menuModel.getTipoMenu());
		menu.setIdMenuPadre(menuModel.getIdMenuPadre());
		menu.setEstadoRegistro(menuModel.getEstadoRegistro());

		return menu;

	}

	public MenuModel convertMenuToMenuModel(Menu menu) {
		MenuModel menuModel = new MenuModel();

		menuModel.setIdMenu(menu.getIdMenu());
		menuModel.setDescripcion(menu.getDescripcion());
		menuModel.setUrl(menu.getUrl());
		menuModel.setTipoMenu(menu.getTipoMenu());
		menuModel.setIdMenuPadre(menu.getIdMenuPadre());
		menuModel.setEstadoRegistro(menu.getEstadoRegistro());

		if (StringUtil.equiv(menu.getEstadoRegistro(), "S")) {
			menuModel.setDescEstadoRegistro("Vigente");
		} else if (StringUtil.equiv(menu.getEstadoRegistro(), "N")) {
			menuModel.setDescEstadoRegistro("No Vigente");
		} else {
			menuModel.setDescEstadoRegistro("Desconocido");
		}

		if (StringUtil.equiv(menu.getTipoMenu(), "P")) {
			menuModel.setDescTipoMenu("Principal");
		} else if (StringUtil.equiv(menu.getTipoMenu(), "H")) {
			menuModel.setDescTipoMenu("Cabecera");
		} else if (StringUtil.equiv(menu.getTipoMenu(), "C")) {
			menuModel.setDescTipoMenu("Menu");
		} else {
			menuModel.setDescTipoMenu("Desconocido");
		}

		return menuModel;
	}
}
