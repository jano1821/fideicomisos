package com.corfid.fideicomisos.component.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.component.utilities.GenericConverter;
import com.corfid.fideicomisos.entity.administrativo.Direccion;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.DireccionModel;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("direccionConverter")
public class DireccionConverter extends GenericConverter {

    @Autowired
    @Qualifier("personaServiceImpl")
    PersonaInterface personaInterface;

    public Direccion convertDireccionModelToDireccion(DireccionModel direccionModel) {
        Direccion direccion = new Direccion();

        direccion.setIdDireccion(direccionModel.getIdDireccion());
        direccion.setDireccion(direccionModel.getDireccion().toUpperCase());
        if (!StringUtil.isEmpty(direccionModel.getIdPersona())) {
            Persona persona;
            persona = personaInterface.findPersonaById(StringUtil.toInteger(direccionModel.getIdPersona()));
            direccion.setPersona(persona);
        }

        direccion.setEstadoRegistro(direccionModel.getEstadoRegistro());

        return direccion;

    }

    public Direccion convertDireccionModelToDireccionExistente(Direccion direccion, DireccionModel direccionModel) {
        direccion.setIdDireccion(direccionModel.getIdDireccion());
        direccion.setDireccion(direccionModel.getDireccion());

        if (!StringUtil.isEmpty(direccionModel.getPersona())) {
            Persona persona;
            persona = personaInterface.findPersonaById(StringUtil.toInteger(direccionModel.getIdPersona()));
            direccion.setPersona(persona);
        }

        direccion.setEstadoRegistro(direccionModel.getEstadoRegistro());

        return direccion;

    }

    public DireccionModel convertDireccionToDireccionModel(Direccion direccion) throws Exception {
        DireccionModel direccionModel = new DireccionModel();
        try {

            direccionModel.setIdDireccion(direccion.getIdDireccion());
            direccionModel.setDireccion(direccion.getDireccion());
            direccionModel.setEstadoRegistro(direccion.getEstadoRegistro());
            if (!StringUtil.isEmpty(direccion.getPersona())) {
                direccionModel.setIdPersona(direccion.getPersona().getIdPersona());
                direccionModel.setNombreCompletoPersona(direccion.getPersona().getNombreCompleto());
            } else {
                direccionModel.setIdPersona(null);
                direccionModel.setNombreCompletoPersona("");
            }

            if (StringUtil.equiv(direccion.getEstadoRegistro(), "S")) {
                direccionModel.setDescEstadoRegistro("Vigente");
            } else if (StringUtil.equiv(direccion.getEstadoRegistro(), "N")) {
                direccionModel.setDescEstadoRegistro("No Vigente");
            } else {
                direccionModel.setDescEstadoRegistro("Desconocido");
            }

            return direccionModel;
        } catch (Exception e) {
            direccionModel.setCodigoError(ConstantesError.ERROR_2);
            direccionModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_2));
            return direccionModel;
        }
    }
}
