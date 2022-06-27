package com.hullasoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullasoft.models.dao.FacturaDao;
import com.hullasoft.models.entity.Factura;


@Service
public class FacturaServiceImp implements IFacturaService{

	@Autowired FacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Factura> listarFacturas() {
		
		return (List<Factura>)facturaDao.findAll();
	}

	@Override
	@Transactional
	public void agregarFactura(Factura id) {
		
		facturaDao.save(id);
	}

	@Override
	@Transactional
	public void eliminarFactura(Factura id) {
		facturaDao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Factura buscarFactura(Factura factura) {
		
		return facturaDao.findById(factura.getId()).orElse(null);
	}
	
	

}
