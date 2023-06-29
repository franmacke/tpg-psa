package com.Aninfo.Proyectos.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RecursoHumano {
    private int legajo;
    private String Nombre;
    private String Apellido;

    @Override
    public String toString() {
        return "Persona{" +
                "legajo=" + legajo +
                ", nombre='" + Nombre + '\'' +
                ", apellido='" + Apellido + '\'' +
                '}';
    }
}
