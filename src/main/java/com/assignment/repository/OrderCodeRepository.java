package com.assignment.repository;

import com.assignment.repository.entity.OrderCodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Repository class toperform CRUD operation for {@link OrderCodeEntity}
 */
@Repository
@Slf4j
@Transactional(propagation = Propagation.REQUIRED)
public class OrderCodeRepository {
    private final EntityManager entityManager;

    public OrderCodeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public OrderCodeEntity getOrderDetails(Integer orderNumber) {
        try {
            return entityManager.createNamedQuery(OrderCodeEntity.GET_NACE_DETAILS_BY, OrderCodeEntity.class)
                    .setParameter("orderNumber", orderNumber).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    public void putOrderDetails(OrderCodeEntity orderCodeEntity) {
        entityManager.persist(orderCodeEntity);
    }
}
