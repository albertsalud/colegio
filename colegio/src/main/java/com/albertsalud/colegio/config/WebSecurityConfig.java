package com.albertsalud.colegio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.albertsalud.colegio.model.entities.Rol;
import com.albertsalud.colegio.model.service.UsuarioServices;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UsuarioServices usuariosServices;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuariosServices).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/error").permitAll()
					.antMatchers("/director").hasRole(Rol.DIRECTOR.toString())
					.antMatchers("/alumno").hasRole(Rol.ALUMNO.toString())
					.antMatchers("/profesor").hasRole(Rol.PROFESOR.toString())
					.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/home", true)
					.permitAll();
		
		http.csrf().disable();
		
	}
	
	
	
}
