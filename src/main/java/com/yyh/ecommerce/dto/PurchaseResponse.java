package com.yyh.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponse {

    // Lombok @Data will generate constructor for FINAL fields
    // or field with annotation @NonNull
    private final String orderTrackingNumber;

}
