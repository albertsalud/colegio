package com.albertsalud.colegio.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albertsalud.colegio.model.entities.Alumno;
import com.albertsalud.colegio.model.entities.Curso;
import com.albertsalud.colegio.model.entities.Evaluacion;
import com.albertsalud.colegio.model.repositories.AlumnoDAO;
import com.albertsalud.colegio.model.repositories.EvaluacionDAO;

@Service
public class EvaluacionServices {
	
	@Autowired private EvaluacionDAO evaluacionDao;
	@Autowired private AlumnoDAO alumnoDao;

	public void checkEvaluaciones(Curso curso) {
		List<Alumno> alumnos = alumnoDao.findByCurso(curso);
		if(alumnos == null || alumnos.isEmpty()) return;
		
		curso.getClases().forEach(clase -> {
			alumnos.forEach(alumno ->{
				if(!evaluacionDao.findByClaseAndAlumno(clase, alumno).isPresent()) {
					Evaluacion evaluacion = Evaluacion.builder()
										.alumno(alumno)
										.clase(clase)
										.build();
					
					evaluacionDao.save(evaluacion);
				}
			});
		});
		
	}

}
