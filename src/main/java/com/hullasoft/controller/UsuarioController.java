package com.hullasoft.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hullasoft.models.dao.UsuarioDao;
import com.hullasoft.models.entity.Curso;
import com.hullasoft.models.entity.Pais;
import com.hullasoft.models.entity.Rol;
import com.hullasoft.models.entity.Usuario;
import com.hullasoft.models.service.ICursoService;
import com.hullasoft.models.service.IPaisService;
import com.hullasoft.models.service.IUsuarioService;



@Controller
public class UsuarioController {

	@Autowired(required = true)
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPaisService paisService;
	
	@Autowired private UsuarioDao usuarioDAO;
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/inicio")
	public String ver(Model model) {

		
		model.addAttribute("paises", paisService.listarPaises());
		
		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregar( Model model) {
		
		
		model.addAttribute("paises", paisService.listarPaises());
		model.addAttribute("usuarios", usuarioService.listarUsuarios());
		model.addAttribute("usuario", new Usuario());
		
		
		
		return "signup";
	}
	
	
	@GetMapping("/listarusuarios")
	public String listar( Model model) {
		
		
		
		model.addAttribute("paises", paisService.listarPaises());
		model.addAttribute("usuarios", usuarioService.listarUsuarios());
		//model.addAttribute("usuario", new Usuario());
		return "listausuarios";
	}
	
	
	@GetMapping("/perfil")
	public String perfil(@AuthenticationPrincipal User u, Model model) {
		
		Usuario user=usuarioDAO.findByUsername(u.getUsername());
		
		model.addAttribute("paises", paisService.listarPaises());
		model.addAttribute("usuario", user);

		return "perfil";
	}
	


	@PostMapping("/grabar")
	public String grabar(@Valid Usuario usuario,Errors errores, Model model) {

		model.addAttribute("usuarios", usuarioService.listarUsuarios());
		model.addAttribute("paises", paisService.listarPaises());
		//model.addAttribute("usuario", usuario);
		if(errores.hasErrors()) {
			return "signup";
		}
		
		//re.addFlashAttribute("mensaje", "Libro registrado con exito");
		
		//model.addAttribute("mensaje", "Registro exitoso");
	    
		Rol rol = new Rol();
		rol.setNombre("ROLE_USER");
		
		usuario.addRol(rol);
		
		Usuario usuario1=usuario;
		
		//log.info("ID: " + rol.toString());
		
		//System.out.println(rol);
		usuarioService.agregar(usuario1);

		return "redirect:/";
	}
	
	
	
	
	@GetMapping("/editarusuario/{id}")
	public String editar(@ModelAttribute Usuario usuario, Model model) {
		
		model.addAttribute("usuario", usuarioService.buscarUsuario(usuario));
		
		model.addAttribute("paises", paisService.listarPaises());
		model.addAttribute("lstpaises",paisService.listarPaises());
		model.addAttribute("usuarios", usuarioService.listarUsuarios());
		return "signup";
	}
	
	
	@GetMapping("/eliminarusuario/{id}")
	public String eliminar(@ModelAttribute Usuario usuario, Model model) {		
		
		usuarioService.eliminar(usuario);
		return "redirect:/listarusuarios";
	}

}
