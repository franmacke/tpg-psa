package com.Aninfo.Proyectos.controller;

import com.Aninfo.Proyectos.domain.Proyecto;
import com.Aninfo.Proyectos.domain.RecursoHumano;
import com.Aninfo.Proyectos.domain.Tarea;
import com.Aninfo.Proyectos.service.ProyectoService;
import com.Aninfo.Proyectos.service.RecursoService;
import com.Aninfo.Proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private TareaService tareaService;

    @Autowired
    private RecursoService recursoService;

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

    @GetMapping("/horasRealesDelProyecto/{idProyecto}")
    public Integer obtenerHorasDelProyecto(@PathVariable long idProyecto){
        Proyecto proyecto = proyectoService.obtenerProyecto(idProyecto).get();

        if (proyecto.getTareas().size() == 0) return 0;
        AtomicReference<Integer> counter = new AtomicReference<>(0);
        proyecto.getTareas().forEach(x -> counter.updateAndGet(v -> v + x.getHorasReales()));
        return counter.get();
    }


    @PostMapping("/asignarLider/{idProyecto}/{lider}")
    public void asignarLider(@PathVariable Long idProyecto, @PathVariable String lider){
        Proyecto proyecto = this.proyectoService.obtenerProyecto(idProyecto).get();
        proyecto.setLider(lider);
        this.proyectoService.actualizarProyecto(idProyecto, proyecto);
    }

    @PutMapping("/cambiarEstadoAlSiguiente/{idProyecto}")
    public void cambiarEstadoAlSiguiente(@PathVariable Long idProyecto){
        Proyecto proyecto = this.proyectoService.obtenerProyecto(idProyecto).get();
        proyecto.setEstado(proyecto.getEstado().siguienteEstado());
        this.proyectoService.guardarProyecto(proyecto);
    }

    @PutMapping("/cambiarEstadoAlAnterior/{idProyecto}")
    public void cambiarEstadoAlAnterior(@PathVariable Long idProyecto){
        Proyecto proyecto = this.proyectoService.obtenerProyecto(idProyecto).get();
        proyecto.setEstado(proyecto.getEstado().estadoAnterior());
        this.proyectoService.guardarProyecto(proyecto);
    }

    @GetMapping("/obtenerRecursos")
    public List<RecursoHumano> obtenerRecursos(){
        return recursoService.obtenerRecursos();
    }

    @GetMapping("/{idProyecto}/listarTareas")
    public List<Tarea> listarTareas(@PathVariable Long idProyecto){
        return this.proyectoService.obtenerProyecto(idProyecto).get().getTareas();
    }
}
