package com.example.onskeliste.controller;

import com.example.onskeliste.model.User;
import com.example.onskeliste.repository.ProductRepository;
import com.example.onskeliste.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    public UserController(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginRequest(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     Model model) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            model.addAttribute("error", "Username not found");
            return "login";
        } else if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Incorrect password");
            return "login";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("products", productRepository.getAll());
            return "list";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegisterRequest(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            model.addAttribute("error", "Username already taken");
            return "register";
        } else {
            userRepository.addUser(new User(username, password));
            return "redirect:/login";
        }
    }
}