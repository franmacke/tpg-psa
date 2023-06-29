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
             "https://api-recursos.onrender.com/recursos",
                HttpMethod.GET,
                null,
                responseType);


        return response.getBody();
    }
}
