package com.yyh.ecommerce.config;

import com.yyh.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
// RepositoryRestConfigurer is configurer from Spring Data Rest
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH};

        // disable HTTP methods for Product: POST, PUT and DELETE
        disableHttpMethods(Product.class, config, theUnsupportedActions);

        // disable HTTP methods for ProductCategory: POST, PUT and DELETE
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);

        // disable HTTP methods for Country: POST, PUT and DELETE
        disableHttpMethods(Country.class, config, theUnsupportedActions);

        // disable HTTP methods for State: POST, PUT and DELETE
        disableHttpMethods(State.class, config, theUnsupportedActions);

        // disable HTTP methods for Order: POST, PUT and DELETE
        disableHttpMethods(Order.class, config, theUnsupportedActions);

        // call an internal helper method
        exposeIds(config);

        // configure cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }

    // disable HTTP methods for Classes: POST, PUT and DELETE
    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration().forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config)
    {
        // expose entity ids
        //

        // - GET a list of all ENTITY TYPES from the entity manager
        // for example: Order, Customer, Product, ProductCategory, etc.
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // - CREATE an array (entityClasses) of the entity types
        List<Class> entityClasses = new ArrayList<>();

        // - GET the entity types for the entities
        for(EntityType tempEntityType : entities)
        {
            entityClasses.add(tempEntityType.getJavaType());
            System.out.println(tempEntityType.getJavaType().toGenericString());
        }

        // - EXPOSE the entity ids for the array of ENTITY/domain TYPES
        // CONVERT a collection to an ARRAY using EMPTY ARRAY using toArray method
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
