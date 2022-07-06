package com.assignment.mapper;

import com.assignment.domin.NaceDetail;
import com.assignment.repository.entity.OrderCodeEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper class to map {@link NaceDetail} property to {@link OrderCodeEntity}.
 */
@Mapper(componentModel = "spring")
@Component
public interface OrderCodeMapper {
    OrderCodeEntity toEntity(NaceDetail source);
}
