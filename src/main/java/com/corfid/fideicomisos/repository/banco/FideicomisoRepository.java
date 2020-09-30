package com.corfid.fideicomisos.repository.banco;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.banco.Fideicomiso;

@Repository("fideicomisoRepository")
public interface FideicomisoRepository extends JpaRepository<Fideicomiso, Serializable> {

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_desenf, CUFI.c_desmon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", 
		   countQuery = "SELECT COUNT(*) FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
					+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
					+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
					+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S'", 
		   nativeQuery = true)
	public abstract Page<Object> getListFideicomisoCuentaEntidadFinancieraByIdFideicomisario(
			@Param("identificadorFideicomisario") String identificadorFideicomisario, Pageable pageable);
	
	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_desenf, CUFI.c_desmon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "			
			+ "INNER JOIN bandfife FIFE ON FIFE.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmfite FITE ON FITE.n_idfite = FIFE.n_idfite "			
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FITE.n_idfite = :identificadorFideicomitente"
			+ "AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", 
		   countQuery = "SELECT COUNT(*) FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
					+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
					+ "INNER JOIN bandfife FIFE ON FIFE.n_idfico = FICO.n_idfico "
					+ "INNER JOIN banmfite FITE ON FITE.n_idfite = FIFE.n_idfite "
					+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
					+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FITE.n_idfite = :identificadorFideicomitente"
					+ "AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S'",  
		   nativeQuery = true)
	public abstract Page<Object> getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioIdFideicomitente(
			@Param("identificadorFideicomisario") String identificadorFideicomisario,
			@Param("identificadorFideicomitente") String identificadorFideicomitente, 			
			Pageable pageable);

}
