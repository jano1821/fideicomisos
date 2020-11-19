package com.corfid.fideicomisos.repository.flujoprocesodocumento;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.eeff.EstadosFinancieros;
import com.corfid.fideicomisos.entity.flujoprocesodocumento.DocumentoFideicomiso;

@Repository("documentoFideicomisoRepository")
public interface DocumentoFideicomisoRepository extends JpaRepository<DocumentoFideicomiso, Serializable> {
	
	@Query(value = "SELECT model FROM DocumentoFideicomiso model "
			+ "WHERE model.fideicomiso.identificadorFideicomiso = :identificadorFideicomiso "
			+ "AND model.tipoDocumento = :tipoDocumento AND model.estadoRegistro = 'S'",
			countQuery = "SELECT COUNT(model) FROM DocumentoFideicomiso model "
					+ "WHERE model.fideicomiso.identificadorFideicomiso = :identificadorFideicomiso "
					+ "AND model.tipoDocumento = :tipoDocumento AND model.estadoRegistro = 'S'")
	public abstract Page<DocumentoFideicomiso> getListDocumentoFideicomisoByIdFideicomisoTipoDocumento(
			@Param("identificadorFideicomiso") Integer identificadorFideicomiso,
			@Param("tipoDocumento") String tipoDocumento,
			Pageable pageable);
	
	 public abstract DocumentoFideicomiso findByIdentificadorDocumentoFideicomiso(Integer identificadorDocumentoFideicomiso);

}
