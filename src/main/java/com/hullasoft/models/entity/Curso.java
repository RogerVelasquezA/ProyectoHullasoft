package com.hullasoft.models.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@Column(name = "id_curso")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El Nombre no puede estar Vacio")
	@Size(max = 30)
	private String nombre;
	
	@Column(name = "fec_subida")
	private LocalDateTime fechaSubida;
	
	@Column(name = "fec_actualizacion")
	private LocalDateTime FechaActualizacion;
	
	@NotBlank(message = "La descripcion no puede estar Vacia")
	@Size(max = 500)
	private String descripcion;
	
	
	@NotNull(message = "este campo no puede estar vacio")
	private Double precio;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "id_materia")
	@NotNull(message = "Debe seleccionar una materia")
	private Materia materias;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_docente")
	@NotNull(message = "Debe seleccionar una Docente")
	private Docente docentes;
	
	@OneToMany(mappedBy = "cursos",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Capitulo> capitulos;
	
	
	
	@PrePersist //permite que se inicie antes que se llame el metodo persist
	public void prePersist() {
		this.fechaSubida = LocalDateTime.now();
	}
	
	@PreUpdate //permite que se inicie antes que se llame el metodo persist
	public void preUpdate() {
		this.FechaActualizacion = LocalDateTime.now();
	}
	
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Docente getDocentes() {
		return docentes;
	}

	public void setDocentes(Docente docentes) {
		this.docentes = docentes;
	}

	public Materia getMaterias() {
		return materias;
	}

	public void setMaterias(Materia materias) {
		this.materias = materias;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

	

	public LocalDateTime getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(LocalDateTime fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public LocalDateTime getFechaActualizacion() {
		return FechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		FechaActualizacion = fechaActualizacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", fechaSubida=" + fechaSubida + ", FechaActualizacion="
				+ FechaActualizacion + ", descripcion=" + descripcion + ", precio=" + precio + ", materias=" + materias
				+ ", docentes=" + docentes + ", capitulos=" + capitulos + "]";
	}
	
	

}
