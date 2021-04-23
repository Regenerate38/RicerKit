package com.example.ricerkit.Models;

public class CatOwl {
    private String catslideName;
    private String catslideUrl;

    public CatOwl(String catslideName, String catslideUrl) {
        this.catslideName = catslideName;
        this.catslideUrl = catslideUrl;
    }

    public String getCatslideName() {
        return catslideName;
    }

    public void setCatslideName(String catslideName) {
        this.catslideName = catslideName;
    }

    public String getCatslideUrl() {
        return catslideUrl;
    }

    public void setCatslideUrl(String catslideUrl) {
        this.catslideUrl = catslideUrl;
    }
}
