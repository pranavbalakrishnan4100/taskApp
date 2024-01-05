package com.onebox.onebox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onebox.entityobjects.Task;
import com.onebox.entityobjects.TaskActivity;
import com.onebox.onebox.APIRequest;
import com.onebox.onebox.repositories.TaskActivityRepository;
import com.onebox.onebox.repositories.TaskRepository;

@Service
public class TaskService<T> extends BaseService{
	
	@Autowired
	public TaskService(TaskRepository taskRepository) {
        this.repository = taskRepository;
//      ObjectEntityMapper mapper = ObjectEntityMapper.INSTANCE;
//      this.mapper=mapper;
    }
	
    @Override
    public ResponseEntity<T> handleCRUD(APIRequest APIReq){
    	if(!APIReq.operation.equals("GET") && !APIReq.operation.equals("GETALL") && !APIReq.operation.equals("DELETE")) {
    		Task taskObj;
    		if(APIReq.operation.equals("PUT")) {
        		taskObj=(Task) repository.findById(APIReq.id).get();
        	}
    		taskObj=(Task) APIReq.entityObj;
        	TaskActivity act=new TaskActivity(taskObj, APIReq.operation, null, null, null);
        	taskObj.taskActivity.add(act);
        	APIReq.entityObj=taskObj;
    	}

    	return super.handleCRUD(APIReq);
    }
	
}
