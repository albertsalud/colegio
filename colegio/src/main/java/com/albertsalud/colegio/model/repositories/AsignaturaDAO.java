package com.albertsalud.colegio.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.colegio.model.entities.Asignatura;

public interface AsignaturaDAO extends JpaRepository<Asignatura, Long>{

}
