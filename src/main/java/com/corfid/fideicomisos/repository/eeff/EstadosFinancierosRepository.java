package com.corfid.fideicomisos.repository.eeff;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.entity.eeff.EstadosFinancieros;

@Repository("estadosFinancierosRepository")
public interface EstadosFinancierosRepository extends JpaRepository<EstadosFinancieros, Serializable> {

    public abstract EstadosFinancieros findByIdEstadosFinancieros(Integer idEEFF);
    
    @Query(value = "SELECT n FROM EstadosFinancieros n WHERE n.fideicomiso.identificadorFideicomiso in (:fideicomisos)", countQuery = "SELECT count(n.idEstadosFinancieros) FROM EstadosFinancieros n WHERE n.fideicomiso.identificadorFideicomiso in (:fideocomisos)")
    public abstract Page<EstadosFinancieros> listEEFFByFideicomisoPaginado(@Param("fideicomisos") Integer fideocomisos,
                                                                           Pageable pageable);

    @Query(value = "select d from EstadosFinancieros d where d.idEstadosFinancieros = :id AND d.estadoRegistro = 'S' AND d.estado = 'A' ")
    public abstract EstadosFinancieros findByIdEstadosFinancierosVigente(@Param("id") Integer idEstadosFinancieros);
}
