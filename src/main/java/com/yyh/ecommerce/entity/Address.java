package com.yyh.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    @OneToOne
    // join using primary keys
    // indicate that primary key of Address entity is USED as the foreign key value for the associated Order entity
    // share the primary key for two columns in Order(shipping_address_id and billing_address_id)
    @PrimaryKeyJoinColumn
    private Order order;
}
