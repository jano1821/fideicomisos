package com.corfid.fideicomisos.component.utilities;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.utilities.CatalogoError;
import com.corfid.fideicomisos.model.utilities.CatalogoErrorModel;

@Component("catalogoErrorConverter")
public class CatalogoErrorConverter {

    public CatalogoError convertCatalogoErrorModelToCatalogoError(CatalogoErrorModel catalogoErrorModel) {
        CatalogoError catalogoError = new CatalogoError();
        catalogoError.setCodigoError(catalogoErrorModel.getCodigoError());
        catalogoError.setMensaje(catalogoErrorModel.getMensaje());

        return catalogoError;
    }

    public CatalogoErrorModel convertCatalogoErrorToCatalogoErrorModel(CatalogoError catalogoError) {
        CatalogoErrorModel catalogoErrorModel = new CatalogoErrorModel();

        catalogoErrorModel.setCodigoError(catalogoError.getCodigoError());
        catalogoErrorModel.setMensaje(catalogoError.getMensaje());

        return catalogoErrorModel;
    }
}
