package com.example.ricerkit.Models;

public class SearchModel {
    private String hero_image;
    private String movieName;
    private String productUrl;
    private String authorname;
    private String categoryName;

    public SearchModel(String hero_image, String movieName, String productUrl, String authorname, String categoryName) {
        this.hero_image = hero_image;
        this.movieName = movieName;
        this.productUrl = productUrl;
        this.authorname = authorname;
        this.categoryName = categoryName;
    }

    public String getHero_image() {
        return hero_image;
    }

    public void setHero_image(String hero_image) {
        this.hero_image = hero_image;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
