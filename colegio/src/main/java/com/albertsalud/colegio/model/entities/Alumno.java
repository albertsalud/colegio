package com.albertsalud.colegio.model.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Alumno extends Usuario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date fechaDeNacimiento;
	
	@ManyToOne
	@JoinColumn(name="id_curso")
	private Curso curso;
	
	public Alumno() {
		super.setRol(Rol.ALUMNO);
	}
	
	@OneToMany(mappedBy = "alumno", orphanRemoval = true, fetch = FetchType.LAZY)
	public List<Evaluacion> evaluaciones;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + Rol.ALUMNO.name()));
	}

	@Override
	public String getPassword() {
		return this.getContrasena();
	}

	@Override
	public String getUsername() {
		return this.getDni();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
