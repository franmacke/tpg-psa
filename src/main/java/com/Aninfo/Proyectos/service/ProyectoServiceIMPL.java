package com.Aninfo.Proyectos.service;

import com.Aninfo.Proyectos.dao.ProyectoDAO;
import com.Aninfo.Proyectos.domain.Proyecto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void guardarProyecto(Proyecto proyecto){
        proyectoDAO.save(proyecto);
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Proyecto> obtenerProyecto(Long id){
        return proyectoDAO.findById(id);
    }

    @Override
    @Transactional
    public void actualizarProyecto(Long id, Proyecto proyecto) {
        Optional<Proyecto> proyecto1 = proyectoDAO.findById(id);
        proyecto1.get().actualizarProyecto(proyecto);
        proyectoDAO.save(proyecto1.get());
    }

    @Override
    @Transactional
    public void eliminarProyecto(Long id) {
        proyectoDAO.delete(proyectoDAO.findById(id).get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> listarProyectos(){
        return (proyectoDAO.findAll());
    }
}
