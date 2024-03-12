package com.product.k22.service;

import com.product.k22.dto.ProductDTO;
import com.product.k22.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {
    ArrayList<Product> getList();

    List<Map<String, Object>> getListDetail();
    List<ProductDTO> getProductDetail(int id, String name, int priceID);
    int save(Product product);
    void delete(int id);

    Product findById(int id);
    Map<String, Object> getProductDetail(int id);
}
