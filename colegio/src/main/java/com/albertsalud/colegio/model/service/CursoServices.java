package com.albertsalud.colegio.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.albertsalud.colegio.error.UserNotFoundException;
import com.albertsalud.colegio.model.entities.Alumno;
import com.albertsalud.colegio.model.entities.Clase;
import com.albertsalud.colegio.model.entities.Curso;
import com.albertsalud.colegio.model.entities.Evaluacion;
import com.albertsalud.colegio.model.repositories.AlumnoDAO;
import com.albertsalud.colegio.model.repositories.CursoDAO;
import com.albertsalud.colegio.model.repositories.EvaluacionDAO;
import com.albertsalud.colegio.model.repositories.ProfesorDAO;

@Service
public class CursoServices {
	
	@Autowired private CursoDAO cursoDao;
	@Autowired private AlumnoDAO alumnoDao;
	@Autowired private EvaluacionDAO evaluacionDao;
	@Autowired private ProfesorDAO profesorDao;
//	@Autowired private AsignaturaDAO asignaturaDao;
//	@Autowired private ClaseDAO claseDao;
	
	@Autowired private EvaluacionServices evaluacionServices;
	@Autowired private ClaseServices claseServices;
	
	
	public void addAlumno(Long idCurso, Long idAlumno) {
		
		Curso curso = cursoDao.findById(idCurso).orElseThrow(() -> new RuntimeException("No existe el curso"));
		this.addAlumno(curso, idAlumno);
		
	}
	
	public void addAlumno(Curso curso, Long idAlumno) {
		Alumno alumno = alumnoDao.findById(idAlumno).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
		
		this.addAlumno(curso, alumno);
	}
	
	public void addAlumno(Curso curso, Alumno alumno) {
		curso.getClases().forEach(c -> {
			Evaluacion e = Evaluacion.builder()
							.alumno(alumno)
							.clase(c)
							.build();
			
			evaluacionDao.save(e);
		});
	}

	public List<Curso> listCursos() {
		return cursoDao.findAll(Sort.by("nombre", "linea"));
	}

	@Transactional
	public Curso saveCurso(Curso cursoToSave) {
		Curso cursoToReturn = cursoDao.save(cursoToSave);
		evaluacionServices.checkEvaluaciones(cursoToReturn);		
		
		return cursoToReturn;
		
	}

	@Transactional
	public Curso getCurso(long idCurso) {
		Curso c = cursoDao.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
		c.getClases().size();
		
		return c;
	}

	
	public void setClases(Curso cursoToSave, String[] idAsignatura, String[] idProfesor) {
		for(int i = 0; i < idAsignatura.length ; i ++) {
			Clase clase = claseServices.getClase(cursoToSave, Long.valueOf(idAsignatura[i]));
			clase.setProfesor(profesorDao.getOne(Long.valueOf(idProfesor[i])));
			
			cursoToSave.addClase(clase);
		}
		
	}

}
