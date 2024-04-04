package com.example.fileparser.controllers;

import com.example.fileparser.exceptions.ItemAlreadyExistsException;
import com.example.fileparser.exceptions.ItemNotFoundException;
import com.example.fileparser.models.User;
import com.example.fileparser.models.UserDTO;
import com.example.fileparser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestParam("username") String username,  @RequestParam("password") String password) throws ItemAlreadyExistsException {
        return userService.registerUser(username, password);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.FOUND)
    public User loginUser(@RequestParam("username") String username, @RequestParam("password") String password) throws ItemNotFoundException {
        return userService.loginUser(username, password);
    }

}
