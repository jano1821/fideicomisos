package com.corfid.fideicomisos.repository.banco;

import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.banco.MovimientoCuentaEntidadFinanciera;

@Repository("movimientoCuentaEntidadFinancieraRepository")
public interface MovimientoCuentaEntidadFinancieraRepository
		extends JpaRepository<MovimientoCuentaEntidadFinanciera, Serializable> {

	@Query(value = "SELECT model FROM MovimientoCuentaEntidadFinanciera model "
			+ "WHERE model.cuentaEntidadFinanciera.identificadorCuentaEntidadFinanciera = :identificadorCuentaEntidadFinanciera "
			+ "AND model.estadoRegistro = 'S' ORDER BY model.fechaProcesoMovimiento DESC", 
			countQuery = "SELECT COUNT(model) FROM MovimientoCuentaEntidadFinanciera model "
					+ "WHERE model.cuentaEntidadFinanciera.identificadorCuentaEntidadFinanciera = :identificadorCuentaEntidadFinanciera "
					+ "AND model.estadoRegistro = 'S'")
	public abstract Page<MovimientoCuentaEntidadFinanciera> getListMovimientoCuentaEntidadFinancieraByIdCuenta(
			@Param("identificadorCuentaEntidadFinanciera") Integer identificadorCuentaEntidadFinanciera,
			Pageable pageable);

	public abstract MovimientoCuentaEntidadFinanciera findByIdentificadorMovimientoCuentaEntidadFinanciera(
			Integer identificadorMovimientoCuentaEntidadFinanciera);

}
