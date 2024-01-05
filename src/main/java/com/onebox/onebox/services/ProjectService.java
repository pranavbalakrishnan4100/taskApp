package com.onebox.onebox.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onebox.entityobjects.Project;
import com.onebox.onebox.repositories.ProjectRepository;
import com.onebox.onebox.transformers.ResponseTransformer;

@Service
public class ProjectService<T> extends BaseService{
	
	@Autowired
	public ProjectService(ProjectRepository projectRepository) {
        this.repository = projectRepository;
//        ObjectEntityMapper mapper = ObjectEntityMapper.INSTANCE;
//        this.mapper=mapper;
     
    }
	
//	@Override
//	public ResponseEntity<T> post(Object entity){
//		Activity act=new Activity("test");
//		Project projectObj=(Project) entity;
//		projectObj.activities.add(act);
//		return super.post(projectObj);
////		List<Object> resultList=new ArrayList<Object>();
////		try {
////			resultList.add(repository.save(entity));
////			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.OK);
////		}catch(Exception e) {
////			return (ResponseEntity<T>) ResponseTransformer.transformToAPIResponse(resultList, HttpStatus.INTERNAL_SERVER_ERROR, e);
////		}
//	}
}
