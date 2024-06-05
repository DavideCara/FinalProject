package com.mycoll.mycollections.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycoll.mycollections.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String name);
    List<Item> findByCollectionId(Long collectionId);
    // List<Item> findByItemTag(String itemTag);
    // List<Item> findByTagInCollection(Long collectionId, String itemTag);   
}