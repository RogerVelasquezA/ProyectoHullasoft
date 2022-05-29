package com.hullasoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hullasoft.models.dao.CapituloDao;
import com.hullasoft.models.entity.Capitulo;

@Service
public class CapituloServiceImp implements ICapituloService{

	@Autowired CapituloDao capituloDao;
	
	@Override
	public List<Capitulo> listarCapitulos() {
		
		return (List<Capitulo>) capituloDao.findAll();
	}

}
