package com.corfid.fideicomisos.repository.banco;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.banco.Fideicomiso;

@Repository("fideicomisoRepository")
public interface FideicomisoRepository extends JpaRepository<Fideicomiso, Serializable> {

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_acoenf, CUFI.c_isomon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac, CUFI.n_idcufi "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", countQuery = "SELECT COUNT(*) FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
					+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
					+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
					+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S'", nativeQuery = true)
	public abstract Page<Object> getListFideicomisoCuentaEntidadFinancieraByIdFideicomisario(
			@Param("identificadorFideicomisario") Integer identificadorFideicomisario, Pageable pageable);

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_acoenf, CUFI.c_isomon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac, CUFI.n_idcufi "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.c_nomfid LIKE :nombreFideicomiso "
			+ "AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", countQuery = "SELECT COUNT(*) FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
					+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
					+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
					+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.c_nomfid LIKE :nombreFideicomiso "
					+ "AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S'", nativeQuery = true)
	public abstract Page<Object> getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioNombreFideicomiso(
			@Param("identificadorFideicomisario") Integer identificadorFideicomisario,
			@Param("nombreFideicomiso") String nombreFideicomiso, Pageable pageable);

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_acoenf, CUFI.c_isomon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac, CUFI.n_idcufi "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND CUFI.c_codmon = :codigoMoneda "
			+ "AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", countQuery = "SELECT COUNT(*) FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
					+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
					+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
					+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND CUFI.c_codmon = :codigoMoneda "
					+ "AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S'", nativeQuery = true)
	public abstract Page<Object> getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMoneda(
			@Param("identificadorFideicomisario") Integer identificadorFideicomisario,
			@Param("codigoMoneda") String codigoMoneda, Pageable pageable);

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_acoenf, CUFI.c_isomon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac, CUFI.n_idcufi "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.c_nomfid LIKE :nombreFideicomiso "
			+ "AND CUFI.c_codmon = :codigoMoneda AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", countQuery = "SELECT COUNT(*) FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
					+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
					+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
					+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.c_nomfid LIKE :nombreFideicomiso "
					+ "AND CUFI.c_codmon = :codigoMoneda AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S'", nativeQuery = true)
	public abstract Page<Object> getListFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMonedaNombreFideicomiso(
			@Param("identificadorFideicomisario") Integer identificadorFideicomisario,
			@Param("codigoMoneda") String codigoMoneda, @Param("nombreFideicomiso") String nombreFideicomiso,
			Pageable pageable);

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_acoenf, CUFI.c_isomon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac, CUFI.n_idcufi, CUFI.c_codmon "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", nativeQuery = true)
	public abstract List<Object> getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisario(
			@Param("identificadorFideicomisario") Integer identificadorFideicomisario);

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_acoenf, CUFI.c_isomon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac, CUFI.n_idcufi, CUFI.c_codmon "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.c_nomfid LIKE :nombreFideicomiso "
			+ "AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", nativeQuery = true)
	public abstract List<Object> getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioNombreFideicomiso(
			@Param("identificadorFideicomisario") Integer identificadorFideicomisario,
			@Param("nombreFideicomiso") String nombreFideicomiso);

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_acoenf, CUFI.c_isomon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac, CUFI.n_idcufi, CUFI.c_codmon "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND CUFI.c_codmon = :codigoMoneda "
			+ "AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", nativeQuery = true)
	public abstract List<Object> getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMoneda(
			@Param("identificadorFideicomisario") Integer identificadorFideicomisario,
			@Param("codigoMoneda") String codigoMoneda);

	@Query(value = "SELECT FICO.n_idfico, FICO.c_nomfid, CUFI.c_acoenf, CUFI.c_isomon, CUFI.c_numcta, "
			+ "CUFI.c_descef, CUFI.n_salcon, CUFI.n_saldis, CUFI.d_ulfeac, CUFI.n_idcufi, CUFI.c_codmon "
			+ "FROM banmfisa FISA INNER JOIN bandfifo FIFO ON FISA.n_idfisa = FIFO.n_idfisa "
			+ "INNER JOIN banmfico FICO ON FIFO.n_idfico = FICO.n_idfico "
			+ "INNER JOIN banmcufi CUFI ON FICO.n_idfico = CUFI.n_idfico "
			+ "WHERE FISA.n_idfisa = :identificadorFideicomisario AND FICO.c_nomfid LIKE :nombreFideicomiso "
			+ "AND CUFI.c_codmon = :codigoMoneda AND FICO.a_estreg = 'S' AND CUFI.a_estreg = 'S' ORDER BY FICO.n_idfico", nativeQuery = true)
	public abstract List<Object> getListSaldoTotalMonedaFideicomisoCuentaEntidadFinancieraByIdFideicomisarioMonedaNombreFideicomiso(
			@Param("identificadorFideicomisario") Integer identificadorFideicomisario,
			@Param("codigoMoneda") String codigoMoneda, @Param("nombreFideicomiso") String nombreFideicomiso);
	
    @Query(value = "SELECT fico FROM Fideicomiso fico " +
                	"INNER JOIN FideicomisoFideicomisario fifo "+
                    "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "+
                    "INNER JOIN Fideicomisario fisa "+
                    "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "+
                    "WHERE fisa.numeroDocumento = :rucFideicomisario")
    public abstract List<Fideicomiso> getListFideicomisoByRucFideicomisario(@Param("rucFideicomisario") String rucFideicomisario);

}
