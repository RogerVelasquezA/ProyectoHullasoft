package com.hullasoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hullasoft.models.entity.Pais;
import com.hullasoft.models.entity.Usuario;
import com.hullasoft.models.service.IPaisService;
import com.hullasoft.models.service.IUsuarioService;



@Controller
public class UsuarioController {

	@Autowired(required = true)
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPaisService paisService;

	@GetMapping("/")
	public String ver() {

		return "index";
	}

	@GetMapping("/agregar")
	public String agregar( Model model) {
		
		model.addAttribute("paises", paisService.listarPaises());
		model.addAttribute("usuarios", usuarioService.listarUsuarios());
		model.addAttribute("usuario", new Usuario());
		return "signup";
	}
	
	@GetMapping("/login")
	public String login() {
		return "signin";
	}

	@PostMapping("/grabar")
	public String grabar(Usuario usuario, Model model) {

		model.addAttribute("usuarios", usuarioService.listarUsuarios());
		model.addAttribute("usuario", usuario);
		usuarioService.agregar(usuario);

		return "redirect:/agregar";
	}

}
