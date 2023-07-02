package com.Aninfo.Proyectos.controller;

import com.Aninfo.Proyectos.Enum.EstadoTarea;
import com.Aninfo.Proyectos.domain.RecursoHumano;
import com.Aninfo.Proyectos.domain.Tarea;
import com.Aninfo.Proyectos.service.RecursoService;
import com.Aninfo.Proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:3000", "https://tribu-ikzkz6l85-ivanovic99.vercel.app/"}, maxAge = 3600)
@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private RecursoService recursoService;

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerTarea(@PathVariable Long id) {
        Optional<Tarea> optionalTarea = this.tareaService.obtenerTarea(id);
        if (optionalTarea.isPresent()){
            Tarea tarea = optionalTarea.get();
            return ResponseEntity.ok(tarea);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Tarea> crearTarea(@RequestBody Tarea tarea) {
        Optional<Tarea> results = tareaService.guardarTarea(tarea);
        return results.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        try {
            tareaService.actualizarTarea(id, tarea);
            return ResponseEntity.ok("Se actualizó la tarea correctamente");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La tarea no se actualizó correctamente");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTarea(@PathVariable Long id) {
        try {
            tareaService.eliminarTarea(id);
            return ResponseEntity.ok("La tarea se eliminó correctamente");

        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la tarea a eliminar");
        }

    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tarea>> listarTareas() {
        List<Tarea> tareas = tareaService.listarTareas();
        if (tareas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tareas);
        } else {
            return ResponseEntity.ok(tareas);
        }
    }


    @PutMapping("/finalizarTarea/{id}/{horasReales}/{esfuerzoReal}")
    public ResponseEntity<String> finalizarTarea(@PathVariable Long id, @PathVariable Integer horasReales, @PathVariable Integer esfuerzoReal) {
        Optional<Tarea> optionalTarea = this.tareaService.obtenerTarea(id);
        if (optionalTarea.isPresent()) {
            Tarea tarea = optionalTarea.get();
            tarea.finalizarTarea(horasReales, esfuerzoReal);
            tarea.setEstado(EstadoTarea.COMPLETADO);
            tareaService.guardarTarea(tarea);
            return ResponseEntity.ok("Se finalizó la tarea");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La tarea no existe o no se encontró");
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


    @PostMapping("/asignarRecursos/{idTarea}")
    public ResponseEntity<String> asignarRecursos(@PathVariable Long idTarea, @RequestBody List<RecursoHumano> recursos){
        Optional<Tarea> optionalTarea = this.tareaService.obtenerTarea(idTarea);
        if (optionalTarea.isPresent()) {
            Tarea tarea = optionalTarea.get();
            tarea.setRecursos(recursos.stream().map(RecursoHumano::toString).collect(Collectors.toList()));
            this.tareaService.guardarTarea(tarea);

            return ResponseEntity.ok("Se asignaron los recursos a la tarea");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La tarea no existe o no se encontró");
        }
    }

}
