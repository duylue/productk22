package com.product.k22.service;

import com.product.k22.model.Role;
import com.product.k22.model.User;
import com.product.k22.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    void saveRole(Role role);
    Role findRoleById(int id);
   List<Role> getListRole();
   User findUserByUsername(String username);
   void saveUser(User u);

}
