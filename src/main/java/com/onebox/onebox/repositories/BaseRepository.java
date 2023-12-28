package com.onebox.onebox.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean 
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>{
	//public Optional<T> findByParentAndId(T parent, ID id); 
	@Modifying
	public long delete(Specification<T> spec);
}
