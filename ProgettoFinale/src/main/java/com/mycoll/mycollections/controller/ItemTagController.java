package com.mycoll.mycollections.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycoll.mycollections.services.ItemTagService;

@Controller
public class ItemTagController {
    @Autowired
    private ItemTagService itemTagService;

    // Post
    // @PostMapping("/addItemTag")
    // public ItemTag addItemTag(@ModelAttribute ItemTag itemTag) throws Exception{
    //     int count = itemTagService.countTagsForItem(itemTag);
    //     if (count <3) {
    //         return itemTagService.addItemTag(itemTag);
    //     }else {
    //         throw new Exception("Cannot add more than 3 tags to an item");
    //         //da gestire anche con il front end
    //     }  
    
    // }

    // Delete
    @DeleteMapping("/deleteItemTag/{id}")
    public String deleteItemTag(@PathVariable Long id) {
        itemTagService.deleteItemTag(id);
        return "ItemTag removed !! " + id;
    }

    

}
