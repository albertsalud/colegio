package com.albertsalud.colegio.model.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.albertsalud.colegio.dto.EvaluacionDTO;
import com.albertsalud.colegio.error.UserNotFoundException;
import com.albertsalud.colegio.model.entities.Alumno;
import com.albertsalud.colegio.model.entities.Asignatura;
import com.albertsalud.colegio.model.entities.Clase;
import com.albertsalud.colegio.model.entities.Curso;
import com.albertsalud.colegio.model.entities.Evaluacion;
import com.albertsalud.colegio.model.entities.Profesor;
import com.albertsalud.colegio.model.repositories.AlumnoDAO;
import com.albertsalud.colegio.model.repositories.AsignaturaDAO;
import com.albertsalud.colegio.model.repositories.ClaseDAO;
import com.albertsalud.colegio.model.repositories.CursoDAO;
import com.albertsalud.colegio.model.repositories.EvaluacionDAO;
import com.albertsalud.colegio.model.repositories.ProfesorDAO;

import lombok.extern.java.Log;

@Log
@Service
public class ProfesorServices extends UsuarioServices{

	@Autowired
	private ProfesorDAO profesorDao;

	@Autowired
	private AlumnoDAO alumnoDao;

	@Autowired
	private AsignaturaDAO asignaturaDao;

	@Autowired
	private CursoDAO cursoDao;

	@Autowired
	private ClaseDAO claseDao;

	@Autowired
	private EvaluacionDAO evaluacionDao;
	
	public Profesor getProfesor(long idProfesor) {
		Profesor p = profesorDao.findById(idProfesor).orElseThrow(()-> new UserNotFoundException("Profesor no encontrado"));
		p.getClases().size();
		
		return p;
	}
	
	
	@Transactional
	public List<EvaluacionDTO> getEvaluaciones(long curso, long asignatura) {
		Curso c = cursoDao.getOne(curso);
		Asignatura a = asignaturaDao.getOne(asignatura);
		
		Clase clase = claseDao.findByAsignaturaAndCurso(a, c).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
		log.info(clase.getEvaluaciones().size() + " evaluacion/es encontrada/s");
		return clase.getEvaluaciones().stream()
								.map(e -> {
									return EvaluacionDTO.builder()
												.idAlumno(e.getAlumno().getId())
												.idClase(e.getClase().getId())
												.nombreAlumno(e.getAlumno().getNombre() + " " + e.getAlumno().getApellidos())
												.nota(e.getNota()).build();
								})
								.collect(Collectors.toList());
				
	}

	
	public List<Profesor> listProfesores() {
		return profesorDao.findAll(Sort.by("apellidos", "nombre"));
	}


	@Transactional
	public void saveEvaluaciones(List<EvaluacionDTO> evaluaciones) {
		
		evaluaciones.forEach(dto -> {
			Clase c = claseDao.getOne(dto.getIdClase());
			Alumno a = alumnoDao.getOne(dto.getIdAlumno());
			
			Evaluacion e = evaluacionDao.findByClaseAndAlumno(c, a).orElseThrow(() -> new RuntimeException("Evaluacion no encontrada"));
			e.setNota(dto.getNota());
			
			evaluacionDao.save(e);
		});
		
	}

}
