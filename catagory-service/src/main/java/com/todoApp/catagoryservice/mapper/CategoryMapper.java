package com.todoApp.catagoryservice.mapper;

import com.todoApp.catagoryservice.dto.CategoryRequest;
import com.todoApp.catagoryservice.dto.CategoryResponse;
import com.todoApp.catagoryservice.model.Category;

public class CategoryMapper {

    public static CategoryResponse categoryResponseMapper(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .description(category.getDescription())
                .name(category.getName())
                .build();
    }
    public static Category categoryMapper(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setDescription(categoryRequest.getDescription());
        category.setName(categoryRequest.getName());
        return category;
    }
}
