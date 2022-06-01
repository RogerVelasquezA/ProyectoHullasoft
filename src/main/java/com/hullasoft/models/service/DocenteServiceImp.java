package com.hullasoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullasoft.models.dao.DocenteDao;
import com.hullasoft.models.entity.Curso;
import com.hullasoft.models.entity.Docente;
import com.hullasoft.models.entity.Usuario;

@Service
public class DocenteServiceImp implements IDocenteService{

	@Autowired private DocenteDao docenteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Docente> listarDocentes() {		
		return (List<Docente>) docenteDao.findAll();
	}

}
