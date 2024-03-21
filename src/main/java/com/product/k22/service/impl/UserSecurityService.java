package com.product.k22.service.impl;

import com.product.k22.model.MyUserDetail;
import com.product.k22.model.User;
import com.product.k22.repository.URp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    URp userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService.findByUsername(username);
        MyUserDetail userDetail = new MyUserDetail(u);
        return userDetail;
    }
}
