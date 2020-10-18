package com.corfid.fideicomisos.component.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.component.utilities.GenericConverter;
import com.corfid.fideicomisos.entity.administrativo.Email;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.EmailModel;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("emailConverter")
public class EmailConverter extends GenericConverter {

    @Autowired
    @Qualifier("personaServiceImpl")
    PersonaInterface personaInterface;

    public Email convertEmailModelToEmail(EmailModel emailModel) {
        Email email = new Email();

        email.setIdMail(emailModel.getIdMail());
        email.setDireccionMail(emailModel.getDireccionMail().toUpperCase());
        if (!StringUtil.isEmpty(emailModel.getIdPersona())) {
            Persona persona;
            persona = personaInterface.findPersonaById(StringUtil.toInteger(emailModel.getIdPersona()));
            email.setPersona(persona);
        }

        email.setEstadoRegistro(emailModel.getEstadoRegistro());

        return email;

    }

    public Email convertEmailModelToEmailExistente(Email email, EmailModel emailModel) {
        email.setIdMail(emailModel.getIdMail());
        email.setDireccionMail(emailModel.getDireccionMail().toUpperCase());

        if (!StringUtil.isEmpty(emailModel.getIdPersona())) {
            Persona persona;
            persona = personaInterface.findPersonaById(StringUtil.toInteger(emailModel.getIdPersona()));
            email.setPersona(persona);
        }

        email.setEstadoRegistro(emailModel.getEstadoRegistro());

        return email;

    }

    public EmailModel convertEmailToEmailModel(Email email) throws Exception {
        EmailModel emailModel = new EmailModel();
        try {

            emailModel.setIdMail(email.getIdMail());
            emailModel.setDireccionMail(email.getDireccionMail());
            emailModel.setEstadoRegistro(email.getEstadoRegistro());
            if (!StringUtil.isEmpty(email.getPersona())) {
                emailModel.setIdPersona(email.getPersona().getIdPersona());
            } else {
                emailModel.setIdPersona(null);
            }

            if (StringUtil.equiv(email.getEstadoRegistro(), "S")) {
                emailModel.setDescEstadoRegistro("Vigente");
            } else if (StringUtil.equiv(email.getEstadoRegistro(), "N")) {
                emailModel.setDescEstadoRegistro("No Vigente");
            } else {
                emailModel.setDescEstadoRegistro("Desconocido");
            }

            return emailModel;
        } catch (Exception e) {
            emailModel.setCodigoError(ConstantesError.ERROR_2);
            emailModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_2));
            return emailModel;
        }
    }
}
