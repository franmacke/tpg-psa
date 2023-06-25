package com.Aninfo.Proyectos.controller;

import com.Aninfo.Proyectos.domain.Tarea;
import com.Aninfo.Proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/{id}")
    public Tarea obtenerTarea(@PathVariable Long id){
        return tareaService.obtenerTarea(id).get();
    }

    @PostMapping
    public void crearTarea(@RequestBody Tarea tarea){
        tareaService.guardarTarea(tarea);
    }

    @PutMapping("/{id}")
    public void actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea){
        tareaService.actualizarTarea(id, tarea);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id){
        tareaService.eliminarTarea(id);
    }

    @GetMapping("/listar")
    public List<Tarea> listarTareas(){
        return tareaService.listarTareas();
    }
}
