package com.onebox.onebox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.entityobjects.Task;
import com.onebox.onebox.services.TaskService;

@RestController
@RequestMapping(path="/tasks")
@CrossOrigin(origins = "*")
public class TaskController extends BaseController<Task, Long>{

    @Autowired
    public TaskController(TaskService taskService) {
        this.service = taskService;
    }
}
