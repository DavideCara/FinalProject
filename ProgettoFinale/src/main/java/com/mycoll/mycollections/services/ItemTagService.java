package com.mycoll.mycollections.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycoll.mycollections.models.ItemTag;
import com.mycoll.mycollections.repository.ItemTagRepository;

@Service
public class ItemTagService {

    @Autowired
    private ItemTagRepository repository;
    
    @Transactional
    public ItemTag addItemTag(ItemTag itemTag) {
        return repository.save(itemTag);
    }

    // public int countTagsForItem(ItemTag itemTag) {
    //     return repository.countTagsForItem(itemTag);
    // }

    @Transactional    
    public String deleteItemTag(Long id) {
        repository.deleteById(id);
        return "ItemTag removed !! " + id;
    }



    

}
