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

import com.onebox.onebox.APIRequest;
import com.onebox.onebox.ListSpec;
import com.onebox.onebox.services.BaseService;

public abstract class BaseController <T, ID extends Serializable>{
	
	public BaseService<T, ID> service;
	
	@GetMapping
	public ResponseEntity<T> getAll(@RequestParam(required=false) String listSpecs){
		ListSpec listSpec=new ListSpec();
		
		if(listSpecs!=null){
			try {
				listSpec=new ListSpec(new JSONObject(listSpecs));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return service.handleCRUD(new APIRequest("GETALL", null, listSpec, null));
		//return service.getAll(listSpecs);
	}
	
	@GetMapping (path="/{id}")
	public ResponseEntity<T> get(@PathVariable ID id) {
		return service.handleCRUD(new APIRequest<T>("GET", (Long) id, null, null));
		//return service.get(id);
	}
	
	@PostMapping
	public ResponseEntity<T> post(@RequestBody T entity) {
		return service.handleCRUD(new APIRequest<T>("POST", null, null, entity));
		//return service.post(entity);
	}
	
	@DeleteMapping (path="/{id}")
	public ResponseEntity<T> delete(@PathVariable ID id) {
		return service.handleCRUD(new APIRequest<T>("DELETE", (Long) id, null, null));
		//return service.delete(id);
	}
	
	@DeleteMapping
	public ResponseEntity<T> delete(@RequestParam String listSpecs) {
		try {
			return service.handleCRUD(new APIRequest<T>("DELETE", null, new ListSpec(new JSONObject(listSpecs)), null));
		} catch (Exception e) {
			return null;
		}
		//return service.delete(listSpecs);
	}
	
	@PutMapping (path="/{id}")
	public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
		return service.handleCRUD(new APIRequest<T>("PUT", (Long) id, null, entity));
		//return service.put(id, entity);
	}
}
