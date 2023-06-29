package com.Aninfo.Proyectos.domain;

import com.Aninfo.Proyectos.Enum.EstadoTarea;
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
    private EstadoTarea estado;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinal;
    private Integer esfuerzoEstimado;
    private Integer esfuerzoReal;
    @JoinColumn
    private long idProyecto;

    public Tarea actualizarTarea(Tarea tarea){
        this.nombre = tarea.nombre;
        this.descripcion = tarea.descripcion;
        this.horasEstimadas = tarea.horasEstimadas;
        this.horasReales = tarea.horasReales;
        this.fechaInicio = tarea.fechaInicio;
        this.fechaFinal = tarea.fechaFinal;
        this.esfuerzoEstimado = tarea.esfuerzoEstimado;
        this.esfuerzoReal = tarea.esfuerzoReal;
        this.estado = tarea.estado;
        return this;
    }

    public void finalizarTarea(Integer horasReales, Integer esfuerzoReal){
        this.esfuerzoReal = esfuerzoReal;
        this.horasReales = horasReales;
        this.estado = EstadoTarea.COMPLETADO;
    }

    public void asignarProyecto(Proyecto proyecto){
        this.setIdProyecto(proyecto.getId());
    }
}
