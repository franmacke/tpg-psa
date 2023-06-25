package com.Aninfo.Proyectos.controller;

import com.Aninfo.Proyectos.domain.Proyecto;
import com.Aninfo.Proyectos.domain.Tarea;
import com.Aninfo.Proyectos.service.ProyectoService;
import com.Aninfo.Proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private TareaService tareaService;

    @GetMapping("/{id}")
    public Proyecto obtenerProyecto(@PathVariable Long id){
        return proyectoService.obtenerProyecto(id).get();
    }

    @GetMapping("/rangodefechas/{fechaInicio}/{fechaFinalizacion}")
    public List<Proyecto> obtenerProyectosEntreRangosDeFecha( @PathVariable("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
                                                              @PathVariable("fechaFinalizacion") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin)  {
        return proyectoService.obtenerProyectosEntreDosRangosDeFechas(fechaInicio, fechaFin);
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

    @GetMapping("/listar")
    public List<Proyecto> listarProyectos(){
        return proyectoService.listarProyectos();
    }

    @PutMapping("/agregarTarea/{idProyecto}/{id}")
    public void agregarTarea(@PathVariable Long idProyecto, @PathVariable Long id){
        Proyecto proyecto = proyectoService.obtenerProyecto(idProyecto).get();
        Tarea tarea = tareaService.obtenerTarea(id).get();
        proyecto.agregarTarea(tarea);
        proyectoService.guardarProyecto(proyecto);
        tarea.asignarProyecto(proyecto);
        tareaService.guardarTarea(tarea);
    }
}
