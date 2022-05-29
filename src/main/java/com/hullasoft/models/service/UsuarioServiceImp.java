package com.hullasoft.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hullasoft.models.dao.UsuarioDao;
import com.hullasoft.models.entity.Usuario;

@Service
public class UsuarioServiceImp implements IUsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listarUsuarios() {
		
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void eliminar(Usuario usuario) {
		usuarioDao.delete(usuario);
		
	}

	@Override
	@Transactional
	public void agregar(Usuario usuario) {
		usuarioDao.save(usuario);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarUsuario(Usuario usuario) {
		return usuarioDao.findById(usuario.getId()).orElse(null);
		
	}

}
