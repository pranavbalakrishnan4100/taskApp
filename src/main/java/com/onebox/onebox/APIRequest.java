package com.onebox.onebox;

import java.io.Serializable;

public class APIRequest<T> {
	
	public String operation;
	public Long id;
	public ListSpec listSpec;
	public String entityName;
	public T entityObj;
	
	public APIRequest(String operation, Long id, ListSpec listSpec, T entityObj) {
		this.operation=operation;
		this.id=id;
		this.listSpec=listSpec;
		//this.entityName=entityName;
		this.entityObj=entityObj;
	}
}
