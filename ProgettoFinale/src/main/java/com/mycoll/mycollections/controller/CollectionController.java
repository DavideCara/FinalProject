package com.mycoll.mycollections.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycoll.mycollections.models.Collection;
import com.mycoll.mycollections.models.CollectionTag;
import com.mycoll.mycollections.models.User;
import com.mycoll.mycollections.repository.CollectionRepository;
import com.mycoll.mycollections.repository.CollectionTagRepository;
import com.mycoll.mycollections.services.CollectionService;
import com.mycoll.mycollections.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class CollectionController {
    @Autowired
    private CollectionRepository repository;
    
    @Autowired
    private CollectionTagRepository tagRepository;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private UserService userService;
    
    @GetMapping("/profile/{username}")
    public String getCollections(Model model, HttpSession session, @PathVariable String username) {
        Long userId = userService.getUserByUsername(username).getId();
        List<Collection> ris = userService.getCollectionsByUserId(userId, session); 
        User loggedUser = (User)session.getAttribute("user");
        if (userId == loggedUser.getId()) {
            model.addAttribute("isLoggedUser", true);
        } else {
            model.addAttribute("isLoggedUser", false);
        }
        model.addAttribute("collections", ris);
        model.addAttribute("username", username);
       /*  session.getAttribute("user");  */
        return "profile.html";
    }
    
    // Post
    //@PostMapping("/addCollection")
    //public String addCollection(@ModelAttribute Collection collection, HttpSession session) {
    //    Object user = session.getAttribute("user");
    //    User u = (User) user;
    //    collection.setUser(u);
    //    return "redirect:/profile";
    //}

    @PostMapping("/addCollection")
    public String addCollection(@RequestParam (name="name") String name, @RequestParam (name="image") String image, 
    @RequestParam (name="isWishlist",required = false) boolean isWishlist, @RequestParam (name="isPublic",required = false) boolean isPublic, HttpSession session) {
        Object user = session.getAttribute("user");
        User u = (User) user;
        Collection collection = new Collection();
        collection.setName(name);
        collection.setImage(image);
        collection.setWishlist(isWishlist);
        collection.setPublic(isPublic);
        collection.setUser(u);

        repository.save(collection);
        return "redirect:/profile/" + u.getUsername();
    }     

    // Get
    @GetMapping("/collectionID/{id}")
    public Collection getCollectionById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/collection/{name}")
    public Collection getCollectionByName(@PathVariable String name) {
        return repository.findByName(name);
    }


    @GetMapping("/collections")
    public List<Collection> getCollections() {
        return repository.findAll();
    }

    // Put
    @PostMapping("/updateCollection")
    public String updateCollection(@RequestParam (name="id") Long id, @RequestParam (name="name") String name, @RequestParam (name="image") String image, 
    @RequestParam (name="isWishlist", required = false) boolean isWishlist, @RequestParam (name="isPublic", required = false) boolean isPublic, HttpSession session) {
        Collection existingCollection = repository.findById(id).orElse(null);
        existingCollection.setName(name);
        existingCollection.setImage(image);
        existingCollection.setWishlist(isWishlist);
        existingCollection.setPublic(isPublic);
        repository.save(existingCollection);
        User user = (User)session.getAttribute("user");
        return "redirect:/profile/" + user.getUsername();
    }

    @PutMapping("/collection/{collecId}/addTag")
    public Collection addTagToCollection(@PathVariable Long collecId, @RequestBody CollectionTag collectionTag) {
        Collection existingCollection = repository.findById(collecId).orElse(null);
        CollectionTag collectionTagToAdd = tagRepository.save(collectionTag);
        existingCollection.addTagToSelf(collectionTagToAdd);
        return repository.save(existingCollection);
    }

    @PostMapping("/delete-Collection")
    public String deleteCollection(@RequestParam (name="collectionId", defaultValue="0")
    Long collectionId, Model model, HttpSession session){
        if (collectionId == 0) {
            model.addAttribute("error","Errore nell'eliminazione dell'alimento");
            return "errorPage.html";
        }
        collectionService.deleteCollection(collectionId);
        User user = (User)session.getAttribute("user");
        return "redirect:/profile/" + user.getUsername();
    } 
}