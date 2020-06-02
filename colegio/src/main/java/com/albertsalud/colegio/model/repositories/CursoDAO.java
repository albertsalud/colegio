package com.albertsalud.colegio.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.colegio.model.entities.Curso;

public interface CursoDAO extends JpaRepository<Curso, Long> {

}
