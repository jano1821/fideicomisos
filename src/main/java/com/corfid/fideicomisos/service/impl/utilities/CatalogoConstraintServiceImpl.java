package com.corfid.fideicomisos.service.impl.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.utilities.CatalogoConstraintConverter;
import com.corfid.fideicomisos.entity.utilities.CatalogoConstraint;
import com.corfid.fideicomisos.model.utilities.CatalogoConstraintModel;
import com.corfid.fideicomisos.repository.utilities.CatalogoConstraintRepository;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.utilities.Constante;

@Service("catalogoConstraintServiceImpl")
public class CatalogoConstraintServiceImpl implements CatalogoConstraintInterface {

	@Autowired
	@Qualifier("catalogoConstraintRepository")
	private CatalogoConstraintRepository catalogoConstraintRepository;

	@Autowired
	@Qualifier("catalogoConstraintConverter")
	private CatalogoConstraintConverter catalogoConstraintConverter;

	@Override
	public List<CatalogoConstraintModel> findByNombreTabla(String nombreTabla) {
		List<CatalogoConstraint> listCatalogoConstraint = catalogoConstraintRepository.findByNombreTabla(nombreTabla);
		List<CatalogoConstraintModel> listCatalogoConstraintModel = new ArrayList<CatalogoConstraintModel>();

		for (CatalogoConstraint catalogoConstraint : listCatalogoConstraint) {
			listCatalogoConstraintModel.add(
					catalogoConstraintConverter.convertCatalogoConstraintToCatalogoConstraintModel(catalogoConstraint));
		}

		return listCatalogoConstraintModel;
	}

	@Override
	public List<CatalogoConstraintModel> findByNombreTablaAndNombreCampo(String nombreTabla, String nombreCampo) {
		List<CatalogoConstraint> listCatalogoConstraint = catalogoConstraintRepository.findByNombreTablaAndNombreCampo(nombreTabla, nombreCampo);
		List<CatalogoConstraintModel> listCatalogoConstraintModel = new ArrayList<CatalogoConstraintModel>();

		for (CatalogoConstraint catalogoConstraint : listCatalogoConstraint) {
			listCatalogoConstraintModel.add(
					catalogoConstraintConverter.convertCatalogoConstraintToCatalogoConstraintModel(catalogoConstraint));
		}

		return listCatalogoConstraintModel;
	}
	
	public List<CatalogoConstraintModel> findByNombreTablaAndNombreCampo(String nombreTabla, String nombreCampo, String valorInicial) {
		List<CatalogoConstraint> listCatalogoConstraint = catalogoConstraintRepository
				.findByNombreTablaAndNombreCampo(nombreTabla, nombreCampo);
		List<CatalogoConstraintModel> listCatalogoConstraintModel = new ArrayList<CatalogoConstraintModel>();

		CatalogoConstraintModel catalogoConstraintModel = new CatalogoConstraintModel();
		catalogoConstraintModel.setDescConstraint(valorInicial);
		catalogoConstraintModel.setValorConstraint(Constante.CONST_VACIA);
		listCatalogoConstraintModel.add(catalogoConstraintModel);
		
		for (CatalogoConstraint catalogoConstraint : listCatalogoConstraint) {
			listCatalogoConstraintModel.add(
					catalogoConstraintConverter.convertCatalogoConstraintToCatalogoConstraintModel(catalogoConstraint));
		}

		return listCatalogoConstraintModel;
	}
}
