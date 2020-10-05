package com.corfid.fideicomisos.service.impl.administrativo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.EmpresaConverter;
import com.corfid.fideicomisos.entity.administrativo.Empresa;
import com.corfid.fideicomisos.model.administrativo.EmpresaModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.EmpresaRepository;
import com.corfid.fideicomisos.service.administrativo.ClienteEmpresaInterface;
import com.corfid.fideicomisos.service.administrativo.EmpresaInterface;
import com.corfid.fideicomisos.service.administrativo.UsuarioInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;

@Service("empresaServiceImpl")
public class EmpresaServiceImpl extends AbstractService implements EmpresaInterface {

    @Autowired
    @Qualifier("empresaRepository")
    private EmpresaRepository empresaRepository;

    @Autowired
    @Qualifier("empresaConverter")
    private EmpresaConverter empresaConverter;

    @Autowired
    @Qualifier("usuarioServiceImpl")
    private UsuarioInterface usuarioInterface;

    @Autowired
    @Qualifier("clienteEmpresaServiceimpl")
    private ClienteEmpresaInterface clienteEmpresaInterface;

    public EmpresaModel findEmpresaById(Integer id) throws Exception {
        EmpresaModel empresaModel = null;
        try {
            Empresa empresa = empresaRepository.findByIdEmpresa(id);

            if (!_isEmpty(empresa)) {
                empresaModel = empresaConverter.convertEmpresaToEmpresaModel(empresa);
            }

            return empresaModel;
        } catch (Exception e) {
            return empresaModel;
        }
    }

    public Boolean registrarEmpresasOfCadena(String empresasActualizar,
                                             List<EmpresaModel> listEmpresasDelClienteGestionado,
                                             Integer idUsuarioSesion,
                                             Integer idPersonaDelClienteGestionado,
                                             ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        UsuarioModel usuarioModel;
        String[] empresasNueva;
        List<EmpresaModel> listEmpresasVinculadasAlUsuarioSesion;
        Integer contador = 0;

        try {
            usuarioModel = usuarioInterface.findUsuarioByIdModel(idUsuarioSesion);

            if (_equiv(usuarioModel.getTipoUsuario(), Constante.TIPO_USUARIO_SUPER_ADMIN)) {
                empresasNueva = empresasActualizar.split(",");

                for (int i = 0; i < empresasNueva.length; i++) {
                    contador = 0;
                    for (EmpresaModel empresaModel : listEmpresasDelClienteGestionado) {
                        if (_equiv(empresasNueva[i], _toStr(empresaModel.getIdEmpresa()))) {
                            contador++;
                        }
                    }
                    if (contador == 0) {
                        clienteEmpresaInterface.registrarClienteEmpresa(idPersonaDelClienteGestionado,
                                                                        _toInteger(empresasNueva[i]),
                                                                        parametrosAuditoriaModel);
                    }
                }

                contador = 0;
                for (EmpresaModel empresaModel : listEmpresasDelClienteGestionado) {
                    contador = 0;
                    for (int i = 0; i < empresasNueva.length; i++) {
                        if (_equiv(empresasNueva[i], _toStr(empresaModel.getIdEmpresa()))) {
                            contador++;
                        }
                    }
                    if (contador == 0) {
                        clienteEmpresaInterface.removerClienteEmpresa(idPersonaDelClienteGestionado,
                                                                      empresaModel.getIdEmpresa());
                    }
                }
            } else {
                empresasNueva = empresasActualizar.split(",");
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean registrarEmpresa(Integer idEmpresa,
                                    ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        Empresa empresa = new Empresa();

        try {
            empresa.setIdEmpresa(idEmpresa);
            empresa.setEstadoRegistro(Constante.ESTADO_REGISTRO_VIGENTE);
            setInsercionAuditoria(empresa, parametrosAuditoriaModel);

            empresa = empresaRepository.save(empresa);
            if (_isEmpty(empresa)) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean removerEmpresa(Integer idEmpresa) throws Exception {
        try {
            Empresa empresa = empresaRepository.findByIdEmpresa(idEmpresa);
            empresaRepository.delete(empresa);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
