package com.sba301.orchid.service;

import com.sba301.orchid.pojo.Category;
import com.sba301.orchid.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + id + " does not exist."));
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + id + " does not exist."));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
