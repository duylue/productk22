package com.product.k22.service;

import com.product.k22.model.Customer;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    ArrayList<Customer> getList();
    void create(Customer customer);
    void update(Customer customer);
    void delete(int id);
    Customer findById(int id);
    List<Map<String, Object>> getListAddress();
    List<Map<String, Object>> getCusDetail( int cid);
}
