package com.todoApp.userservice.service;

import com.todoApp.userservice.dto.UserRequest;
import com.todoApp.userservice.dto.UserResponse;
import com.todoApp.userservice.model.User;
import com.todoApp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;


    public UserResponse register(UserRequest userRequest){
        String encodedPW = passwordEncoder.encode(userRequest.getPassword());
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setUserDescription(userRequest.getUserDescription());
        user.setPassword(encodedPW);
        user.setRole("standard");
        user.setEnabled(true);
        User user1 =userRepository.save(user);
        return UserResponse.builder()
                .id(user1.getId())
                .userDescription(user1.getUserDescription())
                .userName(user1.getUsername())
                .build();
    }
    public UserResponse getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .userDescription(user.getUserDescription())
                .build();
    }
}
