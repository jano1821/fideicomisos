package com.corfid.fideicomisos.repository.flujoprocesodocumento;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.flujoprocesodocumento.ActividadDetalleDocumentoFideicomiso;

@Repository("actividadDetalleDocumentoFideicomisoRepository")
public interface ActividadDetalleDocumentoFideicomisoRepository
		extends JpaRepository<ActividadDetalleDocumentoFideicomiso, Serializable> {

	@Query(value = "SELECT acdf FROM DocumentoFideicomiso dofi " +
				   "INNER JOIN DetalleDocumentoFideicomiso acdo " +
				   "ON acdo.documentoFideicomiso.identificadorDocumentoFideicomiso = dofi.identificadorDocumentoFideicomiso " +
				   "INNER JOIN ActividadDetalleDocumentoFideicomiso acdf " +
				   "ON acdf.identificadorActividadDetalleDocumentoFideicomiso = acdo.actividadDetalleDocumentoFideicomiso.identificadorActividadDetalleDocumentoFideicomiso " +
				   "WHERE dofi.identificadorDocumentoFideicomiso = :identificadorDocumentoFideicomiso AND acdo.estadoRegistro = 'S' " +
				   "AND acdf.tipoDocumento = dofi.tipoDocumento AND acdf.estadoRegistro = 'S' " +
				   "ORDER BY acdo.ordenVisualizacion ASC")
	public abstract List<ActividadDetalleDocumentoFideicomiso> getListActividadDetalleDocumentoFideicomisoByIdDocumentoFideicomiso(
			@Param("identificadorDocumentoFideicomiso") Integer identificadorDocumentoFideicomiso);

}
