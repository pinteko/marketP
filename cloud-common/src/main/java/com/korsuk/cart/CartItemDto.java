package com.korsuk.cart;

import com.korsuk.core.AuthorDto;
import com.korsuk.core.NovelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CartItemDto {
    private final Long novelId;
    private final String novelTitle;
    private final AuthorDto authorDto;
    private final int quantity;
    private final double pricePerProduct;
    private final double price;
    private final double rating;

    public CartItemDto(Long novelId, String novelTitle, AuthorDto authorDto, int quantity, double pricePerProduct, double price, double rating) {
        this.novelId = novelId;
        this.novelTitle = novelTitle;
        this.authorDto = authorDto;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
        this.rating = rating;
    }

    public Long getNovelId() {
        return novelId;
    }

    public String getNovelTitle() {
        return novelTitle;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerProduct() {
        return pricePerProduct;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
}
