package com.corfid.fideicomisos.repository.banco;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.banco.DetalleMovCuentaEntidadFinanciera;

@Repository("detalleMovCuentaEntidadFinancieraRepository")
public interface DetalleMovCuentaEntidadFinancieraRepository
		extends JpaRepository<DetalleMovCuentaEntidadFinanciera, Serializable> {

	@Query(value = "SELECT model FROM DetalleMovCuentaEntidadFinanciera model "
			+ "WHERE model.movimientoCuentaEntidadFinanciera.identificadorMovimientoCuentaEntidadFinanciera = :identificadorMovimientoCuentaEntidadFinanciera "	
			+ "AND model.estadoRegistro = 'S'")
	public abstract List<DetalleMovCuentaEntidadFinanciera> getListDetalleMovCuentaEntidadFinanciera(
			@Param("identificadorMovimientoCuentaEntidadFinanciera") Integer identificadorMovimientoCuentaEntidadFinanciera);

}
