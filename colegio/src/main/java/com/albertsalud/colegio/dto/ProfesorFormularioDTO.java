package com.albertsalud.colegio.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ProfesorFormularioDTO {
	
	private Long id;
	
	@NotBlank(message = "El nombre debe estar cumplimentado")
	private String nombre;
	
	@NotBlank(message = "El apellido debe estar cumplimentado")
	private String apellidos;
	
	@NotBlank(message = "El teléfono de contacto debe estar cumplimentado")
	private String telefonoContacto;
	
	@NotBlank(message = "El DNI debe estar cumplimentado")
	private String dni;
	
	@Email(message = "Formato no válido")
	private String email;
	
	@NotBlank(message = "La contraseña debe estar cumplimentada")
	private String contrasena;
	
	@NotBlank(message = "Este campo debe coincidir con el campo \"Contraseña\"")
	private String contrasena2;
	

}
