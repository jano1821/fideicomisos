package com.corfid.fideicomisos.component.administrativo;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Rol;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("rolConverter")
public class RolConverter {

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

		rolModel.setIdRol(rol.getIdRol());
		rolModel.setDescripcion(rol.getDescripcion());
		rolModel.setEstadoRegistro(rol.getEstadoRegistro());

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
