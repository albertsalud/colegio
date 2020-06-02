package com.albertsalud.colegio.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable, UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(nullable = false, length = 30)
	protected String nombre;
	
	@Column(nullable = false, length = 50)
	protected String apellidos;
	
	@Column(nullable = false, unique = true, length = 15)
	protected String dni;
	
	@Column(nullable = false, length = 100)
	protected String contrasena;
	
	protected String imagen;
	
	@Column(length = 100, unique=true)
	protected String email;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	@Setter(value = AccessLevel.PROTECTED)
	private Rol rol;
	
}
