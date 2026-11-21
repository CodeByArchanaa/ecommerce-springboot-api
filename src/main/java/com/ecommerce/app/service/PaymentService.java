package com.ecommerce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.app.dto.PaymentRequest;
import com.ecommerce.app.entity.Order;
import com.ecommerce.app.entity.Payment;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Payment pay(PaymentRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (paymentRepository.existsByOrderId(order.getId())) {
            throw new IllegalArgumentException("Payment already done for this order");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setMethod(request.getMethod());

        if (request.getMethod().equals("COD")) {
            payment.setStatus("PENDING");
        } else {
            payment.setStatus("SUCCESS");
            order.setStatus("PAID");
            orderRepository.save(order);
        }
        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrder(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found for this order"));
    }
}