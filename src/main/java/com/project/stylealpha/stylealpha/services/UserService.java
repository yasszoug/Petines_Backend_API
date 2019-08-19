package com.project.stylealpha.stylealpha.services;

import com.project.stylealpha.stylealpha.model.Product;
import com.project.stylealpha.stylealpha.model.User;
import com.project.stylealpha.stylealpha.repositories.ProductRepository;
import com.project.stylealpha.stylealpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserByUsername(String username) {
        return   userRepository.findByUsername(username);
    }


}
