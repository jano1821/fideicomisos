package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.entity.administrativo.Menu;
import com.corfid.fideicomisos.entity.administrativo.MenuRol;
import com.corfid.fideicomisos.entity.administrativo.MenuRolId;
import com.corfid.fideicomisos.entity.administrativo.Rol;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.MenuRepository;
import com.corfid.fideicomisos.repository.administrativo.MenuRolRepository;
import com.corfid.fideicomisos.repository.administrativo.RolRepository;
import com.corfid.fideicomisos.service.administrativo.MenuRolInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;

@Service("menuRolServiceImpl")
public class MenuRolServiceImpl extends AbstractService implements MenuRolInterface {

	@Autowired
	@Qualifier("menuRolRepository")
	MenuRolRepository menuRolRepository;

	@Autowired
	@Qualifier("rolRepository")
	RolRepository rolRepository;

	@Autowired
	@Qualifier("menuRepository")
	MenuRepository menuRepository;

	public List<String> obtenerMenuByRol(Integer idRol) {
		List<MenuRol> listMenuRol;
		List<String> listIdMenu = null;
		String idMenu;
		Rol rol = new Rol();

		rol.setIdRol(idRol);
		listMenuRol = menuRolRepository.findByRol(rol);

		if (!_isEmpty(listMenuRol)) {
			listIdMenu = new ArrayList<String>();
			for (MenuRol menuRol : listMenuRol) {
				idMenu = _toStr(menuRol.getMenu().getIdMenu());
				listIdMenu.add(idMenu);
			}
		}

		return listIdMenu;
	}

	public void addMenuRol(Integer idRol, Integer idMenu, ParametrosAuditoriaModel parametrosAuditoriaModel) {
		MenuRol menuRol = new MenuRol();
		MenuRolId menuRolId = new MenuRolId();
		Rol rol;
		Menu menu;

		menuRolId.setIdRol(idRol);
		menuRolId.setIdMenu(idMenu);
		menuRol.setMenuRolId(menuRolId);

		rol = rolRepository.findByIdRol(idRol);
		menu = menuRepository.findByIdMenu(idMenu);

		rol.setIdRol(idRol);
		menu.setIdMenu(idMenu);

		menuRol.setMenu(menu);
		menuRol.setRol(rol);
		menuRol.setEstadoRegistro(Constante.ESTADO_REGISTRO_VIGENTE);
		setInsercionAuditoria(menuRol, parametrosAuditoriaModel);
		menuRolRepository.save(menuRol);
	}

	public void removeMenuRol(Integer idRol, Integer idMenu, ParametrosAuditoriaModel parametrosAuditoriaModel) {
		MenuRolId menuRolId = new MenuRolId();
		MenuRol menuRol = new MenuRol();
		menuRolId.setIdRol(idRol);
		menuRolId.setIdMenu(idMenu);
		menuRol.setMenuRolId(menuRolId);
		menuRolRepository.delete(menuRol);
	}

}
