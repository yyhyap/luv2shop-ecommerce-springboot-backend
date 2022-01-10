package com.yyh.ecommerce.dao;

import com.yyh.ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {

    // behind the scenes, Spring will execute a query similar to:
    /*
    SELECT * FROM orders
    LEFT OUTER JOIN customer
    ON orders.customer_id = customer.id
    WHERE customer.email = :email
    */
    Page<Order> findByCustomerEmail(@Param("email") String email, Pageable pageable);
    Page<Order> findByCustomerEmailOrderByDateCreatedDesc(@Param("email") String email, Pageable pageable);

}
