package com.Aninfo.Proyectos.client;

import com.Aninfo.Proyectos.domain.RecursoHumano;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RecursoClient {

    RestTemplate restTemplate = new RestTemplate();

    ParameterizedTypeReference<List<RecursoHumano>> responseType =
            new ParameterizedTypeReference<List<RecursoHumano>>() {};


    public List<RecursoHumano> getRecursos() {
        ResponseEntity<List<RecursoHumano>> response = restTemplate.exchange(
//                "https://649cde839bac4a8e669d0392.mockapi.io/api/v1/recurso",
                "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.1/m/api/recursos",
                HttpMethod.GET,
                null,
                responseType);


        return response.getBody();
    }
}
