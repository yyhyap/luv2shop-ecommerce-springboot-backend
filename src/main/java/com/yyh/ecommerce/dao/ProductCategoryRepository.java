package com.yyh.ecommerce.dao;

import com.yyh.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// collectionResourceRel >> Name of JSON entry
/*
{
    "_embedded": {
        "productCategory": [      <<<<<<<<<<<<<< collectionResourceRel = "productCategory"
            {
                "categoryName": "BOOKS",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/product-category/1"
                    },
                    "productCategory": {         <<<<<<<<<<<<<<< itemResourceRel
                        "href": "http://localhost:8080/api/product-category/1"
                    },
                    "products": {
                        "href": "http://localhost:8080/api/product-category/1/products"
                    }
                }
            }
        ]
 */
// path >> API path
@RepositoryRestResource(collectionResourceRel = "productCategory", path="product-category")
// Accept calls from web browser scripts for this origin
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
