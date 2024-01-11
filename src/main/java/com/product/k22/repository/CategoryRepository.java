package com.product.k22.repository;

import com.product.k22.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select p.* , c.category_name from product p\n" +
            ", category c\n" +
            "where c.category_id=p.category_id and p.id = :id and c.category_id = :cid", nativeQuery = true)
    Map<String, Object> getProductDetail(@Param("id") int id, @Param("cid") int cid);
}
