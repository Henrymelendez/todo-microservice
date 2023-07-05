package com.todoApp.todoservice.config;

import com.todoApp.todoservice.dto.CategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "CATEGORY-SERVICE")
public interface APICient {
    @GetMapping("api/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    CategoryResponse getCategory(@PathVariable("id") Long id);
}
