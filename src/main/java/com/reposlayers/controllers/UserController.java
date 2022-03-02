package com.reposlayers.controllers;

import com.reposlayers.UserService.UserService;
import com.reposlayers.models.User;
import com.reposlayers.repositories.LoginRepo;
import com.reposlayers.repositories.UserRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    @PostMapping
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User u = new User();
        u.setFullName(request.getParameter("name"));
        u.setEmail(request.getParameter("email"));
        u.setUsername(request.getParameter("username"));
        u.setPassword(request.getParameter("password"));
         userRepo.save(u);
         response.sendRedirect("index.html");
    }




    @GetMapping
    public List<User> getAllUsers(){

        return userRepo.findAll();
    }
}
