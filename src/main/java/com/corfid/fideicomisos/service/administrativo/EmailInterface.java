package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.model.administrativo.EmailModel;
import com.corfid.fideicomisos.model.cruds.CrudEmailModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface EmailInterface {

    public CrudEmailModel findEmailByIdPersona(Integer idPersona,
                                               Integer paginaActual,
                                               Integer cantidad) throws Exception;

    public EmailModel findEmailById(Integer idEmail) throws Exception;

    public EmailModel addEmail(EmailModel emailModel,
                               ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public List<EmailModel> findEmailByIdPersona(Integer idPersona) throws Exception;
}
