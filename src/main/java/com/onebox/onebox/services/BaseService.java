package com.onebox.onebox.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onebox.onebox.ListSpec;
import com.onebox.onebox.repositories.BaseRepository;
import com.onebox.onebox.specifications.SearchSpecifications;
import com.onebox.onebox.transformers.ResponseTransformer;

@Service
public abstract class BaseService<T, ID extends Serializable>{
	
	protected BaseRepository<T, ID> repository;
	protected BaseRepository<T, ID> parentRepository;
	
	public ObjectEntityMapper mapper;
	
	public ResponseEntity<T> getAll(String listSpecs){
		ListSpec listSpec = null;
		List<T> resultList = new ArrayList<T>();
		
		try {
			listSpec = new ListSpec(new JSONObject(listSpecs));
		} catch (JSONException e) {
			e.printStackTrace();
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.BAD_REQUEST);
		}
		
		
    	Specification spec = SearchSpecifications.specification(listSpec.searchSpecs);
		
		if(listSpec.parentId!=null && parentRepository.existsById(listSpec.parentId)) {
			T parentObj=parentRepository.findById(listSpec.parentId).get();
			spec = spec.and(SearchSpecifications.withAdditionalCondition(listSpec.parentField, parentObj));
		}
		
		Sort sort=null;
		Pageable pageable;
		if(listSpec.sortOrder!=null) {
			if( listSpec.sortOrder.equals("ASC")) {
				sort = Sort.by(Sort.Order.asc(listSpec.sortField));
			}else {
				sort = Sort.by(Sort.Order.desc(listSpec.sortField));
			}
			pageable = PageRequest.of(listSpec.startIndex, listSpec.rowCount, sort); 
		}

		
		pageable = PageRequest.of(listSpec.startIndex, listSpec.rowCount); 
		
		resultList = repository.findAll(spec, pageable).toList();
		
		return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.OK);
	}
	
	public ResponseEntity<T> get(ID id){
    	Optional<T> entity=null;

    	List<Object> resultList=new ArrayList<Object>();
    	entity=repository.findById((Long) id);
    	if(entity.isPresent()) {
    		resultList.add(entity.get());
    	}
		
		return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.OK);
	}
	
	public ResponseEntity<T> post(T entity){
		List<Object> resultList=new ArrayList<Object>();
		try {
			resultList.add(repository.save(entity));
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.OK);
		}catch(Exception e) {
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}
	
	public ResponseEntity<T> delete(ID id){
		List<T> resultList=new ArrayList<T>();
		if(repository.findById((Long) id).get() != null) {
			repository.deleteById((Long) id);
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.OK);
		}else {
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@Transactional(readOnly=false)
	public ResponseEntity<T> delete(String listSpec){
		ListSpec listSpecs = null;
		List<Object> resultList=new ArrayList<Object>();
		
		try {
			listSpecs = new ListSpec(new JSONObject(listSpec));
		} catch (JSONException e) {
			e.printStackTrace();
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.BAD_REQUEST);
		}
		
		
		if(listSpecs!=null && listSpecs.searchSpecs!=null) {
			Specification spec = SearchSpecifications.specification(listSpecs.searchSpecs);
			resultList.add(new JSONObject().append("count", repository.delete(spec)));	
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.OK);
		}else {
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<T> put(ID id, T newEntity){
		List<T> resultList=new ArrayList<T>();
		try {
			T oldEntity=repository.getReferenceById((Long) id);
			//mapper.updateEntityFromDto(newEntity, oldEntity);
			resultList.add(repository.save(oldEntity));
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.OK);
		}catch(Exception e) {
			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.BAD_REQUEST);
		}
	}


}
