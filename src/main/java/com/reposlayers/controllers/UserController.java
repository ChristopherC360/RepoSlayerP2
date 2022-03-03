package com.reposlayers.controllers;

import com.reposlayers.models.User;
import com.reposlayers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController  {

    private UserRepository userRepo;



    @Autowired
    public UserController(UserRepository userRepo){
        this.userRepo = userRepo;
    }

//    @PostMapping(consumes = "application/json", produces = "application/json")
//    public User addUser(@RequestBody User user){
//        return userRepo.save(user);
//    }
    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(User user, HttpServletResponse response) throws IOException {
        try {
            userRepo.save(user);
            response.sendRedirect("http://localhost:8080/index.html");
        } catch (Exception e) {
            response.sendRedirect("http://localhost:8080/RegistrationForm.html");
        }
        
    }


    @GetMapping
    public List<User> getAllUsers(){

        return userRepo.findAll();
    }
}
