package com.hullasoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullasoft.models.dao.MateriaDao;
import com.hullasoft.models.entity.Materia;

@Service
public class MateriaServiceImp implements IMateriaService{

	@Autowired private MateriaDao materiaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Materia> listarMateria() {
		return (List<Materia>) materiaDao.findAll();
	}

}
