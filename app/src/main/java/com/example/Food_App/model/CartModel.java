package com.example.Food_App.model;

public class CartModel {

    String name;
    String price;
    Integer imageUrl;

    public CartModel(String name, String price, Integer imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl=imageUrl;

    }

    String rating;


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
