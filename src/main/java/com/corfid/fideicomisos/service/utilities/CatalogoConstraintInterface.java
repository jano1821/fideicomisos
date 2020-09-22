package com.corfid.fideicomisos.service.utilities;

import java.util.List;

import com.corfid.fideicomisos.model.utilities.CatalogoConstraintModel;

public interface CatalogoConstraintInterface {
	public abstract List<CatalogoConstraintModel> findByNombreTabla(String nombreTabla);
	
	public abstract List<CatalogoConstraintModel> findByNombreTablaAndNombreCampo(String nombreTabla, String nombreCampo);
	
	public List<CatalogoConstraintModel> findByNombreTablaAndNombreCampo(String nombreTabla, String nombreCampo, String valorInicial);
}
