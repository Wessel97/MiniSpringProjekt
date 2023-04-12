package com.example.onskeliste.model;

public class Product {

    private int id;
    private String name;
    private double price;
    private int amount;
    private String link;

    public Product() {
    }

    public Product(int id, String name, double price, int amount, String link) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.link = link;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", amount=" +
                amount + ", link=" + link + '}';
    }
}