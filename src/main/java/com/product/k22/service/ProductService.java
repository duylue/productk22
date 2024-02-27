package com.product.k22.service;

import com.product.k22.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {
    ArrayList<Product> getList();

    List<Map<String, Object>> getListDetail();

    int save(Product product);

    void delete(int id);

    Product findById(int id);

}
