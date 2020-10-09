package com.corfid.fideicomisos.service.administrativo;

import java.util.List;

import com.corfid.fideicomisos.entity.administrativo.Empresa;
import com.corfid.fideicomisos.model.administrativo.EmpresaModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public interface EmpresaInterface {

    public EmpresaModel findEmpresaById(Integer id) throws Exception;

    public Empresa findEmpresaEntityById(Integer id) throws Exception;

    public Boolean registrarEmpresa(Integer idEmpresa,
                                    ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;

    public Boolean removerEmpresa(Integer idEmpresa) throws Exception;

    public Boolean registrarEmpresasOfCadena(String empresasActualizar,
                                             List<EmpresaModel> listEmpresasDelClienteGestionado,
                                             Integer idUsuarioSesion,
                                             Integer idPersonaDelClienteGestionado,
                                             ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception;
}
