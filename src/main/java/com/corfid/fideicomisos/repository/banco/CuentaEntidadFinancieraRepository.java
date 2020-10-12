package com.corfid.fideicomisos.repository.banco;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.banco.CuentaEntidadFinanciera;

@Repository("cuentaEntidadFinancieraRepository")
public interface CuentaEntidadFinancieraRepository extends JpaRepository<CuentaEntidadFinanciera, Serializable> {

	public abstract CuentaEntidadFinanciera findByIdentificadorCuentaEntidadFinanciera(Integer identificadorCuentaEntidadFinanciera);
	
}
