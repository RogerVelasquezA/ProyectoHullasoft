package com.hullasoft.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hullasoft.models.entity.Curso;
import com.hullasoft.models.entity.Docente;
import com.hullasoft.models.entity.Materia;
import com.hullasoft.models.service.ICursoService;
import com.hullasoft.models.service.IDocenteService;
import com.hullasoft.models.service.IMateriaService;



//@RequestMapping("/curso")
@Controller
///@SessionAttributes("curso")
public class CursoController {
	
	@Autowired
	private ICursoService cursoService;
	
	@Autowired
	private IMateriaService materiaService;
	
	@Autowired
	private IDocenteService docenteService;
	
	
	
	@GetMapping("/cursos")
	private String cursos(Model model) {
		
		Curso curso = new Curso();
		/*Materia materias=(Materia)materiaService.listarMateria();
		Docente docentes = (Docente)docenteService.listarDocentes();*/
		
		//model.addAttribute("docente", materiaService.listarMateria());
		//model.addAttribute("materia", docenteService.listarDocentes());
		

		model.addAttribute("cursos", cursoService.listarCursos());
		
		
		
		
		return "about";
	}
	
	@GetMapping("/cargarcurso")
	public String form(Model model) {
		
		
		
		model.addAttribute("lstmaterias",materiaService.listarMateria());
		model.addAttribute("lstdocentes", docenteService.listarDocentes());
		model.addAttribute("cursos", cursoService.listarCursos());
		model.addAttribute("curso", new Curso());
		
		return "formcurso";
	}
	
	
	@PostMapping("/grabarcurso")
	public String grabarPag(@Valid Curso curso, Errors errores, Model model) {
		
		
		model.addAttribute("lstmaterias",materiaService.listarMateria());
		model.addAttribute("lstdocentes", docenteService.listarDocentes());
		model.addAttribute("cursos", cursoService.listarCursos());
		
		if(errores.hasErrors()) {
			return "formcurso";
		}
		cursoService.agregarCurso(curso);

		return "redirect:/cursos";
		}
	
	
	@GetMapping("/editar/{id}")
	public String editar(@ModelAttribute Curso curso, Model model) {
		
		
		model.addAttribute("curso", cursoService.buscarCurso(curso));

		model.addAttribute("lstmaterias",materiaService.listarMateria());
		model.addAttribute("lstdocentes", docenteService.listarDocentes());
		model.addAttribute("cursos", cursoService.listarCursos());
		return "formcurso";
	}
	
	@GetMapping("/eliminarcurso/{id}")
	public String eliminar(@ModelAttribute Curso curso, Model model) {		
		
		cursoService.eliminar(curso);
		return "redirect:/cursos";
	}
	
	

}
