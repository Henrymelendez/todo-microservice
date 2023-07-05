package com.todoApp.catagoryservice.controller;

import com.todoApp.catagoryservice.dto.CategoryRequest;
import com.todoApp.catagoryservice.dto.CategoryResponse;
import com.todoApp.catagoryservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse saveCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse =categoryService.saveCategory(categoryRequest);
        return categoryResponse;
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategory(@PathVariable(name = "id") Long id){
        CategoryResponse categoryResponse =categoryService.getByCategoryById(id);
        return categoryResponse;
    }
}
