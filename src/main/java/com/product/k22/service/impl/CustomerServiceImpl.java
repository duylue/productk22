package com.product.k22.service.impl;

import com.product.k22.model.Customer;
import com.product.k22.service.CustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private EntityManagerFactory factory;
    private EntityManager entityManager;

    @Override
    public ArrayList<Customer> getList() {
        entityManager = factory.createEntityManager();
        ArrayList<Customer> list = (ArrayList<Customer>)
                entityManager.createNativeQuery("SELECT * FROM customer", Customer.class).getResultList();
        return list;
    }

    @Override
    public void create(Customer customer) {

        try {
            entityManager = factory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(customer);
            transaction.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Customer findById(int id) {

        try {
            entityManager = factory.createEntityManager();
            Customer customer =  entityManager.
                   getReference(Customer.class,id);
            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
