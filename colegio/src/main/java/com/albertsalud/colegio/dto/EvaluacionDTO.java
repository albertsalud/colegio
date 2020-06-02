package com.albertsalud.colegio.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionDTO {

	private String nombreAlumno;
	
	@NotNull
	private Long idAlumno;
	
	@NotNull
	private Long idClase;
	
	@Min(value = 0, message = "El valor no puede ser menor que 0.")
	@Max(value = 10, message = "El valor no puede ser mayor que 10.")
	private Float nota;
	
	
}
