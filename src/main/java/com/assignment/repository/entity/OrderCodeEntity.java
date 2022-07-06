package com.assignment.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static com.assignment.repository.entity.OrderCodeEntity.GET_NACE_DETAILS_BY;

/**
 * Entity representation of table ORDER_CODE.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "ORDER_CODE")
@NamedQueries({
        @NamedQuery(name = GET_NACE_DETAILS_BY, query = "select o FROM OrderCodeEntity o where o.orderNumber =: orderNumber")
})
public class OrderCodeEntity implements Serializable {

    private static final long serialVersionUID = -1777718722542586456L;
    public static final String GET_NACE_DETAILS_BY = "getNaceDetailsBy";
    @Id
    @Column(name = "ORDER_NUMBER", nullable = false)
    private Integer orderNumber;

    @Column(name = "LEVEL", nullable = false)
    private int level;
    @Column(name = "CODE", nullable = false, length = 5)
    private String code;
    @Column(name = "PARENT", length = 10)
    private String parent;
    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    private String description;

    @OneToOne(targetEntity = OrderCodeDetailsEntity.class, cascade = CascadeType.ALL)
    private OrderCodeDetailsEntity orderCodeDetailsEntity;
}
