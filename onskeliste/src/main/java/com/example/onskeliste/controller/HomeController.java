package com.example.onskeliste.controller;

import com.example.onskeliste.model.Product;
import com.example.onskeliste.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String start(Model model) {
        return "start";
    }


    @GetMapping("/list")
    public String index(Model model) {
        model.addAttribute("products", productRepository.getAll());
        return "list";
    }

    //fra anchor i index
    @GetMapping("/create")
    public String showCreate() {
        //vis create-siden
        return "create";
    }

    //fra form action
    @PostMapping("/create")
    public String createProduct(@RequestParam("product-name") String newName,
                                @RequestParam("product-price") double newPrice,
                                @RequestParam("product-amount") int newAmount,
                                @RequestParam("product-link") String newLink) {
        //lave et nyt Product
        Product newProduct = new Product();
        newProduct.setPrice(newPrice);
        newProduct.setName(newName);
        newProduct.setAmount(newAmount);
        newProduct.setLink(newLink);

        //gem nyt produkt
        productRepository.addProduct(newProduct);

        //tilbage til produktlisten
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") int updateId, Model model) {
        //find produkt med id=updateId i databasen
        Product updateProduct = productRepository.findProductById(updateId);

        //tilføj produkt til viewmodel, så det kan bruges i Thymeleaf
        model.addAttribute("product", updateProduct);

        //fortæl Spring hvilken HTML-side, der skal vises
        return "update";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam("product-name") String updateName,
                                @RequestParam("product-price") double updatePrice,
                                @RequestParam("product-amount") int updateAmount,
                                @RequestParam("product-link") String updateLink,
                                @RequestParam("product-id") int updateId) {
        //lav produkt ud fra parametre
        Product updateProduct = new Product(updateId, updateName, updatePrice, updateAmount, updateLink);

        //kald opdater i repository
        productRepository.updateProduct(updateProduct);

        //rediriger til oversigtssiden
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        //slet fra repository
        productRepository.deleteById(id);

        //returner til index-siden
        return "redirect:/list";
    }

    @GetMapping("/reserve")
    public String showReserve(Model model) {
        model.addAttribute("products", productRepository.getAll());
        return "reserve";
    }

    @GetMapping("/reservation")
    public String showReservation(Model model) {
        return "reservation";
    }



}