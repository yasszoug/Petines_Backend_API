package com.petines.RESTApi.repositories;

import com.petines.RESTApi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface    UserRepository extends CrudRepository<User, String> {
     List<User> findAll();
     User findById(Integer id);
     User findByUsername(String username);
}
