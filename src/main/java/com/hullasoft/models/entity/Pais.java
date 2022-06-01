package com.hullasoft.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="paises")
public class Pais {
	
	@Id
	@Column(name = "id_pais")
	private Integer id;
	@Column(name = "nom_pais")
	private String nombre;
	
	@OneToMany(mappedBy = "paises",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Usuario> usuarios;
	
	
	
	public List<Usuario> getLibros() {
		return usuarios;
	}
	public void setLibros(List<Usuario> libros) {
		this.usuarios = libros;
	}
	public Pais() {

	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
