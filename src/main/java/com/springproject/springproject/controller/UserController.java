package com.springproject.springproject.controller;

import com.springproject.springproject.dto.UserDto;
import com.springproject.springproject.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
//so to reach its endpoints can be api/user/**
@RequestMapping
public class UserController {
    @Autowired
    private IUserServices userServices;
    //Example: POST http://localhost:8080/api/user -data {user form}
    @PostMapping("api/user")
    public ResponseEntity<?> register (@RequestBody @Valid UserDto user) //@Valid provides validation check
    {
        if (userServices.findByUsername(user.getUsername()) != null)
        {
            //user should be unique.
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //convert dto to user model object then save q  with service
        userServices.saveUser(user.convertToUser());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
