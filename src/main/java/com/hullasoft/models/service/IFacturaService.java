package com.hullasoft.models.service;

import java.util.List;

import com.hullasoft.models.entity.Factura;

public interface IFacturaService {
	
	public List<Factura> listarFacturas();
	
	public void agregarFactura(Factura id);
	
	public void eliminarFactura(Factura id);
	
	public Factura buscarFactura(Factura factura);

}
