package com.corfid.fideicomisos.component.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.corfid.fideicomisos.model.utilities.CatalogoErrorModel;
import com.corfid.fideicomisos.service.utilities.CatalogoErrorInterface;

public class GenericConverter {

	@Autowired
	@Qualifier("catalogoErrorServiceImpl")
	CatalogoErrorInterface catalogoErrorInterface;

	protected String obtenerMensajeError(String codigoError) throws Exception {
		CatalogoErrorModel catalogoErrorModel = new CatalogoErrorModel();
		catalogoErrorModel = catalogoErrorInterface.obtenerMensajeError(codigoError);

		return catalogoErrorModel.getMensaje();
	}

}
