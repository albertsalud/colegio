package com.albertsalud.colegio.model.entities;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Director extends Usuario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Director() {
		super.setRol(Rol.DIRECTOR);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + Rol.DIRECTOR.name()));
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
