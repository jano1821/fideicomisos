package com.corfid.fideicomisos.controller.eeff;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.cruds.CrudEstadosFinancierosModel;
import com.corfid.fideicomisos.model.eeff.EstadosFinancierosModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.service.eeff.EstadosFinancierosInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.service.utilities.RestClientEstadosFinancierosInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.FTPInterface;
import com.corfid.fideicomisos.utilities.InitialController;
import com.corfid.fideicomisos.utilities.StringUtil;

@Controller
@RequestMapping("/eeff")
public class EstadosFinancierosController extends InitialController {

    @Autowired
    @Qualifier("estadosFinancierosServiceImpl")
    private EstadosFinancierosInterface estadosFinancierosInterface;

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    @Autowired
    @Qualifier("fideicomisoServiceImpl")
    private FideicomisoInterface fideicomisoInterface;

    @Autowired
    @Qualifier("ftpServiceImpl")
    private FTPInterface ftpInterface;
    
    @Autowired
    @Qualifier("restClientEstadosFinancierosServiceImpl")
    RestClientEstadosFinancierosInterface restClientEstadosFinancierosInterface;

    @GetMapping("/lista_eeff")
    public ModelAndView showListaMenus(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                       Model model) throws Exception {
        CrudEstadosFinancierosModel crudEstadosFinancierosModel = new CrudEstadosFinancierosModel();
        try {
            datosGenerales.setModulo(Constante.MODULO_EEFF);
            return busqueda(Constante.CONST_VACIA,
                            Constante.CONST_VACIA,
                            Constante.CONST_VACIA,
                            datosGenerales.getRucEmpresa(),
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudEstadosFinancierosModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EEFF);
        }
    }

    @GetMapping("/crudAccionesListaEEFF")
    public String intercepcion() {
        return "redirect:" + Constante.URL_SELECCION;
    }

