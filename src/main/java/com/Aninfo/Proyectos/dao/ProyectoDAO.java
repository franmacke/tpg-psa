package com.Aninfo.Proyectos.dao;

import com.Aninfo.Proyectos.domain.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProyectoDAO extends JpaRepository<Proyecto, Long> {


    @Query("SELECT c FROM  Proyecto c WHERE c.fechaInicio >= :fechaInicio and c.fechaFinalizacion <= :fechaFin")
    List<Proyecto> obtenerProyectosEntreDosFechas(LocalDate fechaInicio, LocalDate fechaFin);
}
