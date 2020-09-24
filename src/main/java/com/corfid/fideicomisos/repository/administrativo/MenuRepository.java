package com.corfid.fideicomisos.repository.administrativo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corfid.fideicomisos.entity.administrativo.Menu;

@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<Menu, Serializable> {

}
