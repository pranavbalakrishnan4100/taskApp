package com.onebox.onebox.utilities;

import java.text.MessageFormat;

import com.onebox.entityobjects.TaskActivity;

public class TaskActivityUtil {
	
	public static final String TASK_CREATION="New task {0} was created.";
	public static final String TASK_UPDATION="Field {0} was updated from {1} to {2}.";

	public static String getDescription(TaskActivity taskActivity) {
		//get based on the activity performed
		if(taskActivity.operation.equals("POST")) {
			return MessageFormat.format(TASK_CREATION, taskActivity.task.subject);
		}else if(taskActivity.operation.equals("PUT")){
			return MessageFormat.format(TASK_UPDATION, taskActivity.field, taskActivity.oldValue, taskActivity.newValue);
		}

		return " ";
	}
}
