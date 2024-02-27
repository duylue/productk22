package com.product.k22.service;

import com.product.k22.model.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    List<Category> getlist();
    int save(Category category);
    void delete( int id);
    Category findById(int id);

}
