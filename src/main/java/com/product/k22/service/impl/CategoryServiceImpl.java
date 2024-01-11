package com.product.k22.service.impl;

import com.product.k22.model.Category;
import com.product.k22.repository.CategoryRepository;
import com.product.k22.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getlist() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }

    @Override
    public int save(Category category) {
        Category temp = categoryRepository.save(category);
        return temp.getCategoryId();
    }

    @Override
    public Map<String, Object> getProductDetail(int id, int cid) {
        return categoryRepository.getProductDetail(id,cid);
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findById(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = categoryOptional.get();
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category khong ton tai");
        }
        return category;
    }
}
