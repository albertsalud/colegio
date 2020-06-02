package com.albertsalud.colegio.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.colegio.model.entities.Asignatura;
import com.albertsalud.colegio.model.entities.Clase;
import com.albertsalud.colegio.model.entities.Curso;


public interface ClaseDAO extends JpaRepository<Clase, Long> {
	
	public Optional<Clase> findByAsignaturaAndCurso(Asignatura a, Curso c);

}
