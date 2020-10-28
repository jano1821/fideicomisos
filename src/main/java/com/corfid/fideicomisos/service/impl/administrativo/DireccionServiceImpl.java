package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.DireccionConverter;
import com.corfid.fideicomisos.component.administrativo.RolConverter;
import com.corfid.fideicomisos.entity.administrativo.Direccion;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.DireccionModel;
import com.corfid.fideicomisos.model.administrativo.PersonaModel;
import com.corfid.fideicomisos.model.cruds.CrudDireccionModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.DireccionRepository;
import com.corfid.fideicomisos.service.administrativo.DireccionInterface;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.ConstantesError;

@Service("direccionServiceImpl")
public class DireccionServiceImpl extends AbstractService implements DireccionInterface {

    @Autowired
    @Qualifier("direccionRepository")
    DireccionRepository direccionRepository;

    /*
     * @Autowired
     * @Qualifier("personaServiceImpl") PersonaInterface personaInterface;
     */

    @Autowired
    @Qualifier("direccionConverter")
    DireccionConverter direccionConverter;

    @Autowired
    @Qualifier("personaServiceImpl")
    PersonaInterface personaInterface;

    public CrudDireccionModel findDireccionByIdPersona(Integer idPersona,
                                                       Integer paginaActual,
                                                       Integer cantidad) throws Exception {
        CrudDireccionModel crudDireccionModel = null;
        List<DireccionModel> listDireccionModel = new ArrayList<DireccionModel>();
        DireccionModel direccionModel;
        List<Direccion> listDireccion;
        Page<Direccion> pageDireccion;
        try {
            crudDireccionModel = new CrudDireccionModel();
            pageDireccion = direccionRepository.findDireccionByIdPersona(idPersona,
                                                                         obtenerIndexPorPagina(paginaActual,
                                                                                               cantidad,
                                                                                               "direccion",
                                                                                               true,
                                                                                               false));

            listDireccion = pageDireccion.getContent();
            crudDireccionModel.setPaginaFinal(pageDireccion.getTotalPages());
            crudDireccionModel.setCantidadRegistros(_toInteger(pageDireccion.getTotalElements()));
            PersonaModel personaModel = personaInterface.findPersonaByIdModel(idPersona);
            crudDireccionModel.setNombreCompletoPersona(personaModel.getNombreCompleto());

            for (Direccion direccion : listDireccion) {
                direccionModel = new DireccionModel();
                direccionModel = direccionConverter.convertDireccionToDireccionModel(direccion);
                listDireccionModel.add(direccionModel);
            }

            crudDireccionModel.setRows(listDireccionModel);

            return crudDireccionModel;
        } catch (Exception e) {
            return crudDireccionModel;
        }
    }

    public DireccionModel findDireccionById(Integer idDireccion) throws Exception {
        DireccionModel direccionModel = null;
        try {
            Direccion direccion;
            direccion = direccionRepository.findByIdDireccion(idDireccion);

            direccionModel = direccionConverter.convertDireccionToDireccionModel(direccion);

            return direccionModel;
        } catch (Exception e) {
            return direccionModel;
        }

    }

    public DireccionModel addDireccion(DireccionModel direccionModel,
                                       ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        try {
            Direccion direccion;

            if (_isEmpty(direccionModel.getIdDireccion())) {
                direccion = direccionConverter.convertDireccionModelToDireccion(direccionModel);
                setInsercionAuditoria(direccion, parametrosAuditoriaModel);
            } else {
                direccion = direccionRepository.findByIdDireccion(direccionModel.getIdDireccion());
                direccion = direccionConverter.convertDireccionModelToDireccionExistente(direccion, direccionModel);
                setModificacionAuditoria(direccion, parametrosAuditoriaModel);
            }

            direccion = direccionRepository.save(direccion);

            if (!_isEmpty(direccion)) {
                direccionModel = direccionConverter.convertDireccionToDireccionModel(direccion);
                direccionModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                direccionModel.setCodigoError(ConstantesError.ERROR_27);
            }

            return direccionModel;
        } catch (Exception e) {
            direccionModel.setCodigoError(ConstantesError.ERROR_27);
            direccionModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_27));
            return direccionModel;
        }

    }
}
