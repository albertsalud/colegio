package com.albertsalud.colegio.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.albertsalud.colegio.model.entities.Asignatura;
import com.albertsalud.colegio.model.repositories.AsignaturaDAO;

@Service
public class AsignaturaServices {
	
	@Autowired
	private AsignaturaDAO asignaturaDao;

	public List<Asignatura> listAsignaturas() {
		return asignaturaDao.findAll(Sort.by("nombre"));
	}

	public Asignatura saveAsignatura(Asignatura asignatura) {
		return asignaturaDao.save(asignatura);
		
	}

	public Asignatura findById(long idAsignatura) {
		return asignaturaDao.findById(idAsignatura).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
	}

}
