package com.corfid.fideicomisos.component.utilities;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.utilities.CatalogoConstraint;
import com.corfid.fideicomisos.model.utilities.CatalogoConstraintModel;

@Component("catalogoConstraintConverter")
public class CatalogoConstraintConverter {
	public CatalogoConstraint convertCatalogoConstraintModelToCatalogoConstraint(
			CatalogoConstraintModel catalogoConstraintModel) {
		CatalogoConstraint catalogoConstraint = new CatalogoConstraint();
		catalogoConstraint.setIdConstraint(catalogoConstraintModel.getIdConstraint());
		catalogoConstraint.setAbrevConstraint(catalogoConstraintModel.getAbrevConstraint());
		catalogoConstraint.setDescConstraint(catalogoConstraintModel.getDescConstraint());
		catalogoConstraint.setNombreConstraint(catalogoConstraintModel.getNombreConstraint());
		catalogoConstraint.setNombreTabla(catalogoConstraintModel.getNombreTabla());
		catalogoConstraint.setValorConstraint(catalogoConstraintModel.getValorConstraint());

		return catalogoConstraint;

	}

	public CatalogoConstraintModel convertCatalogoConstraintToCatalogoConstraintModel(
			CatalogoConstraint catalogoConstraint) {
		CatalogoConstraintModel catalogoConstraintModel = new CatalogoConstraintModel();
		catalogoConstraintModel.setIdConstraint(catalogoConstraint.getIdConstraint());
		catalogoConstraintModel.setAbrevConstraint(catalogoConstraint.getAbrevConstraint());
		catalogoConstraintModel.setDescConstraint(catalogoConstraint.getDescConstraint());
		catalogoConstraintModel.setNombreConstraint(catalogoConstraint.getNombreConstraint());
		catalogoConstraintModel.setNombreTabla(catalogoConstraint.getNombreTabla());
		catalogoConstraintModel.setValorConstraint(catalogoConstraint.getValorConstraint());

		return catalogoConstraintModel;
	}
}
