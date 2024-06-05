package com.mycoll.mycollections.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycoll.mycollections.models.User;
import com.mycoll.mycollections.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppController implements ErrorController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/menu")
    public String menu(HttpSession session) {
        if (session.getAttribute("logged") == null) {
            return "redirect:/";            
        }else{
            return "menu.html";
        }
    }

     /* @GetMapping("/profile")
     public String home() {
         return "profile.html";
     } */

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("logged") == null) {
            return "login.html";            
        }else{
            Object usr = session.getAttribute("user");
            User user = (User)usr;
            return "redirect:/profile/" + user.getUsername();
        }
    }

    @GetMapping("/register")
    public String registerUser(Model model, HttpSession session) {
        if (session.getAttribute("logged") == null) {
            model.addAttribute("user", new User());
            return "sign_up.html";            
        }else{
            Object usr = session.getAttribute("user");
            User user = (User)usr;
            return "redirect:/profile/" + user.getUsername();
        }
        
    }

    @GetMapping("/profile")
    public String myProfile(HttpSession session) {
        if (session.getAttribute("logged") == null) {
            return "redirect:/";            
        }else{
            Object usr = session.getAttribute("user");
            User user = (User)usr;
            return "redirect:/profile/" + user.getUsername();
        }
    }

    @GetMapping("/")
    public String homePage() {
        return "home.html";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "redirect:/";
    }
    

    @PostMapping("/userLogin")
    public String userLogin(
        @RequestParam("l-username") String username,
        @RequestParam("l-password") String password,
        Model model,
        HttpSession session) {
        User loggedInUser = userService.findByUsernameAndPassword(username, password);
        
        //veirfico se l'utente logged è stato trovato oppure se è null
        if (loggedInUser == null) {
            model.addAttribute("error", "Credenziali non valide");
            return "login.html";            
        }else if (loggedInUser.getPassword().equals(password)) {
            //in questo caso i dati sono corretti quindi posso salvare l'utente in sessione
            session.setAttribute("logged", "ok");
            session.setAttribute("user", loggedInUser);
            return "redirect:/profile/" + loggedInUser.getUsername();
        }  else {
            model.addAttribute("error", "Credenziali non valide");
            return "login.html";
        }
    }

    //logout
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("logged", null);
        session.setAttribute("user", null);
        return "redirect:/";
    }
}
