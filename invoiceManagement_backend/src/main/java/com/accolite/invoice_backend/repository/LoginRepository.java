package com.accolite.invoice_backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accolite.invoice_backend.entity.Login;
@Repository
public interface LoginRepository extends CrudRepository<Login, String>{

	
}


