package com.onebox.onebox.services;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import com.onebox.entityobjects.BaseEntity;

@Mapper(componentModel="Spring")
public interface ObjectEntityMapper {
	
	ObjectEntityMapper INSTANCE = Mappers.getMapper(ObjectEntityMapper.class);

	void updateEntityFromDto(BaseEntity taskDto, @MappingTarget BaseEntity task);

}
