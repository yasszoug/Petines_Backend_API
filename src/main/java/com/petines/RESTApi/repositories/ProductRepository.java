package com.petines.RESTApi.repositories;

import com.petines.RESTApi.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAll();
    Product findByPid(Integer pid);
    Product findByStatus(String status);



}
