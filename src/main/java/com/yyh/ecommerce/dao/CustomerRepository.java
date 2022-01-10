package com.yyh.ecommerce.dao;

import com.yyh.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Behind the scenes, Spring will execute the query:
    // SELECT * FROM Customer c WHERE c.email = theEmail;
    // return null if not found
    Customer findByEmail(String theEmail);

}
