package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.RolConverter;
import com.corfid.fideicomisos.entity.administrativo.Rol;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.cruds.CrudRolModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.RolRepository;
import com.corfid.fideicomisos.service.administrativo.RolInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;

@Service("rolServiceImpl")
public class RolServiceImpl  extends AbstractService implements RolInterface {
	@Autowired
	@Qualifier("rolRepository")
	private RolRepository rolRepository;

	@Autowired
	@Qualifier("rolConverter")
	private RolConverter rolConverter;

	@Override
	public List<RolModel> listAllRoles() {
		List<Rol> listRol = rolRepository.findAll();
		List<RolModel> listRolModel = new ArrayList<RolModel>();

		for (Rol rol : listRol) {
			listRolModel.add(rolConverter.convertRolToRolModel(rol));
		}

		return listRolModel;
	}

	@Override
	public CrudRolModel listRolByDescripcionPaginado(String descripcion, Integer pagina, Integer cant) {
		List<Rol> listRol;
		List<RolModel> listRolModel = new ArrayList<RolModel>();
		Page<Rol> pageRol;
		CrudRolModel crudRolModel = new CrudRolModel();

		String cadenaRol = Constante.COMODIN_LIKE + descripcion + Constante.COMODIN_LIKE;

		pageRol = rolRepository.listRolByDescripcionPaginado(cadenaRol,
				obtenerIndexPorPagina(pagina, cant, "descripcion", true, false));

		listRol = pageRol.getContent();
		crudRolModel.setPaginaFinal(pageRol.getTotalPages());
		crudRolModel.setCantidadRegistros(_toInteger(pageRol.getTotalElements()));

		for (Rol rol : listRol) {
			listRolModel.add(rolConverter.convertRolToRolModel(rol));
		}

		crudRolModel.setRows(listRolModel);

		return crudRolModel;
	}

	@Override
	public Rol findRolById(Integer id) {
		return rolRepository.findByIdRol(id);
	}

	@Override
	public RolModel findRolByIdModel(Integer id) {
		return rolConverter.convertRolToRolModel(findRolById(id));
	}

	@Override
	public RolModel addRol(RolModel rolModel, ParametrosAuditoriaModel parametrosAuditoriaModel) {
		Rol rol = findRolById(rolModel.getIdRol());

		if (_isEmpty(rol)) {
			rol = rolConverter.convertRolModelToRol(rolModel);
			setInsercionAuditoria(rol, parametrosAuditoriaModel);
		} else {
			rol = rolConverter.convertRolModelToRolExistente(rol, rolModel);
			setModificacionAuditoria(rol, parametrosAuditoriaModel);
		}

		rol = rolRepository.save(rol);

		return rolConverter.convertRolToRolModel(rol);
	}

	@Override
	public void removeRol(Integer id) {
		Rol rol = findRolById(id);
		if (null != rol) {
			rolRepository.delete(rol);
		}
	}
}
