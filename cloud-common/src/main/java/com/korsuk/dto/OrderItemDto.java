package com.korsuk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long novelId;
    private String novelTitle;
    private AuthorDto authorDto;
    private int quantity;
    private double pricePerProduct;
    private double price;
    private double rating;

    public OrderItemDto(NovelDto novel) {
        this.novelId = novel.getId();
        this.novelTitle = novel.getTitle();
        this.authorDto = new AuthorDto(novel.getAuthor().getId(), novel.getAuthor().getName(), novel.getAuthor().getSurname());
        this.quantity = 1;
        this.pricePerProduct = novel.getPrice();
        this.price = novel.getPrice();
        this.rating = novel.getRating();
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }
}
