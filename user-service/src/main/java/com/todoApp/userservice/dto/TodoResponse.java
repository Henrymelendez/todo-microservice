package com.todoApp.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoResponse {
    private Long id;
    private String task;
    private String description;
    private Long userId;
    private CategoryResponse categoryResponse;
}
