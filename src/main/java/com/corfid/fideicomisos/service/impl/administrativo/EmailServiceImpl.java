package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.DireccionConverter;
import com.corfid.fideicomisos.component.administrativo.EmailConverter;
import com.corfid.fideicomisos.entity.administrativo.Direccion;
import com.corfid.fideicomisos.entity.administrativo.Email;
import com.corfid.fideicomisos.model.administrativo.DireccionModel;
import com.corfid.fideicomisos.model.administrativo.EmailModel;
import com.corfid.fideicomisos.model.cruds.CrudDireccionModel;
import com.corfid.fideicomisos.model.cruds.CrudEmailModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.DireccionRepository;
import com.corfid.fideicomisos.repository.administrativo.EmailRepository;
import com.corfid.fideicomisos.service.administrativo.EmailInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.ConstantesError;

@Service("emailServiceImpl")
public class EmailServiceImpl extends AbstractService implements EmailInterface {

    @Autowired
    @Qualifier("emailRepository")
    EmailRepository emailRepository;

    /*
     * @Autowired
     * @Qualifier("personaServiceImpl") PersonaInterface personaInterface;
     */

    @Autowired
    @Qualifier("emailConverter")
    EmailConverter emailConverter;

    public CrudEmailModel findEmailByIdPersona(Integer idPersona,
                                               Integer paginaActual,
                                               Integer cantidad) throws Exception {
        CrudEmailModel crudEmailModel = null;
        List<EmailModel> listEmailModel = new ArrayList<EmailModel>();
        EmailModel emailModel;
        List<Email> listEmail;
        Page<Email> pageEmail;
        try {
            crudEmailModel = new CrudEmailModel();
            pageEmail = emailRepository.findEmailByIdPersona(idPersona,
                                                             obtenerIndexPorPagina(paginaActual,
                                                                                   cantidad,
                                                                                   "direccionMail",
                                                                                   true,
                                                                                   false));

            listEmail = pageEmail.getContent();
            crudEmailModel.setPaginaFinal(pageEmail.getTotalPages());
            crudEmailModel.setCantidadRegistros(_toInteger(pageEmail.getTotalElements()));

            for (Email email : listEmail) {
                emailModel = new EmailModel();
                emailModel = emailConverter.convertEmailToEmailModel(email);
                listEmailModel.add(emailModel);
            }

            crudEmailModel.setRows(listEmailModel);

            return crudEmailModel;
        } catch (Exception e) {
            return crudEmailModel;
        }
    }

    public EmailModel findEmailById(Integer idEmail) throws Exception {
        EmailModel emailModel = null;
        try {
            Email email;
            email = emailRepository.findByIdMail(idEmail);

            emailModel = emailConverter.convertEmailToEmailModel(email);

            return emailModel;
        } catch (Exception e) {
            return emailModel;
        }

    }

    public EmailModel addEmail(EmailModel emailModel,
                               ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        try {
            Email email;

            if (_isEmpty(emailModel.getIdMail())) {
                email = emailConverter.convertEmailModelToEmail(emailModel);
                setInsercionAuditoria(email, parametrosAuditoriaModel);
            } else {
                email = emailRepository.findByIdMail(emailModel.getIdMail());
                email = emailConverter.convertEmailModelToEmailExistente(email, emailModel);
                setModificacionAuditoria(email, parametrosAuditoriaModel);
            }

            email = emailRepository.save(email);

            if (!_isEmpty(email)) {
                emailModel = emailConverter.convertEmailToEmailModel(email);
                emailModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                emailModel.setCodigoError(ConstantesError.ERROR_28);
            }

            return emailModel;
        } catch (Exception e) {
            emailModel.setCodigoError(ConstantesError.ERROR_28);
            emailModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_28));
            return emailModel;
        }

    }
    
    public List<EmailModel> findEmailByIdPersona(Integer idPersona) throws Exception {
        List<EmailModel> listEmailModel = new ArrayList<EmailModel>();
        EmailModel emailModel;
        List<Email> listEmail;
        try {
            listEmail = emailRepository.findEmailVigenteByIdPersona(idPersona);

            for (Email email : listEmail) {
                emailModel = new EmailModel();
                emailModel = emailConverter.convertEmailToEmailModel(email);
                listEmailModel.add(emailModel);
            }

            return listEmailModel;
        } catch (Exception e) {
            return listEmailModel;
        }
    }
}
