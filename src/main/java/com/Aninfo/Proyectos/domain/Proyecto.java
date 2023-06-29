package com.Aninfo.Proyectos.domain;

import com.Aninfo.Proyectos.Enum.EstadoProyecto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "proyectos")
@Data
public class Proyecto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String descripcion;
    private EstadoProyecto estado;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinalizacion;
    private String lider;
    @OneToMany
    private List<Tarea> tareas;

    public Proyecto actualizarProyecto(Proyecto proyecto){
        this.nombre = proyecto.nombre;
        this.lider = proyecto.lider;
        this.fechaFinalizacion = proyecto.fechaFinalizacion;
        this.fechaInicio = proyecto.fechaInicio;
        return this;
    }

    public void agregarTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

}
