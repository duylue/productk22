package com.product.k22.service.impl;

import com.product.k22.model.Product;
import com.product.k22.repository.ProductRepository;
import com.product.k22.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ArrayList<Product> getList() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> getListDetail() {
        return productRepository.getlist();
    }

    @Override
    public int save(Product product) {
        Product p = productRepository.save(product);
        return p.getPid();
    }


    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }
}
