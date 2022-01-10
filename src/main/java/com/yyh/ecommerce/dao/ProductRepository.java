package com.yyh.ecommerce.dao;

import com.yyh.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path="products")
// Accept calls from web browser scripts for this origin
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    Spring Data JPA query methods: findBy, readBy, queryBy, etc ...

    Spring Data REST will automatically expose endpoints for query methods

    Exposes endpoint: /search/<<queryMethodName>>
    */

    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    // similar to 'SELECT * FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%');'
    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

}
