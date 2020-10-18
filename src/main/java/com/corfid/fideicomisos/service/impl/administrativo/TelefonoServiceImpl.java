package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.TelefonoConverter;
import com.corfid.fideicomisos.entity.administrativo.Telefono;
import com.corfid.fideicomisos.model.administrativo.TelefonoModel;
import com.corfid.fideicomisos.model.cruds.CrudTelefonoModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.TelefonoRepository;
import com.corfid.fideicomisos.service.administrativo.TelefonoInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.ConstantesError;

@Service("telefonoServiceImpl")
public class TelefonoServiceImpl extends AbstractService implements TelefonoInterface {

    @Autowired
    @Qualifier("telefonoRepository")
    TelefonoRepository telefonoRepository;

    @Autowired
    @Qualifier("telefonoConverter")
    TelefonoConverter telefonoConverter;

    public CrudTelefonoModel findTelefonoByIdPersona(Integer idPersona,
                                                     Integer paginaActual,
                                                     Integer cantidad) throws Exception {
        CrudTelefonoModel crudTelefonoModel = null;
        List<TelefonoModel> listTelefonoModel = new ArrayList<TelefonoModel>();
        TelefonoModel telefonoModel;
        List<Telefono> listTelefono;
        Page<Telefono> pageTelefono;
        try {
            crudTelefonoModel = new CrudTelefonoModel();
            pageTelefono = telefonoRepository.findTelefonoByIdPersona(idPersona,
                                                                      obtenerIndexPorPagina(paginaActual,
                                                                                            cantidad,
                                                                                            "numero",
                                                                                            true,
                                                                                            false));

            listTelefono = pageTelefono.getContent();
            crudTelefonoModel.setPaginaFinal(pageTelefono.getTotalPages());
            crudTelefonoModel.setCantidadRegistros(_toInteger(pageTelefono.getTotalElements()));

            for (Telefono telefono : listTelefono) {
                telefonoModel = new TelefonoModel();
                telefonoModel = telefonoConverter.convertTelefonoToTelefonoModel(telefono);
                listTelefonoModel.add(telefonoModel);
            }

            crudTelefonoModel.setRows(listTelefonoModel);

            return crudTelefonoModel;
        } catch (Exception e) {
            return crudTelefonoModel;
        }
    }

    public TelefonoModel findTelefonoById(Integer idTelefono) throws Exception {
        TelefonoModel telefonoModel = null;
        try {
            Telefono telefono;
            telefono = telefonoRepository.findByIdTelefono(idTelefono);

            telefonoModel = telefonoConverter.convertTelefonoToTelefonoModel(telefono);

            return telefonoModel;
        } catch (Exception e) {
            return telefonoModel;
        }

    }

    public TelefonoModel addTelefono(TelefonoModel telefonoModel,
                                     ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        try {
            Telefono telefono;

            if (_isEmpty(telefonoModel.getIdTelefono())) {
                telefono = telefonoConverter.convertTelefonoModelToTelefono(telefonoModel);
                setInsercionAuditoria(telefono, parametrosAuditoriaModel);
            } else {
                telefono = telefonoRepository.findByIdTelefono(telefonoModel.getIdTelefono());
                telefono = telefonoConverter.convertTelefonoModelToTelefonoExistente(telefono, telefonoModel);
                setModificacionAuditoria(telefono, parametrosAuditoriaModel);
            }

            telefono = telefonoRepository.save(telefono);

            if (!_isEmpty(telefono)) {
                telefonoModel = telefonoConverter.convertTelefonoToTelefonoModel(telefono);
                telefonoModel.setCodigoError(ConstantesError.ERROR_0);
            } else {
                telefonoModel.setCodigoError(ConstantesError.ERROR_29);
            }

            return telefonoModel;
        } catch (Exception e) {
            telefonoModel.setCodigoError(ConstantesError.ERROR_29);
            telefonoModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_29));
            return telefonoModel;
        }

    }

    public List<TelefonoModel> findTelefonoByIdPersona(Integer idPersona) throws Exception {
        List<TelefonoModel> listTelefonoModel = new ArrayList<TelefonoModel>();
        TelefonoModel telefonoModel;
        List<Telefono> listTelefono;
        try {
            listTelefono = telefonoRepository.findTelefonoVigenteByIdPersona(idPersona);

            for (Telefono telefono : listTelefono) {
                telefonoModel = new TelefonoModel();
                telefonoModel = telefonoConverter.convertTelefonoToTelefonoModel(telefono);
                listTelefonoModel.add(telefonoModel);
            }


            return listTelefonoModel;
        } catch (Exception e) {
            return listTelefonoModel;
        }
    }
}
