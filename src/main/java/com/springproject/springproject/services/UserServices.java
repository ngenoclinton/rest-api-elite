package com.springproject.springproject.services;

import com.springproject.springproject.model.Role;
import com.springproject.springproject.model.User;
import com.springproject.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public abstract class UserServices implements IUserServices{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LocalDate localDateTime;

    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setCreateDate(localDateTime.now());
        //default role is USER
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }
    @Override
    public User changeRole(Role newRole, String username)
    {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException());
        user.setRole(newRole);
        return userRepository.save(user);
    }
    @Override
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username).orElse(null);
    }
    @Override
    public User deleteUser(Long userId)
    {
        User user =userRepository.findById(userId).orElseThrow(()->new RuntimeException());
        userRepository.delete(user);
        return user;
    }
    @Override
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }
}
