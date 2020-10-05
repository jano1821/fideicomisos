package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.HistorialContrasenia;

@Repository("historialContraseniaRepository")
public interface HistorialContraseniaRepository extends JpaRepository<HistorialContrasenia, Serializable> {

}
