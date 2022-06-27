package com.hullasoft.models.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_factura")
	private Integer id;
	
	
	private LocalDateTime fecha;
	
	@Column(name = "tip_pago")
	private String tipoPago;
		
	@Column(name = "montototal")
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
	
	
	
	public Factura(Double montoTotal, List<ItemFactura> items, Usuario usuario) {
		super();
		
		this.montoTotal = montoTotal;
		this.items = items;
		this.usuario = usuario;
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




	


	public Double getMontoTotal() {
		return montoTotal;
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
	
	
	public void addItemFactura(ItemFactura item) {
		this.items.add(item);
	}



	@Override
	public String toString() {
		return "Factura [id=" + id + ", fecha=" + fecha + ", tipoPago=" + tipoPago + ", montoTotal=" + montoTotal
				+ ", items=" + items + ", usuario=" + usuario + "]";
	}

	
	
	
	
	
}
