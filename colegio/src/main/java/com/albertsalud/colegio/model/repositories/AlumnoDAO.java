package com.albertsalud.colegio.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.colegio.model.entities.Alumno;
import com.albertsalud.colegio.model.entities.Curso;

public interface AlumnoDAO extends JpaRepository<Alumno, Long>{
	
	public List<Alumno> findByCurso(Curso curso);

}
