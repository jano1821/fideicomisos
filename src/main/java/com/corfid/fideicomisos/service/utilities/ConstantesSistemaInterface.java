package com.corfid.fideicomisos.service.utilities;

import com.corfid.fideicomisos.model.utilities.ConstantesSistemaModel;

public interface ConstantesSistemaInterface {
    public ConstantesSistemaModel obtenerConstanteByNombre(String nombreConstante) throws Exception;
}
