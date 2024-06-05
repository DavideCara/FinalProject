package com.mycoll.mycollections.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycoll.mycollections.models.CollectionTag;

public interface CollectionTagRepository extends JpaRepository<CollectionTag, Long> {
    //CollectionTag findByName(String name);
}