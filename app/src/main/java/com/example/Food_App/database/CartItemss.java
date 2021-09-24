package com.example.Food_App.database;

public class CartItemss {
    private  int id;
    private String name;
    private String price;

    public CartItemss(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public CartItemss(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public CartItemss() {

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
