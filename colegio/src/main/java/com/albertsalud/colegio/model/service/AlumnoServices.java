package com.albertsalud.colegio.model.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.albertsalud.colegio.error.UserNotFoundException;
import com.albertsalud.colegio.model.entities.Alumno;
import com.albertsalud.colegio.model.repositories.AlumnoDAO;

@Service
public class AlumnoServices {

	@Autowired
	private AlumnoDAO alumnoDao;
	
	@Transactional
	public Alumno getAlumno(Long idAlumno) {
		Alumno alumno = alumnoDao.findById(idAlumno).orElseThrow(()-> new UserNotFoundException("Alumno no encontrado"));
		alumno.getEvaluaciones().size();
		return alumno;
	}

}
