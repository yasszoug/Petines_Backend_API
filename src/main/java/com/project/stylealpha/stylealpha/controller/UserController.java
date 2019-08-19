package com.project.stylealpha.stylealpha.controller;

import com.project.stylealpha.stylealpha.services.UserService;
import com.project.stylealpha.stylealpha.model.User;
import com.project.stylealpha.stylealpha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }


    @RequestMapping("/login/{username}")
    public User getUsername(@PathVariable String username)
    {
        return userService.getUserByUsername(username);
    }


    @RequestMapping(method= RequestMethod.POST, value="/login")
    public void addUser(@RequestBody User user) {
        userRepo.save(user);
    }

    @PutMapping("/users/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User user ) {

        User newUser = userRepo.findByUsername(username);
        newUser.setPassword(user.getPassword());
        newUser.setProfileimg(user.getProfileimg());
        newUser.setUsername(user.getUsername());
        newUser.setEmailAddress(user.getEmailAddress());

        userRepo.save(newUser);

    }


}
