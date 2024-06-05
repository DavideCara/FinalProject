package com.mycoll.mycollections.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycoll.mycollections.models.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
    Collection findByName(String name);
    //List<Collection> findByCollectionTag(String collectionTag);
}
