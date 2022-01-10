package com.yyh.ecommerce.dto;

import com.yyh.ecommerce.entity.Address;
import com.yyh.ecommerce.entity.Customer;
import com.yyh.ecommerce.entity.Order;
import com.yyh.ecommerce.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class Purchase {

    @NotNull
    private Customer customer;

    @NotNull
    private Address shippingAddress;

    @NotNull
    private Address billingAddress;

    @NotNull
    private Order order;

    @NotNull
    private Set<OrderItem> orderItems;

}
