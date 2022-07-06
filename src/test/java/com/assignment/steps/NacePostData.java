package com.assignment.steps;

import com.assignment.domin.NaceDetail;
import com.assignment.repository.entity.OrderCodeEntity;
import com.assignment.service.NaceService;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Story file implementation for saving NACE details.
 */
@Steps
public class NacePostData {
    private NaceDetail naceDetail;
    @Autowired
    private NaceService naceService;

    @Given("the order number for Nace is $orderNumber")
    public void postData(Integer orderNumber) {
        naceDetail = new NaceDetail();
        naceDetail.setOrderNumber(orderNumber);
    }

    @Given("the level is $level")
    public void setLevel(int level) {
        naceDetail.setLevel(level);
    }

    @Given("the code is $code")
    public void setCode(String code) {
        naceDetail.setCode(code);
    }

    @Given("the description is $description")
    public void setDescription(String description) {
        naceDetail.setDescription(description);
    }

    @Given("the reference to isic is $reference")
    public void setReferenceToIsic(String reference) {
        naceDetail.setReferenceToIsic(reference);
    }

    @When("the user is saving the data")
    public void save() {
        naceService.putDetails(naceDetail);
    }

    @Then("the data gets saved")
    public void verify() {
        OrderCodeEntity result = naceService.getDetails(naceDetail.getOrderNumber());
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(naceDetail.getCode());
        assertThat(result.getDescription()).isEqualTo(naceDetail.getDescription());
        assertThat(result.getLevel()).isEqualTo(naceDetail.getLevel());
        assertThat(result.getOrderCodeDetailsEntity()).isNotNull();
        assertThat(result.getOrderCodeDetailsEntity().getRulings()).isEqualTo(naceDetail.getRulings());
        assertThat(result.getOrderCodeDetailsEntity().getReferenceToIsic()).isEqualTo(naceDetail.getReferenceToIsic());
    }
}
