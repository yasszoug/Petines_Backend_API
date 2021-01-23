package com.petines.RESTApi.repositories;

import com.petines.RESTApi.model.OrderItem;
import com.petines.RESTApi.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewRepository extends CrudRepository<Product, Integer> {


    List<Product> findAll();
    List<Product> findByProductName(String productname);

    @Query("Select p from Product p where p.productName='tshirt' ")
    OrderItem getTshirt();


}
