package com.ecommerce.app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotNull(message = "Order id is required")
    private Long orderId;

    @NotNull(message = "Payment method is required")
    @Pattern(regexp = "UPI|CARD|COD", message = "Method must be UPI, CARD or COD")
    private String method;
}