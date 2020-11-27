package com.corfid.fideicomisos.repository.banco;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.corfid.fideicomisos.entity.banco.TipoCambioSBS;

@Repository("tipoCambioSBSRepository")
public interface TipoCambioSBSRepository extends JpaRepository<TipoCambioSBS, Serializable>{
	
	public abstract TipoCambioSBS findByFechaProceso(Date fechaProceso);
	
	@Query(value = "SELECT tica FROM TipoCambioSBS tica WHERE tica.estadoRegistro = 'S' ORDER BY tica.fechaProceso DESC")
	public abstract List<TipoCambioSBS> getListTipoCambioSBS();

}
