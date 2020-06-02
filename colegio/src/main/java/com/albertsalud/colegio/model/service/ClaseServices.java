package com.albertsalud.colegio.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albertsalud.colegio.model.entities.Asignatura;
import com.albertsalud.colegio.model.entities.Clase;
import com.albertsalud.colegio.model.entities.Curso;
import com.albertsalud.colegio.model.repositories.AsignaturaDAO;
import com.albertsalud.colegio.model.repositories.ClaseDAO;

@Service
public class ClaseServices {
	
	@Autowired private AsignaturaDAO asignaturaDao;
	@Autowired private ClaseDAO claseDao;

	public Clase getClase(Curso curso, Long idAsignatura) {
		Asignatura asignatura = asignaturaDao.getOne(idAsignatura);
		
		if(curso.getId() == null) return generateNewClase(curso, asignatura);
		return claseDao.findByAsignaturaAndCurso(asignatura, curso)
				.orElseGet(() -> {
					return generateNewClase(curso, asignatura);
				});
	}

	private Clase generateNewClase(Curso curso, Asignatura asignatura) {
		Clase clase = new Clase();
		clase.setAsignatura(asignatura);
		clase.setCurso(curso);
		
		return clase;
	}

}
