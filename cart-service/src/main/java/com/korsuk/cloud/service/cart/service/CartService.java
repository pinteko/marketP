package com.korsuk.cloud.service.cart.service;


import com.korsuk.cloud.service.cart.converters.NovelConverter;
import com.korsuk.cloud.service.cart.entities.Novel;
import com.korsuk.cloud.service.cart.myCart.CartNotEntity;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final NovelService novelService;

    private final NovelConverter novelConverter;
    private final RedisTemplate<String, Object> redisTemplate;
    //    private CartNotEntity cart;
    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }


//    @PostConstruct
//    public void init() {
//        cart = new CartNotEntity();
//    }

    public CartNotEntity getCurrentCart(String cartKey) {
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new CartNotEntity());
        }
        return (CartNotEntity) redisTemplate.opsForValue().get(cartKey);
    }

    public void addNovelInCart(String cartKey, Long novelId) {
            Novel novel = novelService.getNovelById(novelId).orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + novelId));
        execute(cartKey, c -> {
            c.addInCart(novelConverter.entityToDto(novel));
        });
    }

    public void clearCart(String cartKey) {
        execute(cartKey, CartNotEntity::clearCart);
    }

    public void deleteFromCart(String cartKey, Long novelId) {
        execute(cartKey, c -> c.removeNovel(novelId));
    }

    public void decrementItem(String cartKey, Long novelId) {
        execute(cartKey, c -> c.decrement(novelId));
    }

    public void merge(String userCartKey, String guestCartKey) {
        CartNotEntity guestCart = getCurrentCart(guestCartKey);
        CartNotEntity userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }

    private void execute(String cartKey, Consumer<CartNotEntity> action) {
        CartNotEntity cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void updateCart(String cartKey, CartNotEntity cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}
