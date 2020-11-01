package com.corfid.fideicomisos.component.utilities;

import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.entity.utilities.ConstantesSistema;
import com.corfid.fideicomisos.model.utilities.ConstantesSistemaModel;

@Component("constantesSistemaConverter")
public class ConstantesSistemaConverter {

    public ConstantesSistema convertConstantesSistemaModelToConstantesSistema(ConstantesSistemaModel constantesSistemaModel) {
        ConstantesSistema constantesSistema = new ConstantesSistema();
        constantesSistema.setIdConstraint(constantesSistemaModel.getIdConstraint());
        constantesSistema.setAbrevConstraint(constantesSistemaModel.getAbrevConstraint());
        constantesSistema.setValor(constantesSistemaModel.getValor());
        constantesSistema.setEstadoRegistro(constantesSistemaModel.getEstadoRegistro());
        constantesSistema.setNombreConstraint(constantesSistemaModel.getNombreConstraint());
        constantesSistema.setDescripcion(constantesSistemaModel.getDescripcion());

        return constantesSistema;
    }

    public ConstantesSistemaModel convertConstantesSistemaToConstantesSistemaModel(ConstantesSistema constantesSistema) {
        ConstantesSistemaModel constantesSistemaModel = new ConstantesSistemaModel();

        constantesSistemaModel.setIdConstraint(constantesSistema.getIdConstraint());
        constantesSistemaModel.setAbrevConstraint(constantesSistema.getAbrevConstraint());
        constantesSistemaModel.setValor(constantesSistema.getValor());
        constantesSistemaModel.setEstadoRegistro(constantesSistema.getEstadoRegistro());
        constantesSistemaModel.setNombreConstraint(constantesSistema.getNombreConstraint());
        constantesSistemaModel.setDescripcion(constantesSistema.getDescripcion());

        return constantesSistemaModel;
    }
}
