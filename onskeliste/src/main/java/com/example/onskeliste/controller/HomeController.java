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
    public String showStart(Model model) {
        return "start";
    }


    @GetMapping("/list")
    public String showList(Model model) {
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
        Product newProduct = new Product();
        newProduct.setPrice(newPrice);
        newProduct.setName(newName);
        newProduct.setAmount(newAmount);
        newProduct.setLink(newLink);

        productRepository.addProduct(newProduct);

        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") int updateId, Model model) {
        Product updateProduct = productRepository.findProductById(updateId);

        model.addAttribute("product", updateProduct);

        return "update";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam("product-name") String updateName,
                                @RequestParam("product-price") double updatePrice,
                                @RequestParam("product-amount") int updateAmount,
                                @RequestParam("product-link") String updateLink,
                                @RequestParam("product-id") int updateId) {
        Product updateProduct = new Product(updateId, updateName, updatePrice, updateAmount, updateLink);

        productRepository.updateProduct(updateProduct);

        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productRepository.deleteById(id);

        return "redirect:/list";
    }

    @GetMapping("/reserve")
    public String showReserve(Model model) {
        model.addAttribute("products", productRepository.getAll());
        return "reserve";
    }

    @GetMapping("/reserve/{id}")
    public String reserveButtonYes(@PathVariable("id") int id, Model model) {

        Product updateReserve = productRepository.findProductById(id);

        productRepository.updateReservedYes(id);

        model.addAttribute("product", updateReserve);

        return "redirect:/reserve";
    }

    @GetMapping("/reservation")
    public String showReservation(Model model) {
        return "reservation";
    }
    


}