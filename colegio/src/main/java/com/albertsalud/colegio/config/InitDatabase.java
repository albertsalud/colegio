package com.albertsalud.colegio.config;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.albertsalud.colegio.model.entities.Alumno;
import com.albertsalud.colegio.model.entities.Asignatura;
import com.albertsalud.colegio.model.entities.Clase;
import com.albertsalud.colegio.model.entities.Curso;
import com.albertsalud.colegio.model.entities.Director;
import com.albertsalud.colegio.model.entities.Profesor;
import com.albertsalud.colegio.model.repositories.AsignaturaDAO;
import com.albertsalud.colegio.model.repositories.CursoDAO;
import com.albertsalud.colegio.model.repositories.ProfesorDAO;
import com.albertsalud.colegio.model.service.CursoServices;
import com.albertsalud.colegio.model.service.UsuarioServices;

@Configuration
public class InitDatabase {
	
	@Autowired
	private UsuarioServices usuarioServices;
	
	@Autowired
	private CursoServices cursoServices;
	
	@Autowired
	private CursoDAO cursoDao;
	
	@Autowired
	private AsignaturaDAO asignaturaDao;
	
	@Autowired
	private ProfesorDAO profesorDao;
	
	private Curso curso;
	
	@PostConstruct
	private void populateDatabase() {
		populateAsignaturas();
		populateProfesores();
		populateDirectores();
		
		
		populateCursos();
		populateAlumnos();
		
		
	}

	private void populateDirectores() {
		Director director = new Director();
		director.setApellidos("Salud García");
		director.setContrasena("albert");
		director.setDni("albert");
		director.setEmail("albert@gmail.com");
		director.setNombre("Albert");
		
		usuarioServices.save(director);
		
	}

	private void populateProfesores() {
		Profesor profe1 = new Profesor();
		profe1.setApellidos("Vilaplana");
		profe1.setContrasena("vilaplana");
		profe1.setDni("1111");
		profe1.setEmail("mvilaplana@gmail.com");
		profe1.setNombre("Marc");
		profe1.setTelefonoContacto("666555444");
		
		Profesor profe2 = new Profesor();
		profe2.setApellidos("Melé");
		profe2.setContrasena("mele");
		profe2.setDni("2222");
		profe2.setEmail("imele@gmail.com");
		profe2.setNombre("Ignasi");
		profe2.setTelefonoContacto("666555000");

		usuarioServices.save(profe1);
		usuarioServices.save(profe2);
		
	}

	private void populateAsignaturas() {
		
		List<Asignatura> asignaturas = Arrays.asList(
				new Asignatura("Matemáticas"),
				new Asignatura("Lenguaje"),
				new Asignatura("Catalán"),
				new Asignatura("Gimnasia"),
				new Asignatura("Inglés"),
				new Asignatura("Ciencias sociales"),
				new Asignatura("Ciencias naturales"),
				new Asignatura("Filosofía"),
				new Asignatura("Física y química"),
				new Asignatura("Música")
				);
		
		asignaturaDao.saveAll((Iterable) asignaturas);
		
	}

	private void populateAlumnos() {
		Calendar c = Calendar.getInstance();
		c.set(1982, Calendar.MAY, 11);
		
		Alumno alumno = new Alumno();
		alumno.setApellidos("Salud Domingo");
		alumno.setContrasena("1234");
		alumno.setDni("12345678Z");
		alumno.setEmail("unaisalud@gmail.com");
		alumno.setFechaDeNacimiento(c.getTime());
		alumno.setNombre("Unai");
		
		alumno.setCurso(curso);
		
		
		Alumno alumno2 = new Alumno();
		alumno2.setApellidos("Salud Domingo");
		alumno2.setContrasena("1234");
		alumno2.setDni("Alex");
		alumno2.setEmail("alexsalud@gmail.com");
		alumno2.setFechaDeNacimiento(c.getTime());
		alumno2.setNombre("Alexandra");
		
		alumno.setCurso(curso);
		alumno2.setCurso(curso);
		usuarioServices.save(alumno);
		usuarioServices.save(alumno2);
		
		cursoServices.addAlumno(curso, alumno);
		cursoServices.addAlumno(curso, alumno2);
	}

	private void populateCursos() {
		Calendar c = Calendar.getInstance();
		c.set(2019, Calendar.SEPTEMBER, 15);
		
		this.curso = Curso.builder()
					.fechaInicio(c.getTime())
					.linea("B")
					.nombre("4º de EGB")
					.vigente(true)
					.build();
		
		Clase clase1 = new Clase();
		clase1.setAsignatura(asignaturaDao.getOne(1l));
		clase1.setProfesor(profesorDao.getOne(1l));
		
		Clase clase2 = new Clase();
		clase2.setAsignatura(asignaturaDao.getOne(3l));
		clase2.setProfesor(profesorDao.getOne(1l));
		
		Clase clase3 = new Clase();
		clase3.setAsignatura(asignaturaDao.getOne(7l));
		clase3.setProfesor(profesorDao.getOne(2l));
		
		curso.addClase(clase1);
		curso.addClase(clase2);
		curso.addClase(clase3);
		
		cursoDao.save(this.curso);
	}

}
