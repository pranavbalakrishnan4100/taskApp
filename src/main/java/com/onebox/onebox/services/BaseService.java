package com.onebox.onebox.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onebox.onebox.ListSpec;
import com.onebox.onebox.repositories.BaseRepository;
import com.onebox.onebox.specifications.SearchSpecifications;
import com.onebox.onebox.transformers.ResponseTransformer;

@Service
public abstract class BaseService<T, ID extends Serializable> {
	
	protected BaseRepository<T, ID> repository;
	protected BaseRepository<T, ID> parentRepository;
	
	public ResponseEntity<T> getAll(ListSpec listSpec){
		List<T> resultList;
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
		
		resultList = repository.findAll();
		
		return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.OK);
	}
	
//	private ResponseEntity<T> get(ID id){
//	}

}
