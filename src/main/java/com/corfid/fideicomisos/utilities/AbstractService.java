package com.corfid.fideicomisos.utilities;

import java.util.Date;

import com.corfid.fideicomisos.entity.auditoria.Auditoria;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public class AbstractService {
	protected Long _toLong(Object objeto) {
		return StringUtil.toLong(objeto);
	}

	protected Integer _toInteger(Object objeto) {
		return StringUtil.toInteger(objeto);
	}

	protected Short _toShort(Object objeto) {
		return StringUtil.toShort(objeto);
	}

	protected Double _toDouble(Object objeto) {
		return StringUtil.toDouble(objeto);
	}

	protected Float _toFloat(Object objeto) {
		return StringUtil.toFloat(objeto);
	}

	protected String _toBlank(String cadena) {
		return StringUtil.toBlank(cadena);
	}

	protected String _toBlankObject(Object object) {
		return StringUtil.toBlankObject(object);
	}

	protected String _toStr(Object object) {
		return StringUtil.toStr(object);
	}

	protected boolean _isEmpty(Object object) {
		return StringUtil.isEmpty(object);
	}

	protected String _subStr(String cadena, int indexFin) {
		return StringUtil.subStr(cadena, indexFin);
	}

	protected String _subStr(String cadena, int indexInicio, int indexFin) {
		return StringUtil.subStr(cadena, indexInicio, indexFin);
	}

	protected String _isEmpty(String object, String defaultValue) {
		return _isEmpty(object) ? defaultValue : object;
	}

	protected boolean _equiv(Object cadena1, Object cadena2) {
		return StringUtil.equiv(cadena1, cadena2);
	}

	protected boolean _inList(String cadena, String... valores) {
		return StringUtil.inList(cadena, valores);
	}

	/**
	 * Metodo setea los campos de Auditoria de Insercion a una entidad
	 *
	 * @param auditable
	 * @param beanParametrosAuditoria
	 */
	protected void setInsercionAuditoria(Auditoria auditoria, ParametrosAuditoriaModel parametrosAuditoriaModel) {
		Date fechaInsercion;
		String usuarioInsercion;
		String ipInsercion;

		fechaInsercion = parametrosAuditoriaModel.getFechaInsercion();
		usuarioInsercion = parametrosAuditoriaModel.getUsuarioInsercion();
		ipInsercion = parametrosAuditoriaModel.getIpInsercion();

		auditoria.setFechaInsercion(fechaInsercion);
		auditoria.setUsuarioInsercion(usuarioInsercion);
		auditoria.setIpInsercion(ipInsercion);
	}

	/**
	 * Metodo setea los campos de Auditoria de modificacion a una entidad
	 *
	 * @param auditable
	 * @param beanParametrosAuditoria
	 */
	protected void setModificacionAuditoria(Auditoria auditoria, ParametrosAuditoriaModel parametrosAuditoriaModel) {
		Date fechaModificacion;
		String usuarioModificacion;
		String ipModificacion;

		fechaModificacion = parametrosAuditoriaModel.getFechaModificacion();
		usuarioModificacion = parametrosAuditoriaModel.getUsuarioModificacion();
		ipModificacion = parametrosAuditoriaModel.getIpModificacion();

		auditoria.setFechaModificacion(fechaModificacion);
		auditoria.setUsuarioModificacion(usuarioModificacion);
		auditoria.setIpModificacion(ipModificacion);
	}
}