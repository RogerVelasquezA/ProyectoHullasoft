package com.hullasoft.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullasoft.models.dao.CursoDao;
import com.hullasoft.models.entity.Curso;

@Service
public class CursoServiceImp implements ICursoService {

	@Autowired private CursoDao cursoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Curso> listarCursos() {
		// TODO Auto-generated method stub
		return (List<Curso>) cursoDao.findAll();
	}

	@Override
	@Transactional
	public void agregarCurso(Curso id) {
		cursoDao.save(id);
	}

	@Override
	@Transactional
	public void eliminar(Curso id) {
		// TODO Auto-generated method stub
		cursoDao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Curso buscarCurso(Curso id) {
		// TODO Auto-generated method stub
		return cursoDao.findById(id.getId()).orElse(null);
	}


}
