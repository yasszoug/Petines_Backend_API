package com.project.stylealpha.stylealpha.repositories;

import com.project.stylealpha.stylealpha.model.OrderItem;
import com.project.stylealpha.stylealpha.model.Product;
import com.project.stylealpha.stylealpha.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewRepository extends CrudRepository<Product, Integer> {


    List<Product> findAll();
    List<Product> findByProductName(String productname);

    @Query("Select p from Product p where p.productName='tshirt' ")
    OrderItem getTshirt();


}
