package com.example.Food_App.model;

public class PopularFood {

    String name;
    String price;
    String rating;
    Integer imageUrl;

    public PopularFood(String name, String price, Integer imageUrl, String rating) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.rating= rating;
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

    public String getRating(){return rating;}

    public void setRating(String rating){this.rating=rating;}
}
