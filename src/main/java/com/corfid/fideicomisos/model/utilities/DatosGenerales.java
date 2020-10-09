package com.corfid.fideicomisos.model.utilities;

import java.util.ArrayList;
import java.util.List;

import com.corfid.fideicomisos.model.administrativo.EmpresaModel;
import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.administrativo.RolModel;

public class DatosGenerales {

    private List<MenuModel> listMenuModel = new ArrayList<MenuModel>();
    private String modo;
    private String rucEmpresa;
    private String menu;
    private List<EmpresaModel> listEmpresaModelVinculadas;
    private List<RolModel> listRolModel;
    private Integer idUsuario;
    private String tipoUsuarioSession;
    private Integer idEmpresa;

    public List<MenuModel> getListMenuModel() {
        return listMenuModel;
    }

    public void setListMenuModel(List<MenuModel> listMenuModel) {
        this.listMenuModel = listMenuModel;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public List<EmpresaModel> getListEmpresaModelVinculadas() {
        return listEmpresaModelVinculadas;
    }

    public void setListEmpresaModelVinculadas(List<EmpresaModel> listEmpresaModelVinculadas) {
        this.listEmpresaModelVinculadas = listEmpresaModelVinculadas;
    }

    public List<RolModel> getListRolModel() {
        return listRolModel;
    }

    public void setListRolModel(List<RolModel> listRolModel) {
        this.listRolModel = listRolModel;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoUsuarioSession() {
        return tipoUsuarioSession;
    }

    public void setTipoUsuarioSession(String tipoUsuarioSession) {
        this.tipoUsuarioSession = tipoUsuarioSession;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

}
