package com.mycoll.mycollections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycoll.mycollections.models.ItemTag;

public interface ItemTagRepository extends JpaRepository<ItemTag, Long> {
    ItemTag findByName(String name);
    void deleteByName(String name);
    // int countTagsForItem(ItemTag itemTag);
    
}
