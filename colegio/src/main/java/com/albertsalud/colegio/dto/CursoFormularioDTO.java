package com.albertsalud.colegio.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.albertsalud.colegio.model.entities.Clase;

import lombok.Data;

@Data
public class CursoFormularioDTO {
	
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String linea;
	
	private boolean vigente;
	
	private List<Clase> clases;
}
