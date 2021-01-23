package com.petines.RESTApi.repositories;

import com.petines.RESTApi.model.Product;
import com.petines.RESTApi.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, String> {

    List<Review> findAll();


    @Query("SELECT r FROM Review r WHERE r.prod_id.pid = :#{#product.pid}")
    List<Review> findReviewsByProduct(@Param("product") Product product);
}
