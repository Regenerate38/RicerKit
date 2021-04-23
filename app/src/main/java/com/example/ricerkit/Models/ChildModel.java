package com.example.ricerkit.Models;

public class ChildModel {
    private String hero_image;
    private String movieName;
    private String productUrl;

    public ChildModel(String hero_image, String movieName, String productUrl){
        this.hero_image = hero_image;
        this.movieName = movieName;
        this.productUrl = productUrl;
    }
    public String getHeroImage() {
        return hero_image;
    }
    public String getMovieName() {
        return movieName;
    }
    public String getProductUrl() {
        return productUrl;
    }
}
