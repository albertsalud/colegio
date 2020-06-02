package com.albertsalud.colegio.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.colegio.model.entities.Profesor;

public interface ProfesorDAO extends JpaRepository<Profesor, Long>{

}
