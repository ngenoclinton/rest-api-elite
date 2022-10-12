package com.springproject.springproject.services;

import com.springproject.springproject.model.Role;
import com.springproject.springproject.model.User;

import java.util.List;

public interface IUserServices {
    User saveUser(User user);

    User changeRole(Role newRole, String username);

    User findByUsername(String username);

    User deleteUser(Long userId);

    List<User> findAllUsers();
}
