package com.hullasoft.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.hullasoft.models.entity.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer>{
	
	Usuario findByUsername(String username);
	
	
	

}
