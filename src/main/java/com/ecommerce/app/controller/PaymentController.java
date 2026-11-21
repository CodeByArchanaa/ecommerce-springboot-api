package com.ecommerce.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.entity.Payment;
import com.ecommerce.app.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public Payment pay(@Valid @RequestBody PaymentRequest request) {
        return paymentService.pay(request);
    }

    @GetMapping("/order/{orderId}")
    public Payment getPayment(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrder(orderId);
    }
}