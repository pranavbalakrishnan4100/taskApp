package com.onebox.onebox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onebox.onebox.repositories.TaskActivityRepository;
import com.onebox.onebox.repositories.TaskRepository;

@Service
public class ActivityService extends BaseService{

	@Autowired
	public ActivityService(TaskActivityRepository activityRepository) {
        this.repository = activityRepository;
//        ObjectEntityMapper mapper = ObjectEntityMapper.INSTANCE;
//        this.mapper=mapper;
    }
}
