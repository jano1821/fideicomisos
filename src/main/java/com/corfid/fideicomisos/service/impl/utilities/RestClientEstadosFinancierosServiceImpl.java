package com.corfid.fideicomisos.service.impl.utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.corfid.fideicomisos.model.administrativo.CatalogoConstraintModel;
import com.corfid.fideicomisos.model.utilities.ResponseTokenArchivos;
import com.corfid.fideicomisos.service.utilities.CatalogoConstraintInterface;
import com.corfid.fideicomisos.service.utilities.RestClientEstadosFinancierosInterface;
import com.corfid.fideicomisos.utilities.Constante;
import com.corfid.fideicomisos.utilities.ConstantesError;
import com.corfid.fideicomisos.utilities.StringUtil;

@Service("restClientEstadosFinancierosServiceImpl")
public class RestClientEstadosFinancierosServiceImpl implements RestClientEstadosFinancierosInterface {

    @Autowired
    @Qualifier("catalogoConstraintServiceImpl")
    private CatalogoConstraintInterface catalogoConstraintInterface;

    static RestTemplate restTemplate = new RestTemplate();

    public String getObtenerToken() throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            List<CatalogoConstraintModel> listCatalogoConstraintModelParametros;
            String url = "";
            String authorization = "";
            String site = "";
            String parauso = "";
            String parcnt = "";

            listCatalogoConstraintModelParametros = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_SERVICIO,
                                                                                                                Constante.PARAMETROS);

            for (CatalogoConstraintModel catalogoConstraintModel : listCatalogoConstraintModelParametros) {
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "URL_ARC")) {
                    url = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "AUTH_ARC")) {
                    authorization = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "SITE")) {
                    site = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "PARAUSO")) {
                    parauso = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "PARCNT")) {
                    parcnt = catalogoConstraintModel.getValorConstraint();
                }
            }

            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", authorization);

            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("site", site);
            parts.add("paruso", parauso);
            parts.add("parcnt", parcnt);

            ResponseTokenArchivos responseTokenArchivos = restTemplate.postForObject(url, new HttpEntity<>(parts, headers),ResponseTokenArchivos.class);

            return responseTokenArchivos.getJsonNode().getAccess_token();
        } catch (Exception e) {
            return ConstantesError.ERROR_1;
        }
    }

    public byte[] ObtenerArchivos(String token, String ruta, String archivo) throws Exception {
        try {
            HttpHeaders headers = new HttpHeaders();
            List<CatalogoConstraintModel> listCatalogoConstraintModelParametros;
            String url = "";
            String authorization = "";
            String site = "";

            listCatalogoConstraintModelParametros = catalogoConstraintInterface.findByNombreTablaAndNombreCampo(Constante.TABLA_SERVICIO,
                                                                                                                Constante.PARAMETROS);

            for (CatalogoConstraintModel catalogoConstraintModel : listCatalogoConstraintModelParametros) {
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "URL_DOW")) {
                    url = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "AUTH_DOW")) {
                    authorization = catalogoConstraintModel.getValorConstraint();
                }
                if (StringUtil.equiv(catalogoConstraintModel.getDescConstraint(), "SITE_DOW")) {
                    site = catalogoConstraintModel.getValorConstraint();
                }
            }

            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", authorization);

            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("site", site);
            parts.add("siteFolder", ruta);
            parts.add("siteFile", archivo);
            parts.add("token", token);

            ByteArrayResource resource = restTemplate.postForObject(url, new HttpEntity<>(parts, headers),ByteArrayResource.class);

            return resource.getByteArray();
        } catch (Exception e) {
            return null;
        }
    }

}
