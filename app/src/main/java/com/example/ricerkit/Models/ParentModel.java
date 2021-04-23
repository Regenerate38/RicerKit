package com.example.ricerkit.Models;

public class ParentModel {
    private String movieCategory;

    public ParentModel(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String movieCategory() {
        return movieCategory;
    }
}