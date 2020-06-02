package com.albertsalud.colegio.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.colegio.model.entities.Alumno;
import com.albertsalud.colegio.model.entities.Clase;
import com.albertsalud.colegio.model.entities.Evaluacion;

public interface EvaluacionDAO extends JpaRepository<Evaluacion, Long>{
	
	public Optional<Evaluacion> findByClaseAndAlumno(Clase c, Alumno a);

}
