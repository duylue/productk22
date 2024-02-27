package com.product.k22.repository;

import com.product.k22.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
