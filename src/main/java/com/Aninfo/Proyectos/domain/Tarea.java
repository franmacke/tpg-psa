package com.Aninfo.Proyectos.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "tareas")
@Data
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;
    private Integer horasEstimadas;
    private Integer horasReales;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinal;
    @ManyToOne
    @JoinColumn
    private Proyecto proyecto;

    public Tarea actualizarTarea(Tarea tarea){
        this.nombre = tarea.nombre;
        this.descripcion = tarea.descripcion;
        this.horasEstimadas = tarea.horasEstimadas;
        this.horasReales = tarea.horasReales;
        this.fechaInicio = tarea.fechaInicio;
        this.fechaFinal = tarea.fechaFinal;
        return this;
    }

    public void asignarProyecto(Proyecto proyecto){
        this.setProyecto(proyecto);
    }
}
