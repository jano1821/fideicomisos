package com.corfid.fideicomisos.component.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.administrativo.Telefono;
import com.corfid.fideicomisos.component.utilities.GenericConverter;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.TelefonoModel;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("telefonoConverter")
public class TelefonoConverter extends GenericConverter {

    @Autowired
    @Qualifier("personaServiceImpl")
    PersonaInterface personaInterface;

    public Telefono convertTelefonoModelToTelefono(TelefonoModel telefonoModel) {
        Telefono telefono = new Telefono();

        telefono.setIdTelefono(telefonoModel.getIdTelefono());
        telefono.setNumero(telefonoModel.getNumero().toUpperCase());
        telefono.setOperador(telefonoModel.getOperador().toUpperCase());
        if (!StringUtil.isEmpty(telefonoModel.getIdPersona())) {
            Persona persona;
            persona = personaInterface.findPersonaById(StringUtil.toInteger(telefonoModel.getIdPersona()));
            telefono.setPersona(persona);
        }

        telefono.setEstadoRegistro(telefonoModel.getEstadoRegistro());

        return telefono;

    }

    public Telefono convertTelefonoModelToTelefonoExistente(Telefono telefono, TelefonoModel telefonoModel) {
        telefono.setIdTelefono(telefonoModel.getIdTelefono());
        telefono.setNumero(telefonoModel.getNumero());
        telefono.setOperador(telefonoModel.getOperador().toUpperCase());

        if (!StringUtil.isEmpty(telefonoModel.getPersona())) {
            Persona persona;
            persona = personaInterface.findPersonaById(StringUtil.toInteger(telefonoModel.getIdPersona()));
            telefono.setPersona(persona);
        }

        telefono.setEstadoRegistro(telefonoModel.getEstadoRegistro());

        return telefono;

    }

    public TelefonoModel convertTelefonoToTelefonoModel(Telefono telefono) throws Exception {
        TelefonoModel telefonoModel = new TelefonoModel();
        try {

            telefonoModel.setIdTelefono(telefono.getIdTelefono());
            telefonoModel.setNumero(telefono.getNumero());
            telefonoModel.setOperador(telefono.getOperador());

            telefonoModel.setEstadoRegistro(telefono.getEstadoRegistro());
            if (!StringUtil.isEmpty(telefono.getPersona())) {
                telefonoModel.setIdPersona(telefono.getPersona().getIdPersona());
                telefonoModel.setNombreCompletoPersona(telefono.getPersona().getNombreCompleto());
            } else {
                telefonoModel.setIdPersona(null);
                telefonoModel.setNombreCompletoPersona("");
            }

            if (StringUtil.equiv(telefono.getEstadoRegistro(), "S")) {
                telefonoModel.setDescEstadoRegistro("Vigente");
            } else if (StringUtil.equiv(telefono.getEstadoRegistro(), "N")) {
                telefonoModel.setDescEstadoRegistro("No Vigente");
            } else {
                telefonoModel.setDescEstadoRegistro("Desconocido");
            }

            return telefonoModel;
        } catch (Exception e) {
            telefonoModel.setCodigoError(ConstantesError.ERROR_2);
            telefonoModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_2));
            return telefonoModel;
        }
    }
}
