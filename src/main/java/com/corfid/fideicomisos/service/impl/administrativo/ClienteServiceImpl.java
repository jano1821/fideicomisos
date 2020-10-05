package com.corfid.fideicomisos.service.impl.administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.corfid.fideicomisos.component.administrativo.ClienteConverter;
import com.corfid.fideicomisos.entity.administrativo.Cliente;
import com.corfid.fideicomisos.model.administrativo.ClienteModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;
import com.corfid.fideicomisos.repository.administrativo.ClienteRepository;
import com.corfid.fideicomisos.service.administrativo.ClienteInterface;
import com.corfid.fideicomisos.utilities.AbstractService;
import com.corfid.fideicomisos.utilities.Constante;

@Service("clienteServiceImpl")
public class ClienteServiceImpl extends AbstractService implements ClienteInterface {

    @Autowired
    @Qualifier("clienteRepository")
    ClienteRepository clienteRepository;

    @Autowired
    @Qualifier("clienteConverter")
    ClienteConverter clienteConverter;
    
    public ClienteModel findByIdCliente(Integer idPersona) throws Exception {
        ClienteModel clienteModel = null;
        try {
            Cliente cliente = clienteRepository.findByIdCliente(idPersona);
            
            if (!_isEmpty(cliente)) {
                clienteModel = clienteConverter.convertClienteToClienteModel(cliente);
            }

            return clienteModel;
        } catch (Exception e) {
            return clienteModel;
        }
    }

    public Boolean registrarCliente(Integer idCliente,
                                    ParametrosAuditoriaModel parametrosAuditoriaModel) throws Exception {
        Cliente cliente = new Cliente();

        try {
            cliente.setIdCliente(idCliente);
            cliente.setEstadoRegistro(Constante.ESTADO_REGISTRO_VIGENTE);
            setInsercionAuditoria(cliente, parametrosAuditoriaModel);

            cliente = clienteRepository.save(cliente);
            if (_isEmpty(cliente)) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean removerCliente(Integer idCliente) throws Exception {
        try {
            Cliente cliente = clienteRepository.findByIdCliente(idCliente);
            clienteRepository.delete(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
