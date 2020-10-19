package com.corfid.fideicomisos.component.administrativo;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.component.utilities.GenericConverter;
import com.corfid.fideicomisos.entity.administrativo.CatalogoMail;
import com.corfid.fideicomisos.model.administrativo.CatalogoMailModel;
import com.corfid.fideicomisos.utilities.ConstantesError;

@Component("catalogoMailConverter")
public class CatalogoMailConverter extends GenericConverter {

    public CatalogoMailModel convertCatalogoMailToCatalogoMailModel(CatalogoMail catalogoMail) throws Exception {
        CatalogoMailModel catalogoMailModel = new CatalogoMailModel();
        try {

            catalogoMailModel.setIdCorreo(catalogoMail.getIdCorreo());
            catalogoMailModel.setAsunto(catalogoMail.getAsunto());
            catalogoMailModel.setContenido(catalogoMail.getContenido());
            catalogoMailModel.setCodigo(catalogoMail.getCodigo());
            catalogoMailModel.setEstadoRegistro(catalogoMail.getEstadoRegistro());

            return catalogoMailModel;
        } catch (Exception e) {
            catalogoMailModel.setCodigoError(ConstantesError.ERROR_2);
            catalogoMailModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_2));
            return catalogoMailModel;
        }
    }
}
