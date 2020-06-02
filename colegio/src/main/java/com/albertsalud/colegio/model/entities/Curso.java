package com.albertsalud.colegio.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String nombre;	// por ejemplo, 3º de EGB, 1º ESO
	
	@Column(nullable = false, length = 5)
	private String linea;	// por ejemplo, A, B (2º B, 3º A, ...)
	
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	private boolean vigente;
	
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@OrderBy("asignatura")
	private List<Clase> clases;
	
	public void addClase(Clase clase) {
		if(clases == null) clases = new ArrayList<>();
		
		clases.add(clase);
		clase.setCurso(this);
	}
	

}
