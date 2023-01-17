package com.korsuk.dto;

public class NovelToSave {

    private String title;

    private String authorName;

    private String authorSurname;

    private Double rating;

    private Double price;

    public NovelToSave() {
    }

    public NovelToSave(String title, String authorName, String authorSurname, Double rating, Double price) {
        this.title = title;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.rating = rating;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

