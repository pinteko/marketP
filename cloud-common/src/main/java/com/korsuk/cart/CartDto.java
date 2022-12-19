package com.korsuk.cart;

import lombok.AllArgsConstructor;

import java.util.List;


public class CartDto {

    private final List<CartItemDto> novelsInCart;
    private final double totalPrice;

    public CartDto(List<CartItemDto> novelsInCart, double totalPrice) {
        this.novelsInCart = novelsInCart;
        this.totalPrice = totalPrice;
    }

    public List<CartItemDto> getNovelsInCart() {
        return novelsInCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
