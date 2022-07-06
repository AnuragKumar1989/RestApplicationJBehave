package com.assignment.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Entity representation of table ORDER_CODE_DETAILS.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@Table(name = "ORDER_CODE_DETAILS")
public class OrderCodeDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "ITEM_INCLUDE")
    private String itemInclude;
    @Column(name = "ADDITIONAL_ITEM_INCLUDE")
    private String additionalItemInclude;
    @Column(name = "RULINGS", length = 100)
    private String rulings;
    @Column(name = "ITEM_EXCLUDE")
    private String itemExclude;
    @Column(name = "REFERENCE_TO_ISIC")
    private String referenceToIsic;

    @OneToOne(targetEntity = OrderCodeEntity.class, fetch = FetchType.EAGER)
    private OrderCodeEntity orderCodeEntity;

}
