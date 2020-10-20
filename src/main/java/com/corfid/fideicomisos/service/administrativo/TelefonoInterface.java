package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.model.administrativo.TelefonoModel;
import com.corfid.fideicomisos.model.cruds.CrudTelefonoModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface TelefonoInterface {

    public CrudTelefonoModel findTelefonoByIdPersona(Integer idPersona,
                                                     Integer paginaActual,
                                                     Integer cantidad) throws Exception;

    public TelefonoModel findTelefonoById(Integer idTelefono) throws Exception;

    public TelefonoModel addTelefono(TelefonoModel telefonoModel,
                                     ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public List<TelefonoModel> findTelefonoByIdPersona(Integer idPersona) throws Exception;

    public String actualizarEstadoTelefonos(Integer idPersona) throws Exception;
}
