package com.mycoll.mycollections.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycoll.mycollections.models.CollectionTag;
import com.mycoll.mycollections.repository.CollectionTagRepository;

@Service
public class CollectionTagService {

    @Autowired
    private CollectionTagRepository collectionTagRepository;
    
    @Transactional
    public void deleteCollectionTag(Long id) {
        collectionTagRepository.deleteById(id);
    }

    @Transactional
    public CollectionTag getTagById(Long id) {
        return collectionTagRepository.findById(id).orElse(null);
    }

    
}
