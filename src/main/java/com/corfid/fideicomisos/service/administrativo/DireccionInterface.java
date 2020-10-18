package com.corfid.fideicomisos.service.administrativo;

import com.corfid.fideicomisos.model.administrativo.DireccionModel;
import com.corfid.fideicomisos.model.cruds.CrudDireccionModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface DireccionInterface {

    public abstract CrudDireccionModel findDireccionByIdPersona(Integer idPersona,
                                                                Integer paginaActual,
                                                                Integer cantidad) throws Exception;

    public DireccionModel findDireccionById(Integer idDireccion) throws Exception;

    public DireccionModel addDireccion(DireccionModel direccionModel,
                                       ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;
}
