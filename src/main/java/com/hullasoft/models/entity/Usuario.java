package com.hullasoft.models.entity;

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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El Nombre no puede estar Vacio")
	@Size(max = 30)
	private String nombres;
	
	@NotBlank(message = "El Apellido no puede estar Vacio")
	@Size(max = 30)
	private String apellidos;
	
	@NotBlank(message = "El DNI no puede estar Vacio")
	@Size(min = 8, max = 8)
	private String dni;
	
	@NotBlank(message = "El Correo no puede estar Vacio")
	private String email;
	
	
	
	@Column(name = "fec_inscrip")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String fecInscripcion;
	
	
	private String estado;
	
	@NotBlank(message = "La Contraseña no puede estar Vacia")
	private String contrasenia;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pais")
	@NotNull(message = "Debe de seleccionar un Pais")
	private Pais paises;
	
	
	
	public Pais getPaises() {
		return paises;
	}
	public void setPaises(Pais paises) {
		this.paises = paises;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFecInscripcion() {
		return fecInscripcion;
	}
	public void setFecInscripcion(String fecInscripcion) {
		this.fecInscripcion = fecInscripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	

}
