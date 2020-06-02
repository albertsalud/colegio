package com.albertsalud.colegio.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionFormularioDTO {
	
	@NotNull
	private Long curso;
	
	@NotNull
	private Long asignatura;
	
	@NotEmpty
	@Valid
	private List<EvaluacionDTO> evaluaciones;

}
