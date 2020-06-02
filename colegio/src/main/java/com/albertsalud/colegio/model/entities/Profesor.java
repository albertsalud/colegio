package com.albertsalud.colegio.model.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Profesor extends Usuario{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Profesor() {
		super.setRol(Rol.PROFESOR);
	}
	
	@Column(length = 10)
	private String telefonoContacto;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor")
	private List<Clase> clases;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + Rol.PROFESOR.name()));
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
