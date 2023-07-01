package com.Aninfo.Proyectos.service;

import com.Aninfo.Proyectos.domain.Proyecto;
import com.Aninfo.Proyectos.domain.Tarea;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TareaService {

    Optional<Tarea> guardarTarea(Tarea tarea);

    Optional<Tarea> obtenerTarea(Long id);

    void actualizarTarea(Long id, Tarea tarea);

    void eliminarTarea(Long id);

    List<Tarea> listarTareas();
}
