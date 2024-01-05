package com.onebox.onebox.services;

import com.onebox.entityobjects.BaseEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T21:48:03+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Private Build)"
)
@Component
public class ObjectEntityMapperImpl implements ObjectEntityMapper {

    @Override
    public void updateEntityFromDto(BaseEntity taskDto, BaseEntity task) {
        if ( taskDto == null ) {
            return;
        }
    }
}
