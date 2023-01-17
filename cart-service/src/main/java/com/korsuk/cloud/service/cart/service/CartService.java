package com.korsuk.cloud.service.cart.service;


import com.korsuk.cloud.service.cart.exceptions.NovelNotFoundException;
import com.korsuk.cloud.service.cart.integrations.NovelsServiceIntegration;
import com.korsuk.cloud.service.cart.models.Cart;
import com.korsuk.core.NovelDto;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final NovelsServiceIntegration novelsServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String cartKey) {
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public void addNovelInCart(String cartKey, Long novelId) {
        NovelDto novelDto = novelsServiceIntegration.findById(novelId).orElseThrow(() -> new NovelNotFoundException("Novel not found with id:" + novelId));
        execute(cartKey, c -> {
            c.addInCart(novelDto);
        });
    }

    public void clearCart(String cartKey) {
        execute(cartKey, Cart::clearCart);
    }

    public void deleteFromCart(String cartKey, Long novelId) {
        execute(cartKey, c -> c.removeNovel(novelId));
    }

    public void decrementItem(String cartKey, Long novelId) {
        execute(cartKey, c -> c.decrement(novelId));
    }

    public void merge(String userCartKey, String guestCartKey) {
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}
