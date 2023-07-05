package com.todoApp.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;
    private String userDescription;
    private String password;
    private String role;
    private boolean enabled;

}
