package com.corfid.fideicomisos.service.impl.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.CatalogoMailConverter;
import com.corfid.fideicomisos.entity.administrativo.CatalogoMail;
import com.corfid.fideicomisos.model.administrativo.CatalogoMailModel;
import com.corfid.fideicomisos.repository.administrativo.CatalogoMailRepository;
import com.corfid.fideicomisos.service.administrativo.CatalogoMailInterface;
import com.corfid.fideicomisos.utilities.AbstractService;

@Service("catalogoMailServiceImpl")
public class CatalogoMailServiceImpl extends AbstractService implements CatalogoMailInterface {

    @Autowired
    @Qualifier("catalogoMailRepository")
    CatalogoMailRepository catalogoMailRepository;

    @Autowired
    @Qualifier("catalogoMailConverter")
    CatalogoMailConverter catalogoMailConverter;

    public CatalogoMailModel obtenerCorreoPorCodigo(String codigoMail) throws Exception {
        CatalogoMailModel catalogoMailModel = null;
        try {

            CatalogoMail catalogoMail = null;

            catalogoMail = catalogoMailRepository.findCatalogoMailByCodigo(codigoMail);

            catalogoMailModel = catalogoMailConverter.convertCatalogoMailToCatalogoMailModel(catalogoMail);

            return catalogoMailModel;

        } catch (Exception e) {
            return catalogoMailModel;
        }
    }

}
