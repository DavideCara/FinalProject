package com.mycoll.mycollections.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycoll.mycollections.models.Collection;
import com.mycoll.mycollections.models.Item;
import com.mycoll.mycollections.models.User;
import com.mycoll.mycollections.services.CollectionService;
import com.mycoll.mycollections.services.ItemService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/itemsOfCollection")
    public String itemHome(@RequestParam (name="collectionId", defaultValue="1")Long collectionId, Model model, HttpSession session) {
        List<Item> items = itemService.getItemsByCollection(collectionId);
            model.addAttribute("items", items);
            session.setAttribute("collectionId", collectionId);
            System.out.println(session.getAttribute("collectionId"));
            return "item.html";
        //}
    }

    // ritorno alla pagina degli item dovrei visualizzare la lista?
    //@GetMapping("/itemHome/{collectionId}")
    //public String itemHome(Model model, @PathVariable Long collectionId) {
    //    List<Item> items = itemService.getItemsByCollection(collectionId);
    //    model.addAttribute("items", items);
    //    return "item.html";
    //}
    
    @GetMapping("/items")
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @PostMapping("/addItem")
    public String addItem(@RequestParam (name="name") String name, @RequestParam (name="description") String description, @RequestParam (name="image") String image, 
    @RequestParam (name="number") int number, @RequestParam (name = "date") Date date,
    HttpSession session){    
        Item item = new Item();
        Long id=(Long)session.getAttribute("collectionId");
        item.setName(name);
        item.setDescription(description);        
        item.setImage(image);
        item.setNumber(number);
        item.setDate(date);
        Collection collection = collectionService.getCollectionById(id);
        if (collection != null) {
            item.setCollection(collection);
            itemService.addItem(item); 
        } else {            
        return "errorPage.html"; 
        }        
        System.out.println("collectionId: " + id);       
        return "redirect:/itemsOfCollection?collectionId=" + id;
    }
    
    @GetMapping("/returnToCollection")
    public String returnToCollection(HttpSession session){
        User user = (User)session.getAttribute("user");


        return "redirect:/profile/" + user.getUsername();
    }

    @GetMapping("/itemID/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/item/{name}")
    public Item getItemByName(@PathVariable String name) {
        return itemService.getItemByName(name);
    }

    @PostMapping("/updateItem")
    public String updateItem(@ModelAttribute Item item, HttpSession session) {
        Long collectionId=(Long)session.getAttribute("collectionId");
        itemService.updateItem(item);
        return "redirect:/itemsOfCollection?collectionId=" + collectionId;
    }

    // @GetMapping("/items/{itemTag}")
    // public String getItemsByItemTag(@PathVariable String itemTag){
    //     itemService.getItemsByItemTag(itemTag);
    //     return "redirect:/itemHome/{collectionId}";//da cambiare pagina html
    // }
    
    // @GetMapping("/collection/{collectionId}/items/{itemTag}")
    // public List<Item> getItemsByTagInCollection(@PathVariable Long collectionId, @PathVariable String itemTag){
    //     return itemService.getItemsByTagInCollection(collectionId, itemTag);
    // }

    //@DeleteMapping("/deleteItem/{id}")
    //public String deleteItem(@PathVariable Long id) {
    //    itemService.deleteItem(id);
    //    return "Item removed !! " + id;
    //}
    
    
    @PostMapping("/deleteItem")
    public String deleteItem(@RequestParam(name="id",defaultValue = "0") Long id, Model model, HttpSession session){
        if (id == 0) {
            model.addAttribute("error","Errore nell'eliminazione dell'oggetto associato all'id");
            return "errorPage.html";            
        }
        Long collectionId=(Long)session.getAttribute("collectionId");
        itemService.deleteItem(id);
        return "redirect:/itemsOfCollection?collectionId=" + collectionId;
    }

}
