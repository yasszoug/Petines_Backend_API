package com.petines.RESTApi.services;

import com.petines.RESTApi.model.User;
import com.petines.RESTApi.repositories.ProductRepository;
import com.petines.RESTApi.repositories.UserRepository;
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
