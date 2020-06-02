package com.albertsalud.colegio.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.albertsalud.colegio.error.UserNotFoundException;
import com.albertsalud.colegio.model.entities.Usuario;
import com.albertsalud.colegio.model.repositories.UsuarioDAO;

@Primary
@Service
public class UsuarioServices implements UserDetailsService{
	
	@Autowired private UsuarioDAO usuarioDao;
	@Autowired private PasswordEncoder passwordEncoder;
	
	public Usuario login(String dni, String contrasena) {
		
		String encryptedPassword = passwordEncoder.encode(contrasena);
		return usuarioDao.findByDniAndContrasena(dni, encryptedPassword)
				.orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
	}

	public void save(Usuario usuario) {
		usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
		usuarioDao.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioDao.findByDni(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado"));
	}
}
