package com.reposlayers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reposlayers.models.User;
import com.reposlayers.repositories.LoginRepo;
import com.reposlayers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    private UserRepository userRepository;

    //    private LoginRepo loginRepo;
    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {



        try {
            User u = userRepository.findByUsername(request.getParameter("loginUser"));
            if(u != null) {

                if (u.getUsername().equals(request.getParameter("loginUser")) && u.getPassword().equals(request.getParameter("loginPassword"))) {
                    request.getSession().setAttribute("user", u);
                    response.sendRedirect("Dashboard.html");
//                    response.getWriter().write("Succesful Login");
//                    System.out.println("Success");
                }else {
//                    response.getWriter().write("Incorrect username or password, please try again");
                    response.sendRedirect("index.html");
//                    System.out.println("User not found");
                }
                }else{
//
//                response.getWriter().write("Incorrect username or password, please try again");
                response.sendRedirect("index.html");
//                System.out.println("User not found");
            }
        } catch (Exception e) {
//            System.out.println("Wrong username");
//            response.getWriter().write("Username not Found");
//            response.sendRedirect("index.html");


        }
    }
}


//    @PostMapping
//    public void login(HttpServletRequest request, HttpServletResponse response) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        try {
//            User u = userRepository.findByUsername(username);
//            if (u != null) {
//                if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
////                    request.getSession().setAttribute("user", u);
////                    response.sendRedirect("");
//                    response.getWriter().write("Succesfull");
//                } else {
////                    response.sendRedirect("User not found");
//                    response.getWriter().write("User not found");
//                }
//            }
//
//        } catch (Exception e) {
//
//
//        }
//    }
//}


