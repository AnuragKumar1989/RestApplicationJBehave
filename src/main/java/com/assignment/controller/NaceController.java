package com.assignment.controller;

import com.assignment.domin.NaceDetail;
import com.assignment.repository.entity.OrderCodeEntity;
import com.assignment.service.NaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller class for NACE.
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class NaceController {

    private final NaceService naceService;

    public NaceController(NaceService naceService) {
        this.naceService = naceService;
    }

    @RequestMapping(value = "/nace/{orderNumber}")
    public ResponseEntity<String> getNaceDetails(@PathVariable Integer orderNumber) {
        OrderCodeEntity details = naceService.getDetails(orderNumber);
        return new ResponseEntity<>(details.toString(), HttpStatus.OK);
    }

    @PostMapping(value = "/nace", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> putNaceDetails(@RequestBody NaceDetail naceDetail) {
        naceService.putDetails(naceDetail);
        return new ResponseEntity<>("Saved successfully", HttpStatus.ACCEPTED);
    }
}
