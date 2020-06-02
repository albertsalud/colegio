package com.albertsalud.colegio.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@NoArgsConstructor
public class Asignatura {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "El nombre no puede estar en blanco.")
	@Size(min = 3, max = 30, message = "La longitud de campo debe ser entre 3 y 30 caracteres")
	@Column(nullable = false, length=30, unique = true)
	private String nombre;
	
	public Asignatura(String nombre) {
		this.nombre = nombre;
	}

}
