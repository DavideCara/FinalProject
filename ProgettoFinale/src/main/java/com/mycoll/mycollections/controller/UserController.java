package com.mycoll.mycollections.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.mycoll.mycollections.models.Collection;
import com.mycoll.mycollections.models.User;
import com.mycoll.mycollections.services.CollectionService;
import com.mycoll.mycollections.services.UserService;


@Controller
public class UserController {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/registered";
    }

    @GetMapping("/registered")
    public String registered() {
        return "registered.html";
    }
    

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users.html";
    }

    // Get
    @GetMapping("/userID/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUserCollections")
    public List<Collection> getUserCollections() {
        return collectionService.getCollections();
    }

    // Put
    @PutMapping("/updateUser")
    public User updateUser(@ModelAttribute User user) {
        return userService.updateUser(user);
    }

    // Delete
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return id.toString();
    }
}
