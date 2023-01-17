package com.korsuk.cart;

import java.util.List;


public class CartDto {

    private List<CartItemDto> novelsInCart;
    private double totalPrice;

    public CartDto(List<CartItemDto> novelsInCart, double totalPrice) {
        this.novelsInCart = novelsInCart;
        this.totalPrice = totalPrice;
    }

    public CartDto() {

    }

    public List<CartItemDto> getNovelsInCart() {
        return novelsInCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
