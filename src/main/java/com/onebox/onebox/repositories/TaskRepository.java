package com.onebox.onebox.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onebox.entityobjects.Task;

@Repository
public interface TaskRepository extends BaseRepository<Task, Long>{
	
}
