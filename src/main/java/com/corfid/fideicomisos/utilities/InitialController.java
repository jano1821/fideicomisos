package com.corfid.fideicomisos.utilities;

import java.util.Date;

import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public class InitialController {

	private Date fechaInsercion;

	private String usuarioInsercion;

	private String ipInsercion;

	private Date fechaModificacion;

	private String usuarioModificacion;

	private String ipModificacion;

	public Date getFechaInsercion() {
		return fechaInsercion;
	}

	protected void setFechaInsercion(Date fechaInsercion) {
		this.fechaInsercion = fechaInsercion;
	}

	protected String getUsuarioInsercion() {
		return usuarioInsercion;
	}

	protected void setUsuarioInsercion(String usuarioInsercion) {
		this.usuarioInsercion = usuarioInsercion;
	}

	protected String getIpInsercion() {
		return ipInsercion;
	}

	protected void setIpInsercion(String ipInsercion) {
		this.ipInsercion = ipInsercion;
	}

	protected Date getFechaModificacion() {
		return fechaModificacion;
	}

	protected void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	protected String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	protected void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	protected String getIpModificacion() {
		return ipModificacion;
	}

	protected void setIpModificacion(String ipModificacion) {
		this.ipModificacion = ipModificacion;
	}

	public InitialController(Date fechaInsercion, String usuarioInsercion, String ipInsercion, Date fechaModificacion,
			String usuarioModificacion, String ipModificacion) {
		super();
		this.fechaInsercion = fechaInsercion;
		this.usuarioInsercion = usuarioInsercion;
		this.ipInsercion = ipInsercion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
		this.ipModificacion = ipModificacion;
	}

	public InitialController() {

	}

	protected void setParametrosAuditoriaModel(Date fechaInsercion, String usuarioInsercion, String ipInsercion,
			Date fechaModificacion, String usuarioModificacion, String ipModificacion) {
		this.fechaInsercion = fechaInsercion;
		this.usuarioInsercion = usuarioInsercion;
		this.ipInsercion = ipInsercion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModificacion = usuarioModificacion;
		this.ipModificacion = ipModificacion;
	}

	protected ParametrosAuditoriaModel getParametrosAuditoriaModel() {
		ParametrosAuditoriaModel parametrosAuditoriaModel = new ParametrosAuditoriaModel();
		parametrosAuditoriaModel.setFechaInsercion(this.fechaInsercion);
		parametrosAuditoriaModel.setUsuarioInsercion(this.usuarioInsercion);
		parametrosAuditoriaModel.setIpInsercion(this.ipInsercion);
		parametrosAuditoriaModel.setFechaModificacion(this.fechaModificacion);
		parametrosAuditoriaModel.setUsuarioModificacion(this.usuarioModificacion);
		parametrosAuditoriaModel.setIpModificacion(this.ipModificacion);

		return parametrosAuditoriaModel;
	}

	protected Date getFechaAndHoraActual() {
		String dateString = "", hourString = "";

		dateString = FormatoFecha.obtenerFechaActual();
		hourString = FormatoFecha.obtenerHora();

		return FormatoFecha.stringToTimestampddMMyyyyhhmmss(dateString, hourString);
	}

	protected PaginadoModel obtenerMovimientoAndPagina(String paginaActual, String paginaFinal, String movIzquierda,
			String movDerecha) {
		PaginadoModel paginadoModel = new PaginadoModel();
		Integer nuevapaginaActual = 1;
		boolean izquierda = false;
		boolean derecha = false;

		if (StringUtil.equiv(movIzquierda, Constante.IZQUIERDA)) {
			if (StringUtil.equiv(paginaActual, Constante.PAGINA_INICIAL)) {
				nuevapaginaActual = StringUtil.toInteger(paginaActual);
				izquierda = true;
			} else {
				nuevapaginaActual = StringUtil.toInteger(paginaActual) - 1;
			}

		} else if (StringUtil.equiv(movDerecha, Constante.DERECHA)) {
			if (StringUtil.equiv(paginaActual, paginaFinal)) {
				nuevapaginaActual = StringUtil.toInteger(paginaActual);
				derecha = true;
			} else {
				nuevapaginaActual = StringUtil.toInteger(paginaActual) + 1;
			}
		} else {
			nuevapaginaActual = StringUtil.toInteger(paginaActual);
		}

		paginadoModel.setMovDerecha(derecha);
		paginadoModel.setMovIzquierda(izquierda);
		paginadoModel.setPaginaActual(nuevapaginaActual);

		return paginadoModel;
	}

}
