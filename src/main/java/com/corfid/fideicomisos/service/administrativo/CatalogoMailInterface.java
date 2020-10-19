package com.corfid.fideicomisos.service.administrativo;

import com.corfid.fideicomisos.model.administrativo.CatalogoMailModel;

public interface CatalogoMailInterface {

    public CatalogoMailModel obtenerCorreoPorCodigo(String codigoMail) throws Exception;
}
