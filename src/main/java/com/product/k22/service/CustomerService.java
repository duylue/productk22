package com.product.k22.service;

import com.product.k22.model.Customer;

import java.util.ArrayList;

public interface CustomerService {
    ArrayList<Customer> getList();
    void create(Customer customer);
    void update(Customer customer);
    void delete(int id);
    Customer findById(int id);
}
