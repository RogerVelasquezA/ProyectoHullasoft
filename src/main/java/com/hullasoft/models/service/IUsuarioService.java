package com.hullasoft.models.service;


import java.util.List;

import com.hullasoft.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> listarUsuarios();
	
	public void eliminar(Usuario id);
	
	public void agregar(Usuario id);
	
	public Usuario buscarUsuario(Usuario usuario);
	
	
	public Usuario buscarUsuarioyContrasenia(Usuario email, Usuario contrasenia);
	
}
