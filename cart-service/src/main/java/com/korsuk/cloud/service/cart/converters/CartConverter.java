package com.korsuk.cloud.service.cart.converters;

import com.korsuk.cart.CartDto;
import com.korsuk.cart.CartItemDto;
import com.korsuk.cloud.service.cart.models.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartConverter {

    public CartDto modelToDto(Cart cart) {
        List<CartItemDto> cartItemDtos = cart.getNovelsInCart().stream().map(it ->
                new CartItemDto(it.getNovelId(), it.getNovelTitle(), it.getAuthorDto(), it.getQuantity(),
                        it.getPricePerProduct(), it.getPrice(), it.getRating())).collect(Collectors.toList());

        CartDto cartDto = new CartDto(cartItemDtos, cart.getTotalPrice());
        return cartDto;
    }
}
