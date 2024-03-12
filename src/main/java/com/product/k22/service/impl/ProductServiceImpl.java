package com.product.k22.service.impl;

import com.product.k22.dto.ProductDTO;
import com.product.k22.model.Product;
import com.product.k22.repository.ProductRepository;
import com.product.k22.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManagerFactory factory;
    private EntityManager entityManager;

    @Override
    public ArrayList<Product> getList() {
        Product products = productRepository.findByPidAndPnameQuery(3, 1, 1);
        return (ArrayList<Product>) productRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> getListDetail() {
        return productRepository.getlist();
    }

    @Override
    public List<ProductDTO> getProductDetail(int id, String name, int priceId) {
        String query = "select p.pid, p.pname, p.price, c.cname \n" +
                "from  product p, category c\n" +
                "where p.cid = c.cid";
        if (id > 0) {
            query += "\n and c.cid = " + id;
        }
        if (priceId > 0) {
            switch (priceId) {
                case 1:
                    query += "\n and p.price between 0 and 2000  ";
                    break;
                case 2:
                    query += "\n and p.price between 2001 and 5000  ";
                    break;
                case 3:
                    query += "\n and p.price > 5000  ";
                    break;
            }
        }
        if (name != null && !name.equals("")) {
            query += "\n and p.pname = '" + name+ "'";
        }
        entityManager = factory.createEntityManager();
       List<ProductDTO> list =  entityManager.createNativeQuery(query, ProductDTO.class).getResultList();
       return list;
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

    @Override
    public Map<String, Object> getProductDetail(int id) {
        return productRepository.getProductDetail(id);
    }
}
