package com.hullasoft.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.hullasoft.models.entity.Docente;

public interface DocenteDao extends PagingAndSortingRepository<Docente, Integer>{
	


}
