package com.hullasoft.models.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import util.EncriptarPassword;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;
	
	@NotBlank(message = "El Username no puede estar Vacio")
	@Size(max = 30)
	@Column(unique = true)
	private String username;
	
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
	private LocalDateTime fecInscripcion;
	
	
	private String estado;
	
	@NotBlank(message = "La Contraseña no puede estar Vacia")
	private String password;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pais")
	@NotNull(message = "Debe de seleccionar un Pais")
	private Pais paises;
	
	
	@OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY, cascade = CascadeType.ALL) //1 cliente - muchas facturas
	private List<Factura> facturas;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_usuario")
    private List<Rol> roles;
	
	@PrePersist 
	public void prePersist() {
		this.fecInscripcion = LocalDateTime.now();
	}
	
	public Usuario() {
		this.roles = new ArrayList<Rol>();
	}


	
	
	


	

	public Usuario(Integer id, @NotBlank(message = "El Username no puede estar Vacio") @Size(max = 30) String username,
			@NotBlank(message = "El Nombre no puede estar Vacio") @Size(max = 30) String nombres,
			@NotBlank(message = "El Apellido no puede estar Vacio") @Size(max = 30) String apellidos,
			@NotBlank(message = "El DNI no puede estar Vacio") @Size(min = 8, max = 8) String dni,
			@NotBlank(message = "El Correo no puede estar Vacio") String email, LocalDateTime fecInscripcion,
			String estado, @NotBlank(message = "La Contraseña no puede estar Vacia") String password,
			@NotNull(message = "Debe de seleccionar un Pais") Pais paises, List<Factura> facturas, List<Rol> roles) {
		super();
		this.id = id;
		this.username = username;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.fecInscripcion = fecInscripcion;
		this.estado = estado;
		this.password = password;
		this.paises = paises;
		this.facturas = facturas;
		this.roles = roles;
	}

	public void addRol(Rol rol) {
		this.roles.add(rol);
	}
	
	
	
	
	
	public LocalDateTime getFecInscripcion() {
		return fecInscripcion;
	}

	public void setFecInscripcion(LocalDateTime fecInscripcion) {
		this.fecInscripcion = fecInscripcion;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	public List<Rol> getRoles() {
				
		return roles;
	}
	
	
	
	
	public void setRoles(List<Rol> roles) {
		
		
		this.roles = roles;
	}
	
	
	
	
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
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	

	
	
	public void setPassword(String password) {
				
		EncriptarPassword encriptar = new EncriptarPassword();
				
		String pass = encriptar.encriptarPassword(password);
		
		this.password = pass;
	}
	
	
	
	
	

}
