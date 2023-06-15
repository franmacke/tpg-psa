package com.Aninfo.Proyectos.dao;

import com.Aninfo.Proyectos.domain.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoDAO extends JpaRepository<Proyecto, Long> {
}
