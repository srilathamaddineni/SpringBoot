package com.devtiro.blog.services.impl;

import com.devtiro.blog.domain.entities.Category;
import com.devtiro.blog.repositories.CategoryRepository;
import com.devtiro.blog.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
       if(categoryRepository.existsByNameIgnoreCase(category.getName()))
       {
           throw new IllegalArgumentException("Category name is already existed"+ category.getName());
       }
       return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category=categoryRepository.findById(id);
        if(category.isPresent())
        {
            if(!category.get().getPosts().isEmpty())
            {
                throw new IllegalArgumentException("Category has posts associated with it");
            }
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public Category getCategoryByID(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Category not found with id:"+id));
    }
}
