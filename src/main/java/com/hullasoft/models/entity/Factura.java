package com.hullasoft.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facturas")
public class Factura {

	@Id
	@Column(name = "id_factura")
	private Integer id;
	private Date fecha;
	@Column(name = "tip_pago")
	private String tipoPago;
	
	
	
	
	
}
