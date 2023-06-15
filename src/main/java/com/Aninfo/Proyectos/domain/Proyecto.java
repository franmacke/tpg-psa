package com.Aninfo.Proyectos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "proyectos")
@Data
public class Proyecto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private LocalDate fechaInicio;

    private LocalDate fechaFinalizacion;

    private String lider;

    public Proyecto actualizarProyecto(Proyecto proyecto){
        this.nombre = proyecto.nombre;
        this.lider = proyecto.lider;
        this.fechaFinalizacion = proyecto.fechaFinalizacion;
        this.fechaInicio = proyecto.fechaInicio;

        return this;
    }
}
