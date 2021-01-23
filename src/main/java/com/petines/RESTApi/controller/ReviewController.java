package com.petines.RESTApi.controller;

import com.petines.RESTApi.model.Product;
import com.petines.RESTApi.model.Review;
import com.petines.RESTApi.model.User;
import com.petines.RESTApi.repositories.ProductRepository;
import com.petines.RESTApi.repositories.ReviewRepository;
import com.petines.RESTApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/reviews")
    public List<Review> getReviews()
    {
        return reviewRepository.findAll();
    }

    @RequestMapping(method= RequestMethod.POST, value="/reviews")
    @ResponseBody
    public void addReview(@RequestBody Review review) {
        Review newReview = new Review();

        Product p = productRepository.findByPid(review.getProd_id().getPid());
        User u = userRepository.findByUsername(review.getUser().getUsername());


        newReview.setDate(review.getDate());
        newReview.setProd_id(p);
        newReview.setUser(u);
        newReview.setReview(review.getReview());
        reviewRepository.save(newReview);
    }

    @RequestMapping("/reviews/{pid}")
    public List<Review> getReview(@PathVariable int pid)
    {
        Product p = productRepository.findByPid(pid);
        return reviewRepository.findReviewsByProduct(p);
    }

}