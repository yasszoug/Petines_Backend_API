package com.project.stylealpha.stylealpha.repositories;

import com.project.stylealpha.stylealpha.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;


public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAll();
    Product findByPid(Integer pid);
    Product findByStatus(String status);



}
