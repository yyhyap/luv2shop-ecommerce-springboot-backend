package com.yyh.ecommerce.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.yyh.ecommerce.dto.PaymentInfo;
import com.yyh.ecommerce.dto.Purchase;
import com.yyh.ecommerce.dto.PurchaseResponse;
import com.yyh.ecommerce.service.CheckoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@Valid @RequestBody Purchase purchase)
    {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<String> createPaymentIntent(@Valid @RequestBody PaymentInfo paymentInfo) throws StripeException {

        log.info("Payment amount: {}", paymentInfo.getAmount());
        log.info("Payment currency: {}", paymentInfo.getCurrency());
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
        String paymentString = paymentIntent.toJson();

        return new ResponseEntity<>(paymentString, HttpStatus.OK);
    }

}
