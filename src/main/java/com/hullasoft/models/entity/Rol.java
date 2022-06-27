package com.hullasoft.models.entity;

import java.io.Serializable;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="rol")
public class Rol/* implements Serializable*/{
    //private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    
    @NotEmpty
    private String nombre;

    
    
    
    
	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
    
    
    
}