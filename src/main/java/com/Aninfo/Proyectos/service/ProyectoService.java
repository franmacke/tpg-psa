package com.Aninfo.Proyectos.service;

import com.Aninfo.Proyectos.domain.Proyecto;

import java.util.Optional;

public interface ProyectoService {

    void guardarProyecto(Proyecto proyecto);

    Optional<Proyecto> obtenerProyecto(Long id);

    void actualizarProyecto(Long id, Proyecto proyecto);

    void eliminarProyecto(Long id);
}
