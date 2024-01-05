package com.onebox.entityobjects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.onebox.onebox.utilities.TaskActivityUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

@Entity(name="task_activity")
@Table(name="task_activity")
@SecondaryTable(name = "task_activity_desc", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class TaskActivity {
	
	public TaskActivity() {}
	
	public TaskActivity(Task task, String operation, String field, String newValue, String oldValue) {
		this.task=task;
		this.operation=operation;
		this.field=field;
		this.newValue=newValue;
		this.oldValue=oldValue;
		this.time=System.currentTimeMillis();
		
		String description=TaskActivityUtil.getDescription(this);
		this.description=description;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "task", referencedColumnName="id")
	public Task task;
	
	@Column(name="operation")
	public String operation;

	@Column(name="field")
	public String field;
	
	@Column(name="new_value")
	public String newValue;
	
	@Column(name="old_value")
	public String oldValue;
	
	@Column(name="description", table="task_activity_desc")
	public String description;
	
	@Column(name="time")
	public Long time;
	
}
