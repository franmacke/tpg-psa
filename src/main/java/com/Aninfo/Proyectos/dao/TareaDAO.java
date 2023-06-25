package com.Aninfo.Proyectos.dao;


import com.Aninfo.Proyectos.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaDAO extends JpaRepository<Tarea, Long> {
}
