package com.assignment.service;

import com.assignment.domin.NaceDetail;
import com.assignment.mapper.OrderCodeDetailsMapper;
import com.assignment.mapper.OrderCodeMapper;
import com.assignment.repository.OrderCodeRepository;
import com.assignment.repository.entity.OrderCodeDetailsEntity;
import com.assignment.repository.entity.OrderCodeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Unit test for {@link NaceService}
 */
public class NaceServiceTest {

    @Mock
    public OrderCodeRepository orderCodeRepository;
    @Mock
    public OrderCodeMapper orderCodeMapper;
    @Mock
    public OrderCodeDetailsMapper orderCodeDetailsMapper;

    @InjectMocks
    public NaceService serviceUnderTest;
    @Captor
    private ArgumentCaptor<OrderCodeEntity> capture;
    private OrderCodeEntity expectedEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        expectedEntity = OrderCodeEntity.builder().code("A").level(1).description("AGRICULTURE, FORESTRY AND FISHING").build();
        when(orderCodeRepository.getOrderDetails(ArgumentMatchers.anyInt())).thenReturn(expectedEntity);
    }

    @Test
    public void getDetailsSuccess() {
        OrderCodeEntity actual = serviceUnderTest.getDetails(123);
        verify(orderCodeRepository).getOrderDetails(anyInt());
        assertThat(actual).isEqualTo(expectedEntity);
    }

    @Test
    public void getDetailsFailure() {
        OrderCodeEntity actual = serviceUnderTest.getDetails(null);
        verify(orderCodeRepository, never()).getOrderDetails(anyInt());
        assertThat(actual).isNull();
    }

    @Test
    public void putDetailsFailure() {
        serviceUnderTest.putDetails(null);
        verify(orderCodeRepository, never()).putOrderDetails(any(OrderCodeEntity.class));
        verify(orderCodeMapper, never()).toEntity(any(NaceDetail.class));
        verify(orderCodeDetailsMapper, never()).toEntity(any(NaceDetail.class));
    }

    @Test
    public void putDetailsSuccess() {
        NaceDetail naceDetail = new NaceDetail();
        naceDetail.setOrderNumber(851684);
        when(orderCodeDetailsMapper.toEntity(any(NaceDetail.class))).thenReturn(new OrderCodeDetailsEntity());
        when(orderCodeMapper.toEntity(any(NaceDetail.class))).thenReturn(OrderCodeEntity.builder()
                .orderNumber(naceDetail.getOrderNumber()).build());
        serviceUnderTest.putDetails(naceDetail);
        verify(orderCodeMapper).toEntity(any(NaceDetail.class));
        verify(orderCodeDetailsMapper).toEntity(any(NaceDetail.class));
        verify(orderCodeRepository).putOrderDetails(capture.capture());
        OrderCodeEntity actual = capture.getValue();
        assertThat(actual).isNotNull();
        assertThat(actual.getOrderNumber()).isEqualTo(naceDetail.getOrderNumber());
        assertThat(actual.getOrderCodeDetailsEntity()).isNotNull();
        assertThat(actual.getOrderCodeDetailsEntity().getAdditionalItemInclude()).isEqualTo(naceDetail.getAdditionalItemInclude());
        assertThat(actual.getOrderCodeDetailsEntity().getReferenceToIsic()).isEqualTo(naceDetail.getReferenceToIsic());
        assertThat(actual.getOrderCodeDetailsEntity().getItemInclude()).isEqualTo(naceDetail.getItemInclude());
        assertThat(actual.getOrderCodeDetailsEntity().getRulings()).isEqualTo(naceDetail.getRulings());
        assertThat(actual.getOrderCodeDetailsEntity().getItemExclude()).isEqualTo(naceDetail.getItemExclude());
    }
}
