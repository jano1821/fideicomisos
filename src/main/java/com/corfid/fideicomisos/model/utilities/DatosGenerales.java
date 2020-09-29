package com.corfid.fideicomisos.model.utilities;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.administrativo.MenuModel;

public class DatosGenerales {
	private List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
	private String modo;
	private String rucEmpresa;
	private String menu;

	public List<MenuModel> getListMenuModel() {
		return listMenuModel;
	}

	public void setListMenuModel(List<MenuModel> listMenuModel) {
		this.listMenuModel = listMenuModel;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public String getRucEmpresa() {
		return rucEmpresa;
	}

	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

}
