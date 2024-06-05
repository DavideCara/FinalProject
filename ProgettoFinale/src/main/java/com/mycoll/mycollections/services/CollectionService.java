package com.mycoll.mycollections.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycoll.mycollections.models.Collection;
import com.mycoll.mycollections.repository.CollectionRepository;

@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;

    @Transactional//questa annotazione permette di gestire automaticamente le transazioni
    public Collection addCollection(Collection collection) {
        return collectionRepository.save(collection);
    }

    @Transactional
    public Collection getCollectionById(Long id) {
        return collectionRepository.findById(id).orElse(null);
    }

    @Transactional
    public Collection getCollectionByName(String name) {
        return collectionRepository.findByName(name);
    }

    @Transactional
    public List<Collection> getCollections() {
        return collectionRepository.findAll();
    }

    @Transactional
    public Collection updateCollection(Collection collection) {
        Collection existingCollection = collectionRepository.findById(collection.getId()).orElse(null);
        existingCollection.setName(collection.getName());
        existingCollection.setImage(collection.getImage());
        existingCollection.setWishlist(collection.isWishlist());
        return collectionRepository.save(existingCollection);
    }

    @Transactional
    public void deleteCollection(Long id) {
        collectionRepository.deleteById(id);
    }

    // public List<Collection> getCollectionsByTag(String collectionTag) {
    //     return collectionRepository.findByCollectionTag(collectionTag);
    // }

    
}
