package com.Aninfo.Proyectos.service;

import com.Aninfo.Proyectos.domain.Proyecto;
import java.util.List;
import java.util.Optional;

public interface ProyectoService {

    Optional<Proyecto> guardarProyecto(Proyecto proyecto);

    Optional<Proyecto> obtenerProyecto(Long id);

    void actualizarProyecto(Long id, Proyecto proyecto);

    void eliminarProyecto(Long id);

    List<Proyecto> listarProyectos();

}
