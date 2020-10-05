package com.corfid.fideicomisos.service.utilities;

import com.corfid.fideicomisos.model.utilities.CatalogoErrorModel;

public interface CatalogoErrorInterface {
	public CatalogoErrorModel obtenerMensajeError(String codigoError) throws Exception;
}
