package com.onebox.onebox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebox.onebox.repositories.TaskRepository;

@Service
public class TaskService extends BaseService{
	
	@Autowired
	public TaskService(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }
}
