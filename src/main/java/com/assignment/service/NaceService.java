package com.assignment.service;

import com.assignment.domin.NaceDetail;
import com.assignment.mapper.OrderCodeDetailsMapper;
import com.assignment.mapper.OrderCodeMapper;
import com.assignment.repository.OrderCodeRepository;
import com.assignment.repository.entity.OrderCodeDetailsEntity;
import com.assignment.repository.entity.OrderCodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service class to perform various activities for NACE.
 */
@Service
@Slf4j
public class NaceService {
    public final OrderCodeRepository orderCodeRepository;
    public final OrderCodeMapper orderCodeMapper;
    public final OrderCodeDetailsMapper orderCodeDetailsMapper;

    public NaceService(OrderCodeRepository orderCodeRepository, OrderCodeMapper orderCodeMapper,
                       OrderCodeDetailsMapper orderCodeDetailsMapper) {
        this.orderCodeRepository = orderCodeRepository;
        this.orderCodeMapper = orderCodeMapper;
        this.orderCodeDetailsMapper = orderCodeDetailsMapper;
    }

    public OrderCodeEntity getDetails(Integer orderNumber) {
        return orderCodeRepository.getOrderDetails(orderNumber);
    }

    public void putDetails(NaceDetail naceDetail) {
        if (Objects.isNull(naceDetail)) {
            return;
        }
        OrderCodeDetailsEntity orderCodeDetailsEntity = orderCodeDetailsMapper.toEntity(naceDetail);
        OrderCodeEntity orderCodeEntity = orderCodeMapper.toEntity(naceDetail);
        orderCodeEntity.setOrderCodeDetailsEntity(orderCodeDetailsEntity);
        orderCodeRepository.putOrderDetails(orderCodeEntity);
    }
}
