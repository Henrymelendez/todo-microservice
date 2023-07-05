package com.todoApp.todoservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse {
    private Long id;
    @Column(nullable = false)
    private String task;
    private String description;
    private Long userId;
    private CategoryResponse categoryResponse;

}
