package com.assignment.steps;

import com.assignment.domin.NaceDetail;
import com.assignment.repository.entity.OrderCodeEntity;
import com.assignment.service.NaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Story file implementation for getting NACE details.
 */
@Steps
public class NaceGetData {

    private OrderCodeEntity result;
    private NaceDetail naceDetail;
    @Autowired
    private NaceService naceService;
    @Autowired
    private ObjectMapper objectMapper;

    @Given("the nace details")
    public void prepareNaceDetail() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/json/NaceDetail.json"));
        String json = new String(bytes, StandardCharsets.UTF_8);
        naceDetail = objectMapper.readValue(json, NaceDetail.class);
        naceDetail.setOrderNumber(3546);
    }

    @When("the user is retrieves the data with given order number $orderNumber")
    public void getDetails(Integer orderNumber) {
        result = naceService.getDetails(orderNumber);
    }

    @Then("the data gets retrieved")
    public void verifyOrder() {
        assertThat(result).isNotNull();
        assertThat(result.getOrderNumber()).isEqualTo(naceDetail.getOrderNumber());
    }

    @Then("the level is $level")
    public void verifyLevel(Integer level) {
        assertThat(result.getLevel()).isEqualTo(level);
    }

    @Then("the code is $code")
    public void verifyCode(String code) {
        assertThat(result.getCode()).isEqualTo(code);
    }

    @Then("the description is $description")
    public void verify(String description) {
        assertThat(result.getDescription()).isEqualTo(description);
    }

    @Then("the reference to isic is $reference")
    public void verifyReferenceToIsic(String reference) {
        assertThat(result.getOrderCodeDetailsEntity()).isNotNull();
        assertThat(result.getOrderCodeDetailsEntity().getReferenceToIsic()).isEqualTo(reference);
    }
}
