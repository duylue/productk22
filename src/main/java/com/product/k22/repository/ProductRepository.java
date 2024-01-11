package com.product.k22.repository;

import com.product.k22.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select p.* , c.category_name from product p\n" +
            ", category c\n" +
            "where c.category_id=p.category_id", nativeQuery = true)
    List<Map<String, Object>> getlist();

    @Query(value = "select p.* , c.category_name from product p\n" +
            ", category c\n" +
            "where c.category_id=p.category_id and p.id = :id and category = :cid", nativeQuery = true)
    Map<String, Object> getProductDetail(@Param("id") int id,@Param("cid") int cid);
}
