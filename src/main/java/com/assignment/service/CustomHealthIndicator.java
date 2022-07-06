package com.assignment.service;

import com.assignment.controller.NaceController;
import com.assignment.domin.NaceDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Response;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Health indicator for checking application's health.
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    private final NaceController naceController;
    private final ObjectMapper objectMapper;

    public CustomHealthIndicator(NaceController naceController, ObjectMapper objectMapper) {
        this.naceController = naceController;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/json/NaceDetail.json"));
        String json = new String(bytes, StandardCharsets.UTF_8);
        //naceDetail = objectMapper.readValue(json, NaceDetail.class);
        ResponseEntity response = naceController.putNaceDetails(objectMapper.readValue(json, NaceDetail.class));
        if (Response.SC_ACCEPTED == response.getStatusCode().value()) {
            builder.up().withDetail("up", response.getStatusCode().value()).build();
        } else {
            builder.down().withDetail("down", response.getStatusCode().value()).build();
        }
    }
}
