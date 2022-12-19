package com.korsuk.cloud.service.cart.models;

import com.korsuk.core.AuthorDto;
import com.korsuk.core.NovelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long novelId;
    private String novelTitle;
    private AuthorDto authorDto;
    private int quantity;
    private double pricePerProduct;
    private double price;
    private double rating;

    public CartItem(NovelDto novelDto) {
        this.novelId = novelDto.getId();
        this.novelTitle = novelDto.getTitle();
        this.authorDto = new AuthorDto(novelDto.getAuthor().getId(), novelDto.getAuthor().getName(), novelDto.getAuthor().getSurname());
        this.quantity = 1;
        this.pricePerProduct = novelDto.getPrice();
        this.price = novelDto.getPrice();
        this.rating = novelDto.getRating();
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }
}
