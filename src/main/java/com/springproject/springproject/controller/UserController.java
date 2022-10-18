package com.springproject.springproject.controller;

import com.springproject.springproject.dto.UserDto;
import com.springproject.springproject.model.Role;
import com.springproject.springproject.model.User;
import com.springproject.springproject.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
//so to reach its endpoints can be api/user/**
@RequestMapping
public class UserController {
    @Autowired
    private IUserServices userServices;

    //Example: POST http://localhost:8080/api/user -data {user form}
    @PostMapping("/api/user")
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

    //GET http://localhist:8080/api/user/login -> It should be same with security login path like described before.
    //this is used for logout path also. After logout spring will redirect it to login path.
    @GetMapping("login")
    public ResponseEntity <?> login(HttpServletRequest request)
    {
    //Authentication info will be stored on request by Spring Security.
        Principal principal = request.getUserPrincipal();
        if (principal == null || principal.getName() == null)
        {
            //here it will be logout redirection also consider it as OK. httpStatus -> No error.
            return new ResponseEntity<>(HttpStatus.OK);
        }
        User user = userServices.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    //http://localhist:8080/api/user/{username}/change/{role}
    @PutMapping ("{username}/change/{role}") //this can be also POST, patch
    public ResponseEntity<?> changeRole(@PathVariable String username, @PathVariable Role role)
    {
        User user = userServices.changeRole(role, username);
        return ResponseEntity.ok(user);
    }
}
