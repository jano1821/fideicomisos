package com.corfid.fideicomisos.service.impl.utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.utilities.RequestBodyEnvioSmsModel;
import com.corfid.fideicomisos.model.utilities.RequestValidacionPinModel;
import com.corfid.fideicomisos.model.utilities.ResponseEnvioSmsModel;
import com.corfid.fideicomisos.model.utilities.ResponseValidacionPinModel;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.service.utilities.RestClientInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Service("restClientRecoveryPasswordPin")
public class RestClientRecoveryPasswordPin implements RestClientInterface {

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    static RestTemplate restTemplate = new RestTemplate();

    public String getObtenerPinValidacion(String numero) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            List<CatalogoConstraintModel> listCatalogoConstraintModelParametros;
            String url = "";
            String authorization = "";
            String applicationId = "";
            String messageId = "";
            String from = "";

            listCatalogoConstraintModelParametros = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_SERVICIO,
                                                                                                                Constante.PARAMETROS);

            for (CatalogoConstraintModel catalogoConstraintModel : listCatalogoConstraintModelParametros) {
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "URL_ENV")) {
                    url = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "AUTH")) {
                    authorization = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "APP_ID")) {
                    applicationId = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "MES_ID")) {
                    messageId = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "FROM")) {
                    from = catalogoConstraintModel.getValorConstraint();
                }
            }

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", authorization);

            RequestBodyEnvioSmsModel bodyEnvioSmsModel = new RequestBodyEnvioSmsModel();

            bodyEnvioSmsModel.setApplicationId(applicationId);
            bodyEnvioSmsModel.setMessageId(messageId);
            bodyEnvioSmsModel.setFrom(from);
            bodyEnvioSmsModel.setTo("+51" + numero);
            bodyEnvioSmsModel.setPlaceholders("");

            HttpEntity<RequestBodyEnvioSmsModel> entity = new HttpEntity<>(bodyEnvioSmsModel, headers);

            ResponseEntity<ResponseEnvioSmsModel> responseEnvioSmsModel = restTemplate.exchange(url,
                                                                                                HttpMethod.POST,
                                                                                                entity,
                                                                                                ResponseEnvioSmsModel.class);

            return responseEnvioSmsModel.getBody().getPinId();
        } catch (Exception e) {
            return ConstantesError.ERROR_1;
        }
    }

    public String validarPin(String codigo, String pin) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            List<CatalogoConstraintModel> listCatalogoConstraintModelParametros;
            String url = "";
            String authorization = "";
            String codigoError = ConstantesError.ERROR_0;

            listCatalogoConstraintModelParametros = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_SERVICIO,
                                                                                                                Constante.PARAMETROS);

            for (CatalogoConstraintModel catalogoConstraintModel : listCatalogoConstraintModelParametros) {
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "URL_VAL")) {
                    url = catalogoConstraintModel.getValorConstraint();
                    url = url.replace("REEMPLAZAR", codigo);
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "AUTH")) {
                    authorization = catalogoConstraintModel.getValorConstraint();
                }
            }

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", authorization);

            RequestValidacionPinModel requestValidacionPinModel = new RequestValidacionPinModel();

            requestValidacionPinModel.setPin(pin);

            HttpEntity<RequestValidacionPinModel> entity = new HttpEntity<>(requestValidacionPinModel, headers);

            ResponseEntity<ResponseValidacionPinModel> responseValidacionPinModel = restTemplate.exchange(url,
                                                                                                          HttpMethod.POST,
                                                                                                          entity,
                                                                                                          ResponseValidacionPinModel.class);

            if (StringUtil.equiv(responseValidacionPinModel.getBody().getVerified(), "false")) {
                if (StringUtil.equiv(responseValidacionPinModel.getBody().getPinError(), "TTL_EXPIRED")) {
                    codigoError = ConstantesError.ERROR_30;
                } else if (StringUtil.equiv(responseValidacionPinModel.getBody().getPinError(), "WRONG_PIN")) {
                    codigoError = ConstantesError.ERROR_31;
                }
            } else {
                codigoError = ConstantesError.ERROR_0;
            }
            return codigoError;
        } catch (Exception e) {
            return ConstantesError.ERROR_1;
        }
    }
}
