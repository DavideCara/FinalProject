package com.mycoll.mycollections.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycoll.mycollections.models.Collection;
import com.mycoll.mycollections.models.Item;
import com.mycoll.mycollections.repository.CollectionRepository;
import com.mycoll.mycollections.repository.ItemRepository;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Transactional
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public Item addItemCollection(Item item, Long collectionId) {
        Collection collection = collectionRepository.findById(collectionId)
        .orElseThrow(() -> new IllegalArgumentException("No collection found with id " + collectionId));;
        item.setCollection(collection);
        return itemRepository.save(item);
    }

    @Transactional
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Transactional
    public Item getItemByName(String name) {
        return itemRepository.findByName(name);
    }

    @Transactional
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public List<Item> getItemsByCollection(Long collectionId) {
        return itemRepository.findByCollectionId(collectionId);
    }

    @Transactional
    public Item updateItem(Item item) {
        Item existingItem = itemRepository.findById(item.getId()).orElse(null);        
        existingItem.setDate(item.getDate());
        existingItem.setDescription(item.getDescription());
        existingItem.setImage(item.getImage());    
        existingItem.setName(item.getName());
        existingItem.setNumber(item.getNumber());        
        return itemRepository.save(existingItem);
    }

    @Transactional
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    

    // public List<Item> getItemsByItemTag(String itemTag) {
    //     return itemRepository.findByItemTag(itemTag);
    // }

    // public List<Item> getItemsByTagInCollection(Long collectionId, String itemTag) {
    //     return itemRepository.findByTagInCollection(collectionId, itemTag);
    // }
    
}
