package com.hullasoft.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "det_factura")
public class ItemFactura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_det")
	private Long id;
	

	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso")
	private Curso curso;

	

	
	public ItemFactura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemFactura(Curso curso) {
		super();
		this.curso = curso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	
}
