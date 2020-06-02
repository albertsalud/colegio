package com.albertsalud.colegio.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.albertsalud.colegio.model.entities.Director;
import com.albertsalud.colegio.model.entities.Usuario;
import com.albertsalud.colegio.model.repositories.DirectorDAO;
import com.albertsalud.colegio.model.service.UsuarioServices;

import lombok.extern.java.Log;

@Log
@SpringBootTest
public class UsuarioServicesTest {
	
	@Autowired
	private UsuarioServices services;
	
	
	@Test
	public void loginTest(){
		String dni = "";
		String contrasena = "";
			
		Usuario usuario = services.login(dni, contrasena);
		Assertions.assertNotNull(usuario);
	}
	
	@Test
	public void saveLogin() {
		Director director = new Director();
		director.setApellidos("Salud García");
		director.setContrasena("albertsalud2009");
		director.setDni("52914213E");
		director.setEmail("albertsalud@gmail.com");
		director.setNombre("Albert");
		
		log.info(director.toString());
		
		services.save(director);
	}

}
