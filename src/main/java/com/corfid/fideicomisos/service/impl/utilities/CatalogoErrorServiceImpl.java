package com.corfid.fideicomisos.service.impl.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.utilities.CatalogoErrorConverter;
import com.corfid.fideicomisos.model.utilities.CatalogoErrorModel;
import com.corfid.fideicomisos.repository.utilities.CatalogoErrorRepository;
import com.corfid.fideicomisos.service.utilities.CatalogoErrorInterface;

@Service("catalogoErrorServiceImpl")
public class CatalogoErrorServiceImpl implements CatalogoErrorInterface {

    @Autowired
    @Qualifier("catalogoErrorRepository")
    CatalogoErrorRepository catalogoErrorRepository;

    @Autowired
    @Qualifier("catalogoErrorConverter")
    CatalogoErrorConverter catalogoErrorConverter;

    public CatalogoErrorModel obtenerMensajeError(String codigoError) throws Exception {

        CatalogoErrorModel catalogoErrorModel = new CatalogoErrorModel();

        catalogoErrorModel = catalogoErrorConverter.convertCatalogoErrorToCatalogoErrorModel(catalogoErrorRepository.findByCodigoError(codigoError));
        return catalogoErrorModel;
    }

}
