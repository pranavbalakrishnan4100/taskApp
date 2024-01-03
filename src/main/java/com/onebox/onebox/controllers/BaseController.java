package com.onebox.onebox.controllers;

import java.io.Serializable;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.onebox.ListSpec;
import com.onebox.onebox.services.BaseService;

public abstract class BaseController <T, ID extends Serializable>{
	
	public BaseService<T, ID> service;
	
	@GetMapping
	public ResponseEntity<T> getAll(@RequestParam(required=false) String listSpecs){
		return service.getAll(listSpecs);
	}
	
	@GetMapping (path="/{id}")
	public ResponseEntity<T> get(@PathVariable ID id) {
		return service.get(id);
	}
	
	@PostMapping
	public ResponseEntity<T> post(@RequestBody T entity) {
		return service.post(entity);
	}
	
	@DeleteMapping (path="/{id}")
	public ResponseEntity<T> delete(@PathVariable ID id) {
		return service.delete(id);
	}
	
	@DeleteMapping
	public ResponseEntity<T> delete(@RequestParam String listSpecs) {
		return service.delete(listSpecs);
	}
	
	@PutMapping (path="/{id}")
	public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
		return service.put(id, entity);
	}
}
