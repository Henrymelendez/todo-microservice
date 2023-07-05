package com.todoApp.userservice.controller;

import com.todoApp.userservice.dto.UserRequest;
import com.todoApp.userservice.dto.UserResponse;
import com.todoApp.userservice.model.User;
import com.todoApp.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRequest userRequest){
        userRequest.setRole("USER");
        UserResponse user = authService.register(userRequest);
        return user;
    }

    @GetMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse auth(Principal principal){
        System.out.println(principal);
        return authService.getUserByUsername(principal.getName());
    }
}
