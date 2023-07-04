package com.Aninfo.Proyectos.service;

import com.Aninfo.Proyectos.dao.TareaDAO;
import com.Aninfo.Proyectos.domain.Proyecto;
import com.Aninfo.Proyectos.domain.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceIMPL implements TareaService{

    @Autowired
    private TareaDAO tareaDAO;

    @Override
    @Transactional
    public Optional<Tarea> guardarTarea(Tarea tarea) {
        Tarea nuevaTarea = tareaDAO.save(tarea);

        return Optional.ofNullable(nuevaTarea);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tarea> obtenerTarea(Long id) {
        return tareaDAO.findById(id);
    }

    @Override
    @Transactional
    public void actualizarTarea(Long id, Tarea tarea) {
        Optional<Tarea> tareaFind = tareaDAO.findById(id);
        tareaFind.ifPresent(value -> value.actualizarTarea(tarea));
    }

    @Override
    @Transactional
    public void eliminarTarea(Long id) {
        tareaDAO.delete(tareaDAO.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No se encontró la tarea con el ID: " + id, 1)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> listarTareas() {
        return tareaDAO.findAll();
    }

}
