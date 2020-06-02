package com.albertsalud.colegio.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.colegio.model.entities.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByDni(String dni);
	
	public Optional<Usuario> findByDniAndContrasena(String dni, String contrasena);

}
