package com.growthcapital.seeder.controller;

import com.growthcapital.seeder.dto.PaymentData;
import com.growthcapital.seeder.enums.PaymentStatus;
import com.growthcapital.seeder.exception.ResourceNotFoundException;
import com.growthcapital.seeder.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/calculatePaybackAmount")
    public ResponseEntity<String> calculatePaybackAmount(@RequestBody PaymentData paymentData) {
        try {
            BigDecimal paymentAmount = paymentService.calculatePaybackAmount(paymentData);
            return ResponseEntity.ok(paymentAmount.toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        try {
            throw new ResourceNotFoundException("Test Message");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestParam Integer paymentId, @RequestParam PaymentStatus paymentStatus) {
        try {
            paymentService.updateStatus(paymentId, paymentStatus);
            return ResponseEntity.ok("Payment Status Changed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
