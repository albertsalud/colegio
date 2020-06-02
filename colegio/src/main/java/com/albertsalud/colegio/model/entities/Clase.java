package com.albertsalud.colegio.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Clase {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_asignatura")
	private Asignatura asignatura;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_curso")
	private Curso curso;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_profesor")
	private Profesor profesor;

	@OneToMany(mappedBy = "clase", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Evaluacion> evaluaciones;
}
