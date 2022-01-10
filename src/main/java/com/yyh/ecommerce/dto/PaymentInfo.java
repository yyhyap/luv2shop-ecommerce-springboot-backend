package com.yyh.ecommerce.dto;

import lombok.Data;

@Data
public class PaymentInfo {
    // in cents for Stripe
    private int amount;
    private String currency;
}
