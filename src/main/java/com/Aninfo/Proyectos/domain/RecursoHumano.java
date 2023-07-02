package com.Aninfo.Proyectos.domain;

import jakarta.persistence.*;
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
        return "legajo=" + legajo +
                ", nombre='" + Nombre + '\'' +
                ", apellido='" + Apellido;
    }
}
