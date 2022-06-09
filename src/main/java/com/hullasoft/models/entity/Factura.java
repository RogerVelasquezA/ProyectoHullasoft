package com.hullasoft.models.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name = "facturas")
public class Factura {

	@Id
	@Column(name = "id_factura")
	private Integer id;
	
	
	private LocalDateTime fecha;
	@Column(name = "tip_pago")
	private String tipoPago;
	
	private Double monto;
	
	private Double montoTotal;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //1 factura - muchas itemfactura
	@JoinColumn(name = "id_factura") //llave foranea
	private List<ItemFactura> items;
	
	
	@ManyToOne(fetch = FetchType.LAZY) //muchas facturas - 1 cliente
	private Usuario usuario;

	@PrePersist //permite que se inicie antes que se llame el metodo persist
	public void prePersist() {
		this.fecha = LocalDateTime.now();
	}
	

	public Factura() {
		
		this.items = new ArrayList<ItemFactura>();
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	


	public LocalDateTime getFecha() {
		return fecha;
	}


	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	public String getTipoPago() {
		return tipoPago;
	}


	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}


	public Double getMonto() {
		return monto;
	}


	public void setMonto(Double monto) {
		this.monto = monto;
	}


	public Double getMontoTotal() {
		
		
		Double total = 0.0;
		int size = items.size();
		for(int i=0;i<size;i++) {
			total += items.get(i).calcularImporte();
		}
		return total;
		
		
	}


	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}


	public List<ItemFactura> getItems() {
		return items;
	}


	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	


	
	
	
	
	
}
