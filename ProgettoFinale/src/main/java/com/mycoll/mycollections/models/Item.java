package com.mycoll.mycollections.models;

import java.sql.Date;

import java.util.Set;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="collection_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection collection;

    @Column(nullable = false)
    private String name;

    private String image;

    private String description;

    private Date date;

    private double number;

    @ManyToMany
    @JoinTable(name = "item_tags_rel", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "item_tag_id"))
    Set<ItemTag> itemTags;

}
