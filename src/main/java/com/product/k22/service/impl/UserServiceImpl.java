package com.product.k22.service.impl;

import com.product.k22.model.Role;
import com.product.k22.model.User;
import com.product.k22.model.UserSecurityDetail;
import com.product.k22.repository.RoleRepository;
import com.product.k22.repository.UserRepository;
import com.product.k22.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Role findRoleById(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getListRole() {
        return roleRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User u) {
        userRepository.save(u);
    }

}
