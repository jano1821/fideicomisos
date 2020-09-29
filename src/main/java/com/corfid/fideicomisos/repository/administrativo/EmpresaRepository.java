package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Empresa;

@Repository("empresaRepository")
public interface EmpresaRepository extends JpaRepository<Empresa, Serializable> {

	public abstract Empresa findByIdEmpresa(Integer id);
}
