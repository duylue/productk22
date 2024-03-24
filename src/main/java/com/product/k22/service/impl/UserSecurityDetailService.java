package com.product.k22.service.impl;

import com.product.k22.model.User;
import com.product.k22.model.UserSecurityDetail;
import com.product.k22.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityDetailService implements UserDetailsService {
    @Autowired
   private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user ==  null){
            throw new RuntimeException("account khong ton tai");
        }
        UserSecurityDetail detail = new UserSecurityDetail(user);
        return detail;
    }
}
