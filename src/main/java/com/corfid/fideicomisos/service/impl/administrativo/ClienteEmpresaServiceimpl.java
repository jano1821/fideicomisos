package com.corfid.fideicomisos.service.impl.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.entity.administrativo.ClienteEmpresa;
import com.corfid.fideicomisos.entity.administrativo.ClienteEmpresaId;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.ClienteEmpresaRepository;
import com.corfid.fideicomisos.service.administrativo.ClienteEmpresaInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;

@Service("clienteEmpresaServiceimpl")
public class ClienteEmpresaServiceimpl extends AbstractService implements ClienteEmpresaInterface {

    @Autowired
    @Qualifier("clienteEmpresaRepository")
    ClienteEmpresaRepository clienteEmpresaRepository;

    public Boolean registrarClienteEmpresa(Integer idCliente,
                                           Integer idEmpresa,
                                           ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        ClienteEmpresaId clienteEmpresaId = new ClienteEmpresaId();
        ClienteEmpresa clienteEmpresa = new ClienteEmpresa();
        try {
            clienteEmpresaId.setIdCliente(idCliente);
            clienteEmpresaId.setIdEmpresa(idEmpresa);

            clienteEmpresa.setClienteEmpresaId(clienteEmpresaId);
            clienteEmpresa.setEstadoRegistro(Constante.ESTADO_REGISTRO_VIGENTE);
            setInsercionAuditoria(clienteEmpresa, parametrosAuditoriaModel);

            clienteEmpresa = clienteEmpresaRepository.save(clienteEmpresa);

            if (_isEmpty(clienteEmpresa)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean removerClienteEmpresa(Integer idCliente, Integer idEmpresa) throws Exception {
        ClienteEmpresaId clienteEmpresaId = new ClienteEmpresaId();
        ClienteEmpresa clienteEmpresa = new ClienteEmpresa();
        try {
            clienteEmpresaId.setIdCliente(idCliente);
            clienteEmpresaId.setIdEmpresa(idEmpresa);

            clienteEmpresa = clienteEmpresaRepository.findByClienteEmpresaId(clienteEmpresaId);

            clienteEmpresaRepository.delete(clienteEmpresa);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
