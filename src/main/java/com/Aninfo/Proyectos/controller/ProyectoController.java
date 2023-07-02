package com.Aninfo.Proyectos.controller;

import com.Aninfo.Proyectos.domain.Proyecto;
import com.Aninfo.Proyectos.domain.RecursoHumano;
import com.Aninfo.Proyectos.domain.Tarea;
import com.Aninfo.Proyectos.service.ProyectoService;
import com.Aninfo.Proyectos.service.RecursoService;
import com.Aninfo.Proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin(origins = {"http://localhost:3000", "https://tribu-ikzkz6l85-ivanovic99.vercel.app/"}, maxAge = 3600)
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
    public ResponseEntity<Proyecto> obtenerProyecto(@PathVariable Long id){
        Optional<Proyecto> optionalProyecto = proyectoService.obtenerProyecto(id);
        if (optionalProyecto.isPresent()){
            Proyecto proyecto = optionalProyecto.get();
            return ResponseEntity.ok(proyecto);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto){
        Optional<Proyecto> results = proyectoService.guardarProyecto(proyecto);
        return results.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProyecto(@PathVariable Long id, @RequestBody Proyecto proyecto){
        try {
            proyectoService.actualizarProyecto(id, proyecto);
            return ResponseEntity.ok("Se actualizó la tarea correctamente");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La tarea no se actualizó correctamente");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProyecto(@PathVariable Long id){
        try {
            proyectoService.eliminarProyecto(id);
            return ResponseEntity.ok("El proyecto se eliminó correctamente");

        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el proyecto a eliminar");
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Proyecto>> listarProyectos(){

        List<Proyecto> proyectos = proyectoService.listarProyectos();
        if (proyectos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(proyectos);
        } else {
            return ResponseEntity.ok(proyectos);
        }
    }

    @PutMapping("/agregarTarea/{idProyecto}/{idTarea}")
    public ResponseEntity<String> agregarTarea(@PathVariable Long idProyecto, @PathVariable Long idTarea){

        Optional<Proyecto> optionalProyecto = proyectoService.obtenerProyecto(idProyecto);
        Optional<Tarea> optionalTarea = tareaService.obtenerTarea(idTarea);
        if (optionalProyecto.isPresent() && optionalTarea.isPresent()){
            Tarea tarea = optionalTarea.get();
            Proyecto proyecto = optionalProyecto.get();
            proyecto.agregarTarea(tarea);
            proyectoService.guardarProyecto(proyecto);
            tarea.asignarProyecto(proyecto);
            tareaService.guardarTarea(tarea);
            return ResponseEntity.ok("Se asigno la tarea al proyecto de manera correcta");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la tarea o el proyecto");
        }

    }

    @GetMapping("/horasRealesDelProyecto/{idProyecto}")
    public ResponseEntity<Integer> obtenerHorasDelProyecto(@PathVariable long idProyecto){

        Optional<Proyecto> optionalProyecto = proyectoService.obtenerProyecto(idProyecto);
        if (optionalProyecto.isPresent()) {
            Proyecto proyecto = optionalProyecto.get();
            if (proyecto.getTareas().size() == 0) ResponseEntity.ok().body(0);
            AtomicReference<Integer> counter = new AtomicReference<>(0);
            proyecto.getTareas().forEach(x -> counter.updateAndGet(v -> v + x.getHorasReales()));
            return ResponseEntity.ok().body(counter.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @PostMapping("/asignarLider/{idProyecto}/{lider}")
    public ResponseEntity<String> asignarLider(@PathVariable Long idProyecto, @PathVariable String lider){
        Optional<Proyecto> optionalProyecto = this.proyectoService.obtenerProyecto(idProyecto);
        if (optionalProyecto.isPresent()){
            Proyecto proyecto = optionalProyecto.get();
            proyecto.setLider(lider);
            this.proyectoService.actualizarProyecto(idProyecto, proyecto);
            return ResponseEntity.ok("Se asigno el lider correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto no existe o no se encontró");
        }

    }


    @GetMapping("/obtenerRecursos")
    public ResponseEntity<List<RecursoHumano>> obtenerRecursos(){
        try {
            List<RecursoHumano> recursos = recursoService.obtenerRecursos();
            return ResponseEntity.ok(recursos);
        } catch (Throwable e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{idProyecto}/listarTareas")
    public ResponseEntity<List<Tarea>> listarTareas(@PathVariable Long idProyecto){
        Optional<Proyecto> proyecto = this.proyectoService.obtenerProyecto(idProyecto);
        if (proyecto.isPresent()){
            List<Tarea> tareas = proyecto.get().getTareas();
            return ResponseEntity.ok().body(tareas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
