package com.onebox.entityobjects;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="task")
@Table(name="task")
public class Task extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name="subject")
	public String subject;
	
	@Column(name="status")
	public String status;
	
	@Column(name="priority")
	public String flag="normal";
	
	@Column(name="description")
	public String description;
	
	@Column(name="created_time")
	public Long createdTime;
	
	@Column(name="due_by")
	public Long dueBy;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "project", referencedColumnName="id")
	public Project project;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    public List<TaskActivity> taskActivity = new ArrayList<>();
	
//	@JsonManagedReference
//    @OneToMany(mappedBy = "subtasks", cascade = CascadeType.ALL)
//    private List<Task> subtastasks = new ArrayList<>();
}
