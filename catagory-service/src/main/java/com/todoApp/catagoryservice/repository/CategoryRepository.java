package com.todoApp.catagoryservice.repository;

import com.todoApp.catagoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
