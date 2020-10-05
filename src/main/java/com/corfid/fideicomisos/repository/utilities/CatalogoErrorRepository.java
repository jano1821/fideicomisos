package com.corfid.fideicomisos.repository.utilities;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.utilities.CatalogoError;
import com.corfid.fideicomisos.model.utilities.CatalogoErrorModel;

@Repository("catalogoErrorRepository")
public interface CatalogoErrorRepository extends JpaRepository<CatalogoError, Serializable>{

	public abstract CatalogoError findByCodigoError(String codigoError);
	
}
