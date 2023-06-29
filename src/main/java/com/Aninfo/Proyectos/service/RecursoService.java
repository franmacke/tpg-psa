package com.Aninfo.Proyectos.service;

import com.Aninfo.Proyectos.client.RecursoClient;
import com.Aninfo.Proyectos.domain.RecursoHumano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecursoService {

    @Autowired
    private RecursoClient recursoClient;

    public List<RecursoHumano> obtenerLideres() {
        return recursoClient.getRecursos();
    }
}
