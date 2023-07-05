package com.todoApp.userservice.controller;

import com.todoApp.userservice.dto.APIResponse;
import com.todoApp.userservice.dto.UserRequest;
import com.todoApp.userservice.dto.UserResponse;
import com.todoApp.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //build user create rest api
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse saveUser(@RequestBody UserRequest userRequest){
        UserResponse response = userService.saveUser(userRequest);
        return response;
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public APIResponse getUserById(@PathVariable("id") Long id){
    APIResponse userResponse = userService.getUserById(id);
    return  userResponse;
   }
   @GetMapping
   @ResponseStatus(HttpStatus.OK)
   public List<UserResponse> getAllUsers(){
        List<UserResponse> responses = userService.getUsers();
        return responses;
   }


}
