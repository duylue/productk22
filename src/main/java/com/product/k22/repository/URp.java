package com.product.k22.repository;

import com.product.k22.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URp extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
