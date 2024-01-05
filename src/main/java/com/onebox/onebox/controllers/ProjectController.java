package com.onebox.onebox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.entityobjects.Project;
import com.onebox.onebox.services.ProjectService;

@RestController
@RequestMapping(path="/projects")
@CrossOrigin(origins = "*")
public class ProjectController extends BaseController<Project, Long>{
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.service = projectService;
    }
    
    
   
}
