package com.assignment.controller;

import com.assignment.domin.NaceDetail;
import com.assignment.repository.entity.OrderCodeDetailsEntity;
import com.assignment.repository.entity.OrderCodeEntity;
import com.assignment.service.NaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link NaceController}.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = NaceController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class NaceControllerTest {
    @MockBean
    private NaceService naceService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getNaceDetailsSuccess() throws Exception {
        Integer orderNumber = 105;
        when(naceService.getDetails(anyInt())).thenReturn(OrderCodeEntity.builder().orderNumber(orderNumber)
                .orderCodeDetailsEntity(new OrderCodeDetailsEntity()).build());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/nace/" + orderNumber)).andReturn();
        verify(naceService).getDetails(eq(orderNumber));
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void putNaceDetailsWithoutBody() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/nace")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(400);
    }

    @Test
    public void putNaceDetailsSuccess() throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/json/NaceDetail.json"));
        String json = new String(bytes, StandardCharsets.UTF_8);
        NaceDetail naceDetail = objectMapper.readValue(json, NaceDetail.class);
        mockMvc.perform(MockMvcRequestBuilders.post(new URI("/api/nace")).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(naceDetail)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.ACCEPTED.value()));
    }
}
