package com.onebox.onebox.controllers;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.onebox.onebox.ListSpec;
import com.onebox.onebox.services.BaseService;

public abstract class BaseController <T, ID extends Serializable>{
	
	public BaseService<T, ID> service;
	
	@GetMapping
	public ResponseEntity<T> getAll(ListSpec listSpecs){
		return service.getAll(listSpecs);
	}
	
	@GetMapping (path="/{id}")
	public ResponseEntity<T> get(@PathVariable ID id) {
		return null;
	}
	
	@PostMapping
	public ResponseEntity<T> post() {
		return null;
	}
	
	@DeleteMapping (path="/{id}")
	public ResponseEntity<T> delete(@PathVariable ID id) {
		return null;
	}
	
	@PutMapping (path="{id}")
	public ResponseEntity<T> update(@PathVariable ID id) {
		return null;
	}
}
