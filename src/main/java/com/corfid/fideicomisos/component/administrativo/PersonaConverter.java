package com.corfid.fideicomisos.component.administrativo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.component.utilities.GenericConverter;
import com.corfid.fideicomisos.entity.administrativo.Empresa;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.model.administrativo.ClienteModel;
import com.corfid.fideicomisos.model.administrativo.EmpresaModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.service.administrativo.ClienteInterface;
import com.corfid.fideicomisos.service.administrativo.EmpresaInterface;
import com.corfid.fideicomisos.service.impl.utilities.ErrorControladoException;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("personaConverter")
public class PersonaConverter extends GenericConverter {

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteInterface clienteInterface;

    @Autowired
    @Qualifier("empresaServiceImpl")
    private EmpresaInterface empresaInterface;

    public Persona convertPersonaModelToPersona(PersonaModel personaModel) {
        Persona persona = new Persona();
        persona.setIdPersona(personaModel.getIdPersona());
        persona.setNombres(StringUtil.toUpperBlank(personaModel.getNombres()));
        persona.setApePat(StringUtil.toUpperBlank(personaModel.getApePat()));
        persona.setApeMat(StringUtil.toUpperBlank(personaModel.getApeMat()));
        if (!StringUtil.isEmpty(personaModel.getRazonSocial())) {
            persona.setRazonSocial(StringUtil.toUpperBlank(personaModel.getRazonSocial()));
        }

        if (StringUtil.equiv(StringUtil.toUpperBlank(personaModel.getDescTipoPersona()), "NATURAL")) {
            persona.setTipoPersona(Constante.TIPO_PERSONA_NATURAL);
        } else if (StringUtil.equiv(StringUtil.toUpperBlank(personaModel.getDescTipoPersona()), "JURIDICA")) {
            persona.setTipoPersona(Constante.TIPO_PERSONA_JURIDICA);
        } else {
            persona.setTipoPersona("D");
        }

        if (StringUtil.equiv(persona.getTipoPersona(), Constante.TIPO_PERSONA_NATURAL)) {
            persona.setNombreCompleto(StringUtil.toUpperBlank(personaModel.getNombres() + " " + personaModel.getApePat() + " " + personaModel.getApeMat()));
        } else {
            persona.setNombreCompleto(StringUtil.toUpperBlank(personaModel.getRazonSocial()));
        }
        persona.setTipoDocumento(personaModel.getTipoDocumento());
        persona.setNumeroDocumento(personaModel.getNumeroDocumento());
        persona.setEstadoRegistro(personaModel.getEstadoRegistro());
        persona.setPermiteVinculacion(personaModel.getPermiteVinculacion());
        persona.setPermiteVinculacionCliente(personaModel.getPermiteVinculacionCliente());
        persona.setIdUsuarioRegistro(personaModel.getIdUsuarioRegistro());

        return persona;

    }

    public Persona convertPersonaModelToPersonaExistente(Persona persona, PersonaModel personaModel) {
        persona.setIdPersona(personaModel.getIdPersona());
        persona.setNombres(StringUtil.toUpperBlank(personaModel.getNombres()));
        persona.setApePat(StringUtil.toUpperBlank(personaModel.getApePat()));
        persona.setApeMat(StringUtil.toUpperBlank(personaModel.getApeMat()));
        persona.setRazonSocial(StringUtil.toUpperBlank(personaModel.getRazonSocial()));

        if (StringUtil.equiv(StringUtil.toUpperBlank(personaModel.getDescTipoPersona()), "NATURAL")) {
            persona.setTipoPersona(Constante.TIPO_PERSONA_NATURAL);
        } else if (StringUtil.equiv(StringUtil.toUpperBlank(personaModel.getDescTipoPersona()), "JURIDICA")) {
            persona.setTipoPersona(Constante.TIPO_PERSONA_JURIDICA);
        } else {
            persona.setTipoPersona("D");
        }

        if (StringUtil.equiv(persona.getTipoPersona(), Constante.TIPO_PERSONA_NATURAL)) {
            persona.setNombreCompleto(personaModel.getNombres().toUpperCase() + " " + personaModel.getApePat().toUpperCase() + " " + personaModel.getApeMat().toUpperCase());
        } else {
            persona.setNombreCompleto(personaModel.getRazonSocial().toUpperCase());
        }
        persona.setTipoDocumento(personaModel.getTipoDocumento());
        persona.setNumeroDocumento(personaModel.getNumeroDocumento().toUpperCase());
        persona.setEstadoRegistro(personaModel.getEstadoRegistro());
        persona.setPermiteVinculacion(personaModel.getPermiteVinculacion());
        persona.setPermiteVinculacionCliente(personaModel.getPermiteVinculacionCliente());
        persona.setIdUsuarioRegistro(personaModel.getIdUsuarioRegistro());

        return persona;

    }

