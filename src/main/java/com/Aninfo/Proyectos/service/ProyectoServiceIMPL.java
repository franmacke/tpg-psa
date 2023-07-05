package com.Aninfo.Proyectos.service;

import com.Aninfo.Proyectos.dao.ProyectoDAO;
import com.Aninfo.Proyectos.domain.Proyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServiceIMPL implements ProyectoService {

    @Autowired
    private ProyectoDAO proyectoDAO;

    @Override
    @Transactional
    public Optional<Proyecto> guardarProyecto(Proyecto proyecto){
        Proyecto proyectoNuevo = proyectoDAO.save(proyecto);
        return Optional.ofNullable(proyectoNuevo);
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Proyecto> obtenerProyecto(Long id){
        return proyectoDAO.findById(id);
    }

    @Override
    @Transactional
    public void actualizarProyecto(Long id, Proyecto proyecto) {
//        Optional<Proyecto> proyecto1 = proyectoDAO.findById(id);
//        proyecto1.get().actualizarProyecto(proyecto);
        proyectoDAO.save(proyecto);
    }

    @Override
    @Transactional
    public void eliminarProyecto(Long id) {
        proyectoDAO.delete(proyectoDAO.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("No se encontró el proyecto con el ID: " + id, 1)));

    }

    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> listarProyectos(){
        return proyectoDAO.findAll();
    }

}
