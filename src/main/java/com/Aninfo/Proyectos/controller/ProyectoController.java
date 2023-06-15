package com.Aninfo.Proyectos.controller;

import com.Aninfo.Proyectos.domain.Proyecto;
import com.Aninfo.Proyectos.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("/{id}")
    public Proyecto obtenerProyecto(@PathVariable Long id){
        return proyectoService.obtenerProyecto(id).get();
    }

    @PostMapping
    public void crearProyecto(@RequestBody Proyecto proyecto){
        proyectoService.guardarProyecto(proyecto);
    }

    @PutMapping("/{id}")
    public void actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyecto){
        proyectoService.actualizarProyecto(id, proyecto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProyecto(@PathVariable Long id){
        proyectoService.eliminarProyecto(id);
    }
}
