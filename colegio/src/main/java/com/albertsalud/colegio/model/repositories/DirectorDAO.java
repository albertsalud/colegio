package com.albertsalud.colegio.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.albertsalud.colegio.model.entities.Director;

public interface DirectorDAO extends JpaRepository<Director, Long>{

}
