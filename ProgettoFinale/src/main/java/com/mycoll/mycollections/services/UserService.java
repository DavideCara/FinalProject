package com.mycoll.mycollections.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycoll.mycollections.models.Collection;
import com.mycoll.mycollections.models.User;
import com.mycoll.mycollections.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<Collection> getCollectionsByUserId(Long userId, HttpSession session) {
        User loggedUser = (User)session.getAttribute("user");
        User user = userRepository.findById(userId).orElse(null);
        if (userId == loggedUser.getId()) {
            return user.getCollections();
        }
        else {
            List<Collection> publicCollections = new ArrayList<>();
            for (Collection collec : user.getCollections()) {
                if(collec.isPublic()) {
                    publicCollections.add(collec);
                }
            }
            return publicCollections;
        }
    }


    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    @Transactional
    public String deleteById(Long id) {
        userRepository.deleteById(id);
        return "User removed !! " + id;
    }



    // public User getUserById(@PathVariable Long id) {
    //     return repository.findById(id).orElse(null);
    // }

    // @GetMapping("/user/{username}")
    // public User getUserByUsername(@PathVariable String username) {
    //     return repository.findByUsername(username);
    // }
    
    // @GetMapping("/getUserCollections")
    // public List<Collection> getUserCollections() {
    //     return collectionRepository.findAll();
    // }

    // // Put
    // @PutMapping("/updateUser")
    // public User updateUser(@RequestBody User user) {
    //     User existingUser = repository.findById(user.getId()).orElse(null);
    //     existingUser.setEmail(user.getEmail());
    //     existingUser.setUsername(user.getUsername());
    //     existingUser.setPassword(user.getPassword());
    //     return repository.save(existingUser);
    // }

    // // Delete
    // @DeleteMapping("/deleteUser/{id}")
    // public String deleteUser(@PathVariable Long id) {
    //     repository.deleteById(id);
    //     return "User removed !! " + id;
    // }
}
