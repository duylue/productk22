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
    @Query(value = "select p.*, c.cname ,s.sname\n" +
            "from  product p, category c, status s\n" +
            "where p.cid = c.cid and s.sid = p.sid", nativeQuery = true)
    List<Map<String, Object>> getlist();



}