    public PersonaModel convertPersonaToPersonaModel(Persona persona) throws Exception {
        List<EmpresaModel> listEmpresaModel = new ArrayList<EmpresaModel>();
        ClienteModel clienteModel;
        PersonaModel personaModel = new PersonaModel();

        try {
            personaModel.setIdPersona(persona.getIdPersona());
            personaModel.setNombres(persona.getNombres().toUpperCase());
            personaModel.setApePat(persona.getApePat().toUpperCase());
            personaModel.setApeMat(persona.getApeMat().toUpperCase());
            if (!StringUtil.isEmpty(persona.getRazonSocial())) {
                personaModel.setRazonSocial(persona.getRazonSocial().toUpperCase());
            }
            personaModel.setTipoPersona(persona.getTipoPersona().toUpperCase());
            personaModel.setEstadoRegistro(persona.getEstadoRegistro());
            personaModel.setNombreCompleto(persona.getNombreCompleto().toUpperCase());
            personaModel.setTipoDocumento(persona.getTipoDocumento().toUpperCase());
            personaModel.setDescTipoDocumento(persona.getTipoDocumento().toUpperCase());
            personaModel.setNumeroDocumento(persona.getNumeroDocumento().toUpperCase());
            personaModel.setPermiteVinculacion(persona.getPermiteVinculacion());
            personaModel.setPermiteVinculacionCliente(persona.getPermiteVinculacionCliente());

            clienteModel = clienteInterface.findByIdCliente(persona.getIdPersona());
            if (!StringUtil.isEmpty(clienteModel)) {
                if (!StringUtil.isEmpty(clienteModel.getListEmpresas())) {
                    listEmpresaModel = clienteModel.getListEmpresas();
                }
            }

            personaModel.setListEmpresa(listEmpresaModel);

            if (!StringUtil.isEmpty(clienteInterface.findByIdCliente(persona.getIdPersona()))) {
                personaModel.setCliente(Constante.SI_ES_CLIENTE);
            } else {
                personaModel.setCliente(Constante.NO_ES_CLIENTE);
            }

            if (StringUtil.equiv(persona.getTipoPersona(), "N")) {
                personaModel.setDescTipoPersona("Natural");
            } else if (StringUtil.equiv(persona.getTipoPersona(), "J")) {
                personaModel.setDescTipoPersona("Juridica");
            } else {
                personaModel.setDescTipoPersona("Desconocido");
            }

            if (StringUtil.equiv(persona.getEstadoRegistro(), "S")) {
                personaModel.setDescEstadoRegistro("Vigente");
            } else if (StringUtil.equiv(persona.getEstadoRegistro(), "N")) {
                personaModel.setDescEstadoRegistro("No Vigente");
            } else {
                personaModel.setDescEstadoRegistro("Desconocido");
            }

            return personaModel;
        } catch (ErrorControladoException e) {
            personaModel.setCodigoError(ConstantesError.ERROR_4);
            personaModel.setDescripcionError(ConstantesError.ERROR_4);
            return personaModel;
        } catch (Exception e) {
            personaModel.setCodigoError(ConstantesError.ERROR_1);
            personaModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_1));
            return personaModel;
        }
    }

}
