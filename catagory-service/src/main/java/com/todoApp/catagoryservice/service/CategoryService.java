package com.todoApp.catagoryservice.service;

import com.todoApp.catagoryservice.dto.CategoryRequest;
import com.todoApp.catagoryservice.dto.CategoryResponse;
import com.todoApp.catagoryservice.model.Category;
import com.todoApp.catagoryservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.todoApp.catagoryservice.mapper.CategoryMapper.categoryMapper;
import static com.todoApp.catagoryservice.mapper.CategoryMapper.categoryResponseMapper;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryResponse saveCategory(CategoryRequest categoryRequest){
        Category category = categoryMapper(categoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return categoryResponseMapper(savedCategory);
    }

    public CategoryResponse getByCategoryById(Long id){
        Category category = categoryRepository.findById(id).get();
        CategoryResponse categoryResponse = categoryResponseMapper(category);
        return categoryResponse;
    }

}
