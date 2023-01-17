package com.korsuk.core;


import java.util.Objects;


public class NovelDto {

    private Long id;

    private String title;

    private AuthorDto author;

    private Double rating;

    private Double price;

    private Integer count;

    public NovelDto(Long id, String title, AuthorDto authorDto, Double rating, Double price) {
        this.id = id;
        this.title = title;
        this.author = authorDto;
        this.rating = rating;
        this.price = price;
    }

    public NovelDto() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        NovelDto novelDto = (NovelDto) obj;
        return Objects.equals(id, novelDto.id);
    }
    @Override
    public int hashCode() {
        final int someHash = 49;
        return (id == null ? 0 : id.hashCode()) + someHash;
    }
}