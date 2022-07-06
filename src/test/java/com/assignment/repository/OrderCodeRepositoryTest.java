package com.assignment.repository;

import com.assignment.repository.entity.OrderCodeDetailsEntity;
import com.assignment.repository.entity.OrderCodeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link OrderCodeRepository}
 */
@ExtendWith(SpringExtension.class)
@Import(OrderCodeRepository.class)
@DataJpaTest
public class OrderCodeRepositoryTest {

    @Autowired
    private OrderCodeRepository orderCodeRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private OrderCodeEntity expected;

    @BeforeEach
    public void setUp() {
        OrderCodeDetailsEntity orderCodeDetailsEntity = OrderCodeDetailsEntity.builder().referenceToIsic("C").build();
        expected = OrderCodeEntity.builder().orderNumber(234).code("A").level(1)
                .description("come description").orderCodeDetailsEntity(orderCodeDetailsEntity).build();
        testEntityManager.persistAndFlush(expected);
    }

    @Test
    public void getOrderDetails() {
        OrderCodeEntity actual = orderCodeRepository.getOrderDetails(expected.getOrderNumber());
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getOrderDetailsFailure() {
        OrderCodeEntity actual = orderCodeRepository.getOrderDetails(1);
        assertThat(actual).isNull();
    }

    @Test
    public void putOrderDetails() {
        expected.setOrderNumber(444);
        orderCodeRepository.putOrderDetails(expected);
        int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "ORDER_CODE");
        assertThat(count).isEqualTo(1);
    }
}
