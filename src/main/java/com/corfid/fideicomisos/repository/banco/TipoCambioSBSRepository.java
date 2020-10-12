package com.corfid.fideicomisos.repository.banco;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.corfid.fideicomisos.entity.banco.TipoCambioSBS;

@Repository("tipoCambioSBSRepository")
public interface TipoCambioSBSRepository extends JpaRepository<TipoCambioSBS, Serializable>{
	
	public abstract TipoCambioSBS findByFechaProceso(Date fechaProceso);

}
