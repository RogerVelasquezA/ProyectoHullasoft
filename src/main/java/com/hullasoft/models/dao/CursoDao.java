package com.hullasoft.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hullasoft.models.entity.Curso;

public interface CursoDao extends CrudRepository<Curso,Integer> {
	
	Curso findFirstById(Integer id);
	
	/*
	select  u.apellidos, f.id_factura, c.nombre
	from usuarios u join facturas f
	on id_usuario = usuario_id_usuario join det_factura d 
	on f.id_factura = d.id_factura join cursos c
	on d.id_curso = c.id_curso where u.id_usuario=1
	
	@Query("SELECT FROM usuario u join factura f on ")*/
	Curso  findCursoById(Integer id);
	
	

}
