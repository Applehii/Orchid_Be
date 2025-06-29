package com.sba301.orchid.service;

import com.sba301.orchid.pojo.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(String id);
    Category getCategoryById(String id);
    List<Category> getAllCategories();
}
