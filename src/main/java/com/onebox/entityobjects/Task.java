package com.onebox.entityobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="task")
@Table(name="task")
public class Task extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
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
	
//	@JsonBackReference
//	@ManyToOne
//	@JoinColumn(name = "msp_id", referencedColumnName="id")
//	public MSP msp;
	
}
