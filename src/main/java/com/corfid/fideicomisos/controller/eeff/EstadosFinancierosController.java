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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.corfid.fideicomisos.model.banco.FideicomisoModel;
import com.corfid.fideicomisos.model.cruds.CrudEstadosFinancierosModel;
import com.corfid.fideicomisos.model.eeff.EstadosFinancierosModel;
import com.corfid.fideicomisos.model.utilities.DatosGenerales;
import com.corfid.fideicomisos.model.utilities.GenericModel;
import com.corfid.fideicomisos.model.utilities.PaginadoModel;
import com.corfid.fideicomisos.service.banco.FideicomisoInterface;
import com.corfid.fideicomisos.service.eeff.EstadosFinancierosInterface;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
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
    
    @GetMapping("/lista_eeff")
    public ModelAndView showListaMenus(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                       Model model) throws Exception {
        CrudEstadosFinancierosModel crudEstadosFinancierosModel = new CrudEstadosFinancierosModel();
        try {
            datosGenerales.setModulo(Constante.MODULO_EEFF);
            return busqueda(Constante.CONST_VACIA,
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

    @PostMapping(value = "/crudAccionesListaEEFF", params = { "findRow", "busqueda" })
    public ModelAndView buscarMenu(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                   CrudEstadosFinancierosModel crudEstadosFinancierosModel,
                                   final BindingResult bindingResult,
                                   final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");

            return busqueda(caja,
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

    @PostMapping(value = "/crudAccionesListaEEFF", params = { "rightRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaDerecha(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                      CrudEstadosFinancierosModel crudEstadosFinancierosModel,
                                      final BindingResult bindingResult,
                                      final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");

            return busqueda(caja,
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

    @PostMapping(value = "/crudAccionesListaEEFF", params = { "leftRow", "busqueda", "paginaActual", "paginaFinal" })
    public ModelAndView paginaIzquierda(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                        CrudEstadosFinancierosModel crudEstadosFinancierosModel,
                                        final BindingResult bindingResult,
                                        final HttpServletRequest req) throws Exception {
        try {
            String caja = req.getParameter("busqueda");
            String paginaActual = req.getParameter("paginaActual");
            String paginaFinal = req.getParameter("paginaFinal");

            return busqueda(caja,
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

    @PostMapping(value = "/crudAccionesListaEEFF", params = { "descargarRow" })
    public ResponseEntity<byte[]> descargarPDF(@SessionAttribute("datosGenerales") DatosGenerales datosGenerales,
                                               CrudEstadosFinancierosModel crudEstadosFinancierosModel,
                                               final BindingResult bindingResult,
                                               final HttpServletRequest req) throws Exception {
        ResponseEntity<byte[]> response = null;
        byte[] bytes = null;
        try {
            String idEEFF = req.getParameter("descargarRow");
            EstadosFinancierosModel estadosFinancierosModel;
            String desicion = obtenerConstante(Constante.ORIGEN_ARC);
            estadosFinancierosModel = estadosFinancierosInterface.obtenerEEFF(StringUtil.toInteger(idEEFF));
            String nombreArchivo = estadosFinancierosModel.getNombreArchivo() + "." + estadosFinancierosModel.getTipoArchivo();
            if (StringUtil.equiv(desicion, Constante.POR_FTP)) {
                String fileString = "D:\\ftp\\" + nombreArchivo;
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
            } else {
                bytes = estadosFinancierosModel.getArchivo();
            }

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.parseMediaType("application/pdf"));

            headers.add("content-disposition", "attachment; filename=" + nombreArchivo);

            headers.setContentDispositionFormData(nombreArchivo, nombreArchivo);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
            return response;

        } catch (Exception e) {
            return response;
        }
    }

    private ModelAndView busqueda(String busqueda,
                                  String rucFideicomisario,
                                  String izquierda,
                                  String derecha,
                                  String pagina,
                                  String fin,
                                  CrudEstadosFinancierosModel crudEstadosFinancierosModel) throws Exception {
        CrudEstadosFinancierosModel crudEstadosFinancierosModelLista = new CrudEstadosFinancierosModel();
        List<FideicomisoModel> listFideicomisoModel = new ArrayList<FideicomisoModel>();
        List<GenericModel> listGenericFideicomisoModel = null;
        GenericModel genericModel;
        try {
            ModelAndView mav = new ModelAndView(Constante.LISTA_EEFF);
            PaginadoModel paginadoModel = obtenerMovimientoAndPagina(pagina, fin, izquierda, derecha);

            if (!StringUtil.isEmpty(busqueda)) {
                crudEstadosFinancierosModelLista = estadosFinancierosInterface.listEEFFByFideicomisoPaginado(StringUtil.toInteger(busqueda),
                                                                                                             paginadoModel.getPaginaActual(),
                                                                                                             Constante.PAGINADO_5_ROWS);
            }

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

            listFideicomisoModel = fideicomisoInterface.getFideicomisoModel(rucFideicomisario);
            
            /*listGenericFideicomisoModel = new ArrayList<GenericModel>();
            if (!StringUtil.isEmpty(listFideicomisoModel)) {
                for (FideicomisoModel fideicomisoModel : listFideicomisoModel) {
                    genericModel = new GenericModel();
                    genericModel.setId(StringUtil.toStr(fideicomisoModel.getIdentificadorFideicomiso()));
                    genericModel.setDescripcion(fideicomisoModel.getNombreFideicomiso());
                    listGenericFideicomisoModel.add(genericModel);
                }
            }*/

            /*String comboFideicomisarios = construirComboSearch(Constante.SELECCION_SIMPLE,
                                                               listGenericFideicomisoModel,
                                                               "busqueda",
                                                               "Seleccione Fideicomiso...",
                                                               null);*/

            crudEstadosFinancierosModelLista.setBusqueda(busqueda);
            crudEstadosFinancierosModelLista.setPaginaActual(paginadoModel.getPaginaActual());

            mav.addObject("crudEstadosFinancierosModel", crudEstadosFinancierosModelLista);
            mav.addObject("comboFideicomisarios", listFideicomisoModel);

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
