package com.mycoll.mycollections.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mycoll.mycollections.models.CollectionTag;
import com.mycoll.mycollections.repository.CollectionTagRepository;

@RestController
public class CollectionTagController {

    @Autowired
    private CollectionTagRepository repository;

    // @PostMapping("/addCollectionTag")
    // public CollectionTag addCollectionTag(@RequestBody CollectionTag collectionTag) {
    //     return repository.save(collectionTag);
    // }

    @GetMapping("/collectionTag/{id}")
    public CollectionTag getTagById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/deleteCollectionTag/{id}")
    public String deleteCollectionTag(@PathVariable Long id) {
        repository.deleteById(id);
        return "Collection Tag removed !! " + id;
    }
}
