package com.codemiro.corostore.service;

import com.codemiro.corostore.catalog.CategoryRequest;
import com.codemiro.corostore.repository.CategoryRepository;
import com.codemiro.corostore.repository.entity.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category create(CategoryRequest categoryRequest) {
        return categoryRepository.save( Category.builder()
                .name(categoryRequest.getName())
                .build());

    }

    public Collection<Category> getAll() {
        return categoryRepository.findAll();
    }
}
