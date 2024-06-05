package com.mycoll.mycollections.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="collections")
@Data
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    private String name;

    private String image;

    @JsonProperty
    private boolean isWishlist;

    @JsonProperty
    private boolean isPublic;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToMany
    @JoinTable(name = "collection_tags_rel", joinColumns = {@JoinColumn(name = "collection_id", referencedColumnName = "id")}, 
    inverseJoinColumns = {@JoinColumn(name = "collection_tag_id", referencedColumnName = "id")})
    private Set<CollectionTag> collectionTags;

    public void addTagToSelf(CollectionTag tag) {
        collectionTags.add(tag);
    }
}
