package com.hullasoft.models.service;

import java.util.List;


import com.hullasoft.models.entity.Curso;

public interface ICursoService{

	public List<Curso> listarCursos();
	
	public void agregarCurso(Curso id);
	
	public void eliminar(Curso id);
	
	public Curso buscarCurso(Curso id);
}
