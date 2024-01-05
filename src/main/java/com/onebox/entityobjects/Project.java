package com.onebox.entityobjects;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="project")
@Table(name="project")
public class Project extends BaseEntity{
	
	public static boolean LOG_ACTIVITY=true;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name="title")
	public String title;
	
	@Column(name="description")
	public String description;
	
	@Column(name="created_time")
	public Long createdTime;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public List<Task> tasks = new ArrayList<>();
	
//	@JsonManagedReference
//    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
//    public List<Activity> activities = new ArrayList<>();
}
