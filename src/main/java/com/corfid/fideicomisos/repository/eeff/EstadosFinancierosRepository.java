package com.corfid.fideicomisos.repository.eeff;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.eeff.EstadosFinancieros;

@Repository("estadosFinancierosRepository")
public interface EstadosFinancierosRepository extends JpaRepository<EstadosFinancieros, Serializable> {

    public abstract EstadosFinancieros findByIdEstadosFinancieros(Integer idEEFF);
    
    @Query(value = "SELECT n FROM EstadosFinancieros n "
                 + "INNER JOIN Fideicomiso fico "
                 + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                 + "INNER JOIN FideicomisoFideicomisario fifo "
                 + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                 + "INNER JOIN Fideicomisario fisa "
                 + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                 + "AND fisa.numeroDocumento = :numeroRuc "
                 + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') "
                 + "AND n.fechaCorte = :fechaDate ", 
      countQuery = "SELECT count(n.idEstadosFinancieros) FROM EstadosFinancieros n "
                      + "INNER JOIN Fideicomiso fico "
                      + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                      + "INNER JOIN FideicomisoFideicomisario fifo "
                      + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                      + "INNER JOIN Fideicomisario fisa "
                      + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                      + "AND fisa.numeroDocumento = :numeroRuc "
                      + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') " 
                      + "AND n.fechaCorte = :fechaDate ")
    public abstract Page<EstadosFinancieros> listEEFFByFideicomisoPaginado(@Param("fideicomisos") String fideicomisos,
                                                                           @Param("numeroRuc") String numeroRuc,
                                                                           @Param("fechaDate") Date fechaDate,
                                                                           Pageable pageable);

    @Query(value = "SELECT n FROM EstadosFinancieros n "
                    + "INNER JOIN Fideicomiso fico "
                    + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                    + "INNER JOIN FideicomisoFideicomisario fifo "
                    + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                    + "INNER JOIN Fideicomisario fisa "
                    + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                    + "AND fisa.numeroDocumento = :numeroRuc "
                    + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') ", 
         countQuery = "SELECT count(n.idEstadosFinancieros) FROM EstadosFinancieros n "
                         + "INNER JOIN Fideicomiso fico "
                         + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                         + "INNER JOIN FideicomisoFideicomisario fifo "
                         + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                         + "INNER JOIN Fideicomisario fisa "
                         + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                         + "AND fisa.numeroDocumento = :numeroRuc "
                         + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') ")
       public abstract Page<EstadosFinancieros> listEEFFByFideicomisoPaginadoSinFecha(@Param("fideicomisos") String fideicomisos,
                                                                              @Param("numeroRuc") String numeroRuc,
                                                                              Pageable pageable);
    
    @Query(value = "SELECT n FROM EstadosFinancieros n "
                    + "INNER JOIN Fideicomiso fico "
                    + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                    + "INNER JOIN FideicomisoFideicomisario fifo "
                    + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                    + "INNER JOIN Fideicomisario fisa "
                    + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                    + "AND fisa.numeroDocumento = :numeroRuc "
                    + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') "
                    + "AND MONTH(n.fechaCorte) = :fechaDate1 ", 
         countQuery = "SELECT count(n.idEstadosFinancieros) FROM EstadosFinancieros n "
                         + "INNER JOIN Fideicomiso fico "
                         + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                         + "INNER JOIN FideicomisoFideicomisario fifo "
                         + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                         + "INNER JOIN Fideicomisario fisa "
                         + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                         + "AND fisa.numeroDocumento = :numeroRuc "
                         + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') " 
                         + "AND MONTH(n.fechaCorte) = :fechaDate ")
       public abstract Page<EstadosFinancieros> listEEFFByFideicomisoPaginadoByMes(@Param("fideicomisos") String fideicomisos,
                                                                              @Param("numeroRuc") String numeroRuc,
                                                                              @Param("fechaDate1") Integer fechaDate,
                                                                              Pageable pageable);
    
    @Query(value = "SELECT n FROM EstadosFinancieros n "
                    + "INNER JOIN Fideicomiso fico "
                    + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                    + "INNER JOIN FideicomisoFideicomisario fifo "
                    + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                    + "INNER JOIN Fideicomisario fisa "
                    + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                    + "AND fisa.numeroDocumento = :numeroRuc "
                    + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') "
                    + "AND YEAR(n.fechaCorte) = :fechaDate ", 
         countQuery = "SELECT count(n.idEstadosFinancieros) FROM EstadosFinancieros n "
                         + "INNER JOIN Fideicomiso fico "
                         + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                         + "INNER JOIN FideicomisoFideicomisario fifo "
                         + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                         + "INNER JOIN Fideicomisario fisa "
                         + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                         + "AND fisa.numeroDocumento = :numeroRuc "
                         + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') " 
                         + "AND YEAR(n.fechaCorte) = :fechaDate ")
       public abstract Page<EstadosFinancieros> listEEFFByFideicomisoPaginadoByAnio(@Param("fideicomisos") String fideicomisos,
                                                                              @Param("numeroRuc") String numeroRuc,
                                                                              @Param("fechaDate") Integer fechaDate,
                                                                              Pageable pageable);
    
    @Query(value = "SELECT n FROM EstadosFinancieros n "
                    + "INNER JOIN Fideicomiso fico "
                    + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                    + "INNER JOIN FideicomisoFideicomisario fifo "
                    + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                    + "INNER JOIN Fideicomisario fisa "
                    + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                    + "AND fisa.numeroDocumento = :numeroRuc "
                    + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') "
                    + "AND n.periodo = :periodo ", 
         countQuery = "SELECT count(n.idEstadosFinancieros) FROM EstadosFinancieros n "
                         + "INNER JOIN Fideicomiso fico "
                         + "ON fico.identificadorFideicomiso = n.fideicomiso.identificadorFideicomiso "
                         + "INNER JOIN FideicomisoFideicomisario fifo "
                         + "ON fifo.fideicomiso.identificadorFideicomiso = fico.identificadorFideicomiso "
                         + "INNER JOIN Fideicomisario fisa "
                         + "ON fisa.identificadorFideicomisario = fifo.fideicomisario.identificadorFideicomisario "
                         + "AND fisa.numeroDocumento = :numeroRuc "
                         + "WHERE fico.nombreFideicomiso LIKE CONCAT('%',:fideicomisos,'%') " 
                         + "AND n.periodo = :periodo ")
       public abstract Page<EstadosFinancieros> listEEFFByFideicomisoPaginadoByPeriodo(@Param("fideicomisos") String fideicomisos,
                                                                                       @Param("numeroRuc") String numeroRuc,
                                                                                       @Param("periodo") String periodo,
                                                                                       Pageable pageable);

    @Query(value = "select d from EstadosFinancieros d where d.idEstadosFinancieros = :id AND d.estadoRegistro = 'S' AND d.estado = 'A' ")
    public abstract EstadosFinancieros findByIdEstadosFinancierosVigente(@Param("id") Integer idEstadosFinancieros);
}
