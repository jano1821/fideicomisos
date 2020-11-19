package com.corfid.fideicomisos.repository.flujoprocesodocumento;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.flujoprocesodocumento.DetalleDocumentoFideicomiso;

@Repository("detalleDocumentoFideicomisoRepository")
public interface DetalleDocumentoFideicomisoRepository
		extends JpaRepository<DetalleDocumentoFideicomiso, Serializable> {

	@Query(value = "SELECT model FROM DetalleDocumentoFideicomiso model "
			+ "WHERE model.documentoFideicomiso.identificadorDocumentoFideicomiso = :identificadorDocumentoFideicomiso "
			+ "AND model.estadoRegistro = 'S' "
			+ "ORDER BY model.ordenVisualizacion")
	public abstract List<DetalleDocumentoFideicomiso> getListDetalleDocumentoFideicomisoByIdDocumentoFideicomiso(
			@Param("identificadorDocumentoFideicomiso") Integer identificadorDocumentoFideicomiso);

}
