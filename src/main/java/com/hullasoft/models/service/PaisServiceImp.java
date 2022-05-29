package com.hullasoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullasoft.models.dao.PaisDao;
import com.hullasoft.models.entity.Pais;

@Service
public class PaisServiceImp implements IPaisService{

	@Autowired private PaisDao paisDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pais> listarPaises() {
		
		return (List<Pais>)paisDao.findAll();
	}

}
