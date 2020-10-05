package com.corfid.fideicomisos.component.administrativo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Rol;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.service.administrativo.MenuInterface;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("rolConverter")
public class RolConverter {

	@Autowired
	@Qualifier("menuServiceImpl")
	MenuInterface menuInterface;
	
	public Rol convertRolModelToRol(RolModel rolModel) {
		Rol rol = new Rol();
		rol.setIdRol(rolModel.getIdRol());
		rol.setDescripcion(rolModel.getDescripcion());
		rol.setEstadoRegistro(rolModel.getEstadoRegistro());

		return rol;

	}

	public Rol convertRolModelToRolExistente(Rol rol, RolModel rolModel) {
		rol.setIdRol(rolModel.getIdRol());
		rol.setDescripcion(rolModel.getDescripcion());
		rol.setEstadoRegistro(rolModel.getEstadoRegistro());

		return rol;

	}

	public RolModel convertRolToRolModel(Rol rol) {
		RolModel rolModel = new RolModel();
		List<MenuModel> listMenuModel = new ArrayList<MenuModel>();

		Collection<Integer> roles = new ArrayList<Integer>(); 
		roles.add(rol.getIdRol());
		
		listMenuModel = menuInterface.obtenerAllMenuByRol(roles);
		
		rolModel.setIdRol(rol.getIdRol());
		rolModel.setDescripcion(rol.getDescripcion());
		rolModel.setEstadoRegistro(rol.getEstadoRegistro());
		rolModel.setListMenu(listMenuModel);

		if (StringUtil.equiv(rol.getEstadoRegistro(), "S")) {
			rolModel.setDescEstadoRegistro("Vigente");
		} else if (StringUtil.equiv(rol.getEstadoRegistro(), "N")) {
			rolModel.setDescEstadoRegistro("No Vigente");
		} else {
			rolModel.setDescEstadoRegistro("Desconocido");
		}

		return rolModel;
	}
}
