package com.example.sayargyi;



public class FoodItem {
    private int id;
    private String name;
    private String description;
    private double price;


    private int quantity;
    private double totalValue;

    public FoodItem(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public FoodItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public FoodItem(String name,  double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    public FoodItem() {

    }

    // Getters and setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity){ this.quantity = quantity;}
    public int getQuantity() {
        return quantity;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }



}

