package com.assignment.mapper;

import com.assignment.domin.NaceDetail;
import com.assignment.repository.entity.OrderCodeDetailsEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class to map {@link NaceDetail} property to {@link com.assignment.repository.entity.OrderCodeDetailsEntity}.
 */
@Mapper(componentModel = "spring")
@Component
public interface OrderCodeDetailsMapper {
    OrderCodeDetailsEntity toEntity(NaceDetail source);
}
