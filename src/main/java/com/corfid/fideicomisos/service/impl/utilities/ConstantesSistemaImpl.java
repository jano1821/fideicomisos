package com.corfid.fideicomisos.service.impl.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.utilities.ConstantesSistemaConverter;
import com.corfid.fideicomisos.entity.utilities.ConstantesSistema;
import com.corfid.fideicomisos.model.utilities.ConstantesSistemaModel;
import com.corfid.fideicomisos.repository.utilities.ConstantesSistemaRepository;
import com.corfid.fideicomisos.service.utilities.ConstantesSistemaInterface;
import com.corfid.fideicomisos.utilities.ConstantesError;

@Service("constantesSistemaImpl")
public class ConstantesSistemaImpl implements ConstantesSistemaInterface{

    @Autowired
    @Qualifier("constantesSistemaRepository")
    ConstantesSistemaRepository constantesSistemaRepository;

    @Autowired
    @Qualifier("constantesSistemaConverter")
    ConstantesSistemaConverter constantesSistemaConverter;

    public ConstantesSistemaModel obtenerConstanteByNombre(String nombreConstante) throws Exception {
        ConstantesSistemaModel constantesSistemaModel = new ConstantesSistemaModel();
        ConstantesSistema constantesSistema;

        try {
            constantesSistema = constantesSistemaRepository.findByNombreConstraint(nombreConstante);

            constantesSistemaModel = constantesSistemaConverter.convertConstantesSistemaToConstantesSistemaModel(constantesSistema);
            constantesSistemaModel.setCodigoError(ConstantesError.ERROR_0);
        } catch (Exception e) {
            constantesSistemaModel.setCodigoError(ConstantesError.ERROR_1);
        }
        return constantesSistemaModel;
    }
}