    @PostMapping(value = "/crudAccionesListaEEFF", params = { "findRow", "busqueda" , "mesFechaCorte", "anioFechaCorte" })
    public ModelAndView buscarMenu(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                   CrudEstadosFinancierosModel crudEstadosFinancierosModel,
                                   final BindingResult bindingResult,
                                   final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");
            String mesFechaCorte = req.getParameter("mesFechaCorte");
            String anioFechaCorte = req.getParameter("anioFechaCorte");

            return busqueda(caja,
                            mesFechaCorte,
                            anioFechaCorte,
                            datosGenerales.getRucEmpresa(),
                            Constante.CONST_CERO,
                            Constante.CONST_CERO,
                            Constante.PAGINA_INICIAL,
                            Constante.CONST_CERO,
                            crudEstadosFinancierosModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EEFF);
        }
    }

    @PostMapping(value = "/crudAccionesListaEEFF", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal", "mesFechaCorte", "anioFechaCorte" })
    public ModelAndView paginaDerecha(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      CrudEstadosFinancierosModel crudEstadosFinancierosModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            String mesFechaCorte = req.getParameter("mesFechaCorte");
            String anioFechaCorte = req.getParameter("anioFechaCorte");

            return busqueda(caja,
                            mesFechaCorte,
                            anioFechaCorte,
                            datosGenerales.getRucEmpresa(),
                            Constante.CONST_CERO,
                            Constante.DERECHA,
                            paginaActual,
                            paginaFinal,
                            crudEstadosFinancierosModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EEFF);
        }
    }

    @PostMapping(value = "/crudAccionesListaEEFF", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal", "mesFechaCorte", "anioFechaCorte" })
    public ModelAndView paginaIzquierda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                        CrudEstadosFinancierosModel crudEstadosFinancierosModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");
            String mesFechaCorte = req.getParameter("mesFechaCorte");
            String anioFechaCorte = req.getParameter("anioFechaCorte");

            return busqueda(caja,
                            mesFechaCorte,
                            anioFechaCorte,
                            datosGenerales.getRucEmpresa(),
                            Constante.IZQUIERDA,
                            Constante.CONST_CERO,
                            paginaActual,
                            paginaFinal,
                            crudEstadosFinancierosModel);
        } catch (Exception e) {
            return new ModelAndView(Constante.LISTA_EEFF);
        }
    }

    @GetMapping("/descargarEEFF")
    public ResponseEntity<byte[]> descargarPDF(CrudEstadosFinancierosModel crudEstadosFinancierosModel,
                                               @RequestParam(name = "id", required = false) int id) throws Exception {
        ResponseEntity<byte[]> response = null;
        byte[] bytes = null;
        try {
            String idEEFF = StringUtil.toStr(id);
            EstadosFinancierosModel estadosFinancierosModel;
            String desicion = obtenerConstante(Constante.ORIGEN_ARC);
            estadosFinancierosModel = estadosFinancierosInterface.obtenerEEFF(StringUtil.toInteger(idEEFF));
            String nombreArchivo = estadosFinancierosModel.getNombreArchivo() + "." + estadosFinancierosModel.getTipoArchivo();
            if (StringUtil.equiv(desicion, Constante.POR_FTP)) {
                String ruta = obtenerConstante("RUTA_FTP");
                String fileString = ruta + nombreArchivo;
                File file = new File(fileString);
                FileInputStream fis = new FileInputStream(file);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                try {
                    for (int readNum; (readNum = fis.read(buf)) != -1;) {
                        bos.write(buf, 0, readNum);
                    }
                } catch (IOException ex) {
                    System.out.print(ex.getMessage());
                }
                fis.close();
                bytes = bos.toByteArray();

                estadosFinancierosInterface.guardarpdf(StringUtil.toInteger(idEEFF), bytes);
            } else if(StringUtil.equiv(desicion, Constante.POR_ARC)){
                bytes = estadosFinancierosModel.getArchivo();
            } else {
                String token = restClientEstadosFinancierosInterface.getObtenerToken();
                bytes = restClientEstadosFinancierosInterface.ObtenerArchivos(token, estadosFinancierosModel.getRuta(), nombreArchivo);
            }

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.parseMediaType("application/pdf"));

            headers.add("Content-Disposition", "inline; filename= .. ");

            response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
            return response;

        } catch (Exception e) {
            return response;
        }
    }

    private ModelAndView busqueda(String busqueda,
                                  String mesFechaCorte,
                                  String anioFechaCorte,
                                  String rucFideicomisario,
                                  String izquierda,
                                  String derecha,
                                  String pagina,
                                  String fin,
                                  CrudEstadosFinancierosModel crudEstadosFinancierosModel) throws Exception {
        CrudEstadosFinancierosModel crudEstadosFinancierosModelLista = new CrudEstadosFinancierosModel();
        List<CatalogoConstraintModel> listMeses = new ArrayList<CatalogoConstraintModel>();
        List<CatalogoConstraintModel> listAnios = new ArrayList<CatalogoConstraintModel>();
        try {
            ModelAndView mav = new ModelAndView(Constante.LISTA_EEFF);
            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            //if (!StringUtil.isEmpty(busqueda)) {
                crudEstadosFinancierosModelLista = estadosFinancierosInterface.listEEFFByFideicomisoPaginado(busqueda,
                                                                                                             mesFechaCorte,
                                                                                                             anioFechaCorte,
                                                                                                             rucFideicomisario,
                                                                                                             paginadoModel.getPaginaActual(),
                                                                                                             Constante.PAGINADO_5_ROWS);
            //}

            if (paginadoModel.isMovIzquierda()) {
                crudEstadosFinancierosModelLista.setCodigoError(ConstantesError.ERROR_1);
                crudEstadosFinancierosModelLista.setMensaje(construirMensaje("Aviso",
                                                                             "No hay más Registros a la Izquierda",
                                                                             Constante.MENSAJE_INFORMATIVO));
            } else if (paginadoModel.isMovDerecha()) {
                crudEstadosFinancierosModelLista.setMensaje(construirMensaje("Aviso",
                                                                             "No hay más Registros a la Derecha",
                                                                             Constante.MENSAJE_INFORMATIVO));
                crudEstadosFinancierosModelLista.setCodigoError(ConstantesError.ERROR_1);
            } else {
                if (!StringUtil.isEmpty(crudEstadosFinancierosModel.getCodigoError())) {
                    if (StringUtil.equiv(crudEstadosFinancierosModel.getCodigoError(), ConstantesError.ERROR_0)) {
                        crudEstadosFinancierosModelLista.setMensaje(construirMensaje("Aviso",
                                                                                     "La Operacion se Realizó Satisfactoriamente",
                                                                                     Constante.MENSAJE_SATISFACTORIO));
                    } else if (StringUtil.equiv(crudEstadosFinancierosModel.getCodigoError(),
                                                ConstantesError.ERROR_1)) {
                        crudEstadosFinancierosModelLista.setMensaje(construirMensaje("Aviso",
                                                                                     "Ocurrió un error en la operación",
                                                                                     Constante.MENSAJE_ERROR));
                    } else {
                        crudEstadosFinancierosModelLista.setMensaje(construirMensaje("Aviso",
                                                                                     crudEstadosFinancierosModel.getMensajeError(),
                                                                                     Constante.MENSAJE_ERROR));
                    }
                }
            }

            CatalogoConstraintModel mes = new CatalogoConstraintModel();
            for (int i = 1; i <= 12; i++) {
                mes = new CatalogoConstraintModel();
                if (i >= 10) {
                    mes.setValorConstraint(StringUtil.toStr(i));
                } else {
                    mes.setValorConstraint("0" + StringUtil.toStr(i));
                }

                if (i == 1) {
                    mes.setDescConstraint("Enero");
                }
                if (i == 2) {
                    mes.setDescConstraint("Febrero");
                }
                if (i == 3) {
                    mes.setDescConstraint("Marzo");
                }
                if (i == 4) {
                    mes.setDescConstraint("Abril");
                }
                if (i == 5) {
                    mes.setDescConstraint("Mayo");
                }
                if (i == 6) {
                    mes.setDescConstraint("Junio");
                }
                if (i == 7) {
                    mes.setDescConstraint("Julio");
                }
                if (i == 8) {
                    mes.setDescConstraint("Agosto");
                }
                if (i == 9) {
                    mes.setDescConstraint("Setiembre");
                }
                if (i == 10) {
                    mes.setDescConstraint("Octubre");
                }
                if (i == 11) {
                    mes.setDescConstraint("Noviembre");
                }
                if (i == 12) {
                    mes.setDescConstraint("Diciembre");
                }
                listMeses.add(mes);
            }

            CatalogoConstraintModel anio = new CatalogoConstraintModel();
            for (int i = 0; i < 30; i++) {
                anio = new CatalogoConstraintModel();
                anio.setValorConstraint(StringUtil.toStr(2005 + i));
                anio.setDescConstraint(StringUtil.toStr(2005 + i));
                listAnios.add(anio);
            }

            crudEstadosFinancierosModelLista.setBusqueda(busqueda);
            crudEstadosFinancierosModelLista.setMesFechaCorte(mesFechaCorte);
            crudEstadosFinancierosModelLista.setAnioFechaCorte(anioFechaCorte);
            crudEstadosFinancierosModelLista.setPaginaActual(paginadoModel.getPaginaActual());

            mav.addObject("crudEstadosFinancierosModel", crudEstadosFinancierosModelLista);

            mav.addObject("listMeses", listMeses);
            mav.addObject("listAnios", listAnios);

            return mav;
        } catch (Exception e) {
            crudEstadosFinancierosModelLista.setCodigoError(ConstantesError.ERROR_1);
            crudEstadosFinancierosModelLista.setMensaje(construirMensaje("Aviso",
                                                                         "Ocurrió un problema al consultar registros",
                                                                         Constante.MENSAJE_ERROR));
            return new ModelAndView(Constante.LISTA_MENUS);
        }
    }
}
