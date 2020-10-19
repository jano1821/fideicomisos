package com.corfid.fideicomisos.component.administrativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.corfid.fideicomisos.component.utilities.GenericConverter;
import com.corfid.fideicomisos.entity.administrativo.Persona;
import com.corfid.fideicomisos.entity.administrativo.Rol;
import com.corfid.fideicomisos.entity.administrativo.TipoUsuario;
import com.corfid.fideicomisos.entity.administrativo.Usuario;
import com.corfid.fideicomisos.model.administrativo.RolModel;
import com.corfid.fideicomisos.model.administrativo.UsuarioModel;
import com.corfid.fideicomisos.service.administrativo.PersonaInterface;
import com.corfid.fideicomisos.service.administrativo.TipoUsuarioInterface;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Component("usuarioConverter")
public class UsuarioConverter extends GenericConverter {

    @Autowired
    @Qualifier("personaServiceImpl")
    PersonaInterface personaInterface;

    @Autowired
    @Qualifier("tipoUsuarioServiceImpl")
    TipoUsuarioInterface tipoUsuarioInterface;

    @Autowired
    @Qualifier("rolConverter")
    RolConverter rolConverter;

    public Usuario convertUsuarioModelToUsuario(UsuarioModel usuarioModel) {
        Usuario usuario = new Usuario();
        TipoUsuario tipoUsuario = new TipoUsuario();

        usuario.setIdUsuario(usuarioModel.getIdUsuario());
        usuario.setUsuario(usuarioModel.getUsuario().toUpperCase());
        if (StringUtil.equiv(usuarioModel.getEstadoActividad(), "1")) {
            usuario.setEstadoActividadUsuario(true);
        } else {
            usuario.setEstadoActividadUsuario(false);
        }
        if (!StringUtil.isEmpty(usuarioModel.getPersona())) {
            Persona persona;
            persona = personaInterface.findPersonaById(StringUtil.toInteger(usuarioModel.getPersona()));
            usuario.setPersona(persona);
        }

        tipoUsuario = tipoUsuarioInterface.findTipoUsuarioById(usuarioModel.getTipoUsuario());

        usuario.setTipoUsuario(tipoUsuario);
        usuario.setIdUsuarioRegistro(usuarioModel.getIdUsuarioRegistro());
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        usuario.setPassword(pe.encode(usuarioModel.getPassword()));
        usuario.setEstadoRegistro(usuarioModel.getEstadoRegistro());
        usuario.setUsuarioInsercion(usuarioModel.getUsuarioInsercion());
        usuario.setIpInsercion(usuarioModel.getIpInsercion());
        usuario.setFechaInsercion(usuarioModel.getFechaInsercion());
        usuario.setUsuarioModificacion(usuarioModel.getUsuarioModificacion());
        usuario.setIpModificacion(usuarioModel.getIpModificacion());
        usuario.setFechaModificacion(usuarioModel.getFechaModificacion());

        return usuario;

    }

    public Usuario convertUsuarioModelToUsuarioExistente(Usuario usuario, UsuarioModel usuarioModel) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        usuario.setIdUsuario(usuarioModel.getIdUsuario());
        usuario.setUsuario(usuarioModel.getUsuario());
        if (StringUtil.equiv(usuarioModel.getEstadoActividad(), "1")) {
            usuario.setEstadoActividadUsuario(true);
        } else {
            usuario.setEstadoActividadUsuario(false);
        }

        if (!StringUtil.isEmpty(usuarioModel.getCambiar())) {
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
            usuario.setPassword(pe.encode(usuarioModel.getPassword()));
        }
        if (!StringUtil.isEmpty(usuarioModel.getPersona())) {
            Persona persona;
            persona = personaInterface.findPersonaById(StringUtil.toInteger(usuarioModel.getPersona()));
            usuario.setPersona(persona);
        }
        tipoUsuario = tipoUsuarioInterface.findTipoUsuarioById(usuarioModel.getTipoUsuario());

        usuario.setTipoUsuario(tipoUsuario);
        usuario.setEstadoRegistro(usuarioModel.getEstadoRegistro());
        usuario.setIdUsuarioRegistro(usuarioModel.getIdUsuarioRegistro());

        return usuario;

    }

    public UsuarioModel convertUsuarioToUsuarioModel(Usuario usuario) throws Exception {
        UsuarioModel usuarioModel = new UsuarioModel();
        List<RolModel> listRolModel;
        RolModel rolModel = new RolModel();
        try {

            usuarioModel.setIdUsuario(usuario.getIdUsuario());
            usuarioModel.setUsuario(usuario.getUsuario());
            usuarioModel.setPassword(usuario.getPassword());
            usuarioModel.setTipoUsuario(usuario.getTipoUsuario().getIdTipoUsuario());
            usuarioModel.setEstadoActividadUsuario(usuario.isEstadoActividadUsuario());
            usuarioModel.setEstadoRegistro(usuario.getEstadoRegistro());
            usuarioModel.setIdUsuarioRegistro(usuario.getIdUsuarioRegistro());
            usuarioModel.setUsuarioInsercion(usuario.getUsuarioInsercion());
            usuarioModel.setIpInsercion(usuario.getIpInsercion());
            usuarioModel.setFechaInsercion(usuario.getFechaInsercion());
            usuarioModel.setUsuarioModificacion(usuario.getUsuarioModificacion());
            usuarioModel.setIpModificacion(usuario.getIpModificacion());
            usuarioModel.setFechaModificacion(usuario.getFechaModificacion());

            if (!StringUtil.isEmpty(usuario.getPersona())) {
                usuarioModel.setIdPersona(usuario.getPersona().getIdPersona());
            } else {
                usuarioModel.setIdPersona(null);
            }

            if (StringUtil.equiv(usuario.getTipoUsuario().getIdTipoUsuario(), "S")) {
                usuarioModel.setDescTipoUsuario("Super Administrador");
            } else if (StringUtil.equiv(usuario.getTipoUsuario().getIdTipoUsuario(), "A")) {
                usuarioModel.setDescTipoUsuario("Administrador");
            } else if (StringUtil.equiv(usuario.getTipoUsuario().getIdTipoUsuario(), "B")) {
                usuarioModel.setDescTipoUsuario("Estandar");
            } else {
                usuarioModel.setDescTipoUsuario("Desconocido");
            }

            if (!StringUtil.isEmpty(usuario.getRoles())) {
                listRolModel = new ArrayList<RolModel>();
                for (Rol rol : usuario.getRoles()) {
                    rolModel = rolConverter.convertRolToRolModel(rol);
                    listRolModel.add(rolModel);
                }
                usuarioModel.setListRoles(listRolModel);
            }

            if (usuario.isEstadoActividadUsuario() == true) {
                usuarioModel.setDescEstadoActividad("Activo");
                usuarioModel.setEstadoActividad("1");
            } else {
                usuarioModel.setDescEstadoActividad("Bloqueado");
                usuarioModel.setEstadoActividad("0");
            }

            if (StringUtil.equiv(usuario.getEstadoRegistro(), "S")) {
                usuarioModel.setDescEstadoRegistro("Vigente");
            } else if (StringUtil.equiv(usuario.getEstadoRegistro(), "N")) {
                usuarioModel.setDescEstadoRegistro("No Vigente");
            } else {
                usuarioModel.setDescEstadoRegistro("Desconocido");
            }

            return usuarioModel;
        } catch (Exception e) {
            usuarioModel.setCodigoError(ConstantesError.ERROR_2);
            usuarioModel.setDescripcionError(obtenerMensajeError(ConstantesError.ERROR_2));
            return usuarioModel;
        }
    }
}
