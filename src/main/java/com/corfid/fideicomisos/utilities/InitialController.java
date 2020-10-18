package com.corfid.fideicomisos.utilities;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.corfid.fideicomisos.model.administrativo.MenuModel;
import com.corfid.fideicomisos.model.utilities.GenericModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.model.utilities.ParametrosAuditoriaModel;

public class InitialController {

    @Autowired
    private Environment environment;

    private Date fechaInsercion;

    private String usuarioInsercion;

    private String ipInsercion;

    private Date fechaModificacion;

    private String usuarioModificacion;

    private String ipModificacion;

    public Date getFechaInsercion() {
        return fechaInsercion;
    }

    protected void setFechaInsercion(Date fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    protected String getUsuarioInsercion() {
        return usuarioInsercion;
    }

    protected void setUsuarioInsercion(String usuarioInsercion) {
        this.usuarioInsercion = usuarioInsercion;
    }

    protected String getIpInsercion() {
        return ipInsercion;
    }

    protected void setIpInsercion(String ipInsercion) {
        this.ipInsercion = ipInsercion;
    }

    protected Date getFechaModificacion() {
        return fechaModificacion;
    }

    protected void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    protected String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    protected void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    protected String getIpModificacion() {
        return ipModificacion;
    }

    protected void setIpModificacion(String ipModificacion) {
        this.ipModificacion = ipModificacion;
    }

    public InitialController(Date fechaInsercion, String usuarioInsercion, String ipInsercion, Date fechaModificacion, String usuarioModificacion, String ipModificacion) {
        super();
        this.fechaInsercion = fechaInsercion;
        this.usuarioInsercion = usuarioInsercion;
        this.ipInsercion = ipInsercion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.ipModificacion = ipModificacion;
    }

    public InitialController() {

    }

    protected void setParametrosAuditoriaModel(Date fechaInsercion,
                                               String usuarioInsercion,
                                               String ipInsercion,
                                               Date fechaModificacion,
                                               String usuarioModificacion,
                                               String ipModificacion) {
        this.fechaInsercion = fechaInsercion;
        this.usuarioInsercion = usuarioInsercion;
        this.ipInsercion = ipInsercion;
        this.fechaModificacion = fechaModificacion;
        this.usuarioModificacion = usuarioModificacion;
        this.ipModificacion = ipModificacion;
    }

    protected ParametrosAuditoriaModel getParametrosAuditoriaModel() {
        ParametrosAuditoriaModel parametrosAuditoriaModel = new ParametrosAuditoriaModel();
        parametrosAuditoriaModel.setFechaInsercion(this.fechaInsercion);
        parametrosAuditoriaModel.setUsuarioInsercion(this.usuarioInsercion);
        parametrosAuditoriaModel.setIpInsercion(this.ipInsercion);
        parametrosAuditoriaModel.setFechaModificacion(this.fechaModificacion);
        parametrosAuditoriaModel.setUsuarioModificacion(this.usuarioModificacion);
        parametrosAuditoriaModel.setIpModificacion(this.ipModificacion);

        return parametrosAuditoriaModel;
    }

    protected Date getFechaAndHoraActual() {
        String dateString = "", hourString = "";

        dateString = FormatoFecha.obtenerFechaActual();
        hourString = FormatoFecha.obtenerHora();

        return FormatoFecha.stringToTimestampddMMyyyyhhmmss(dateString, hourString);
    }

    protected PaginadoModel obtenerMovimientoAndPagina(String paginaActual,
                                                       String paginaFinal,
                                                       String movIzquierda,
                                                       String movDerecha) {
        PaginadoModel paginadoModel = new PaginadoModel();
        Integer nuevapaginaActual = 1;
        boolean izquierda = false;
        boolean derecha = false;

        if (StringUtil.equiv(movIzquierda, Constante.IZQUIERDA)) {
            if (StringUtil.equiv(paginaActual, Constante.PAGINA_INICIAL)) {
                nuevapaginaActual = StringUtil.toInteger(paginaActual);
                izquierda = true;
            } else {
                nuevapaginaActual = StringUtil.toInteger(paginaActual) - 1;
            }

        } else if (StringUtil.equiv(movDerecha, Constante.DERECHA)) {
            if (StringUtil.equiv(paginaActual, paginaFinal)) {
                nuevapaginaActual = StringUtil.toInteger(paginaActual);
                derecha = true;
            } else {
                nuevapaginaActual = StringUtil.toInteger(paginaActual) + 1;
            }
        } else {
            nuevapaginaActual = StringUtil.toInteger(paginaActual);
        }

        paginadoModel.setMovDerecha(derecha);
        paginadoModel.setMovIzquierda(izquierda);
        paginadoModel.setPaginaActual(nuevapaginaActual);

        return paginadoModel;
    }

    protected String construirMenu(List<MenuModel> listMenuModel) {
        String menu = "";
        for (MenuModel menuModel : listMenuModel) {
            /*
             * if (StringUtil.equiv(menuModel.getIdMenu(), menuModel.getIdMenuPadre())) { menu += "<div class=\"header item\" ><i class=\"globe icon\"></i>" + menuModel.getDescripcion() + "</div>"; }
             * else { menu += "<a class=\"blue inverted item\" href=\"" + menuModel.getUrl() + "\">" + menuModel.getDescripcion() + "</a> "; }
             */
            if (StringUtil.equiv(menuModel.getIdMenu(), menuModel.getIdMenuPadre())) {
                menu += "<a class=\"header item\"> <i class=\"globe icon\"></i>" + menuModel.getDescripcion() + "</a>";
            } else {
                menu += "<a class=\"item\" href=\"" + menuModel.getUrl() + "\">" + menuModel.getDescripcion() + "</a> ";
            }
        }

        return menu;
    }

    protected String construirComboSearch(String tipoCombo,
                                          List<GenericModel> listGenericModel,
                                          String idName,
                                          String defaultText,
                                          List<GenericModel> listGenericModelSeleccionado) {
        String combo = "";
        String carga = "";
        String separador = "";

        if (StringUtil.equiv(tipoCombo, Constante.SELECCION_MULTIPLE)) {
            combo += "<div class=\"ui fluid multiple search selection dropdown\">";
        } else if (StringUtil.equiv(tipoCombo, Constante.SELECCION_SIMPLE)) {
            combo += "<div class=\"ui fluid search selection dropdown\">";
        }
        if (!StringUtil.isEmpty(listGenericModelSeleccionado)) {
            for (GenericModel genericModel : listGenericModelSeleccionado) {
                carga += separador + genericModel.getId();
                separador = ",";
            }

            combo += "<input type=\"hidden\" value=\"" + carga + "\" name=\"" + idName + "\" id=\"" + idName + "\">";
        } else {
            combo += "<input type=\"hidden\" name=\"" + idName + "\" id=\"" + idName + "\">";
        }
        combo += "<i class=\"dropdown icon\"></i>";
        combo += "<div class=\"default text\">" + defaultText + "</div>";
        combo += "<div class=\"menu\">";
        if (!StringUtil.isEmpty(listGenericModel)) {
            for (GenericModel genericModel : listGenericModel) {
                combo += "<div class=\"item\" data-value=\"" + genericModel.getId() + "\">" + genericModel.getDescripcion() + "</div>";
            }
        }
        combo += "</div></div>";

        return combo;
    }

    protected String construirMensaje(String titulo, String contenido, String tipo) {
        String mensaje = "";

        if (StringUtil.isEmpty(titulo)) {
            titulo = "Mensaje";
        }

        if (StringUtil.equiv(Constante.MENSAJE_ERROR, tipo)) {
            mensaje += "<div class=\"ui negative message\">";
        } else if (StringUtil.equiv(Constante.MENSAJE_INFORMATIVO, tipo)) {
            mensaje += "<div class=\"ui info message\">";
        } else if (StringUtil.equiv(Constante.MENSAJE_SATISFACTORIO, tipo)) {
            mensaje += "<div class=\"ui positive message\">";
        }

        mensaje += "<i class=\"close icon\"></i>";
        mensaje += "<div class=\"header\">" + titulo + "</div>";
        mensaje += "<p>" + contenido + "</p>";
        mensaje += "</div>";

        return mensaje;
    }

    protected String obtenerIp() {
        return environment.getProperty("local.server.port");
    }
}
