package com.korsuk.cloud.service.cart.test;

import com.korsuk.cloud.service.cart.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartTest {

    @Autowired
    private CartService cartService;


    @BeforeEach
    public void initCart() {
        cartService.clearCart("test_cart");
    }

    @Test
    public void addToCartTest() {
//        AuthorDto authorDto = new AuthorDto(7L,"Nasik", "Zhuk");
//        NovelDto novelDto = new NovelDto(15L, "Kimmi", authorDto, 4.56, 12.43);

        cartService.addNovelInCart("test_cart", 9L);
        cartService.addNovelInCart("test_cart", 9L);
        cartService.addNovelInCart("test_cart", 9L);

        Assertions.assertEquals(1, cartService.getCurrentCart("test_cart").getNovelsInCart().size());

    }
}
