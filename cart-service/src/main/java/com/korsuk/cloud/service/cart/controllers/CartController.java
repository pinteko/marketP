package com.korsuk.cloud.service.cart.controllers;

import com.korsuk.cloud.service.cart.myCart.CartNotEntity;
import com.korsuk.cloud.service.cart.service.CartService;
import com.korsuk.dto.StringResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {

    private final CartService cartService;


//    @GetMapping()
//    public List<NovelDto> getNovels() {
//        return cartService.novelsInCart();
//    }

    @GetMapping("/{uuid}")
    public CartNotEntity getCurrentCart(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{novelId}")
    public void add(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long novelId) {
        cartService.addNovelInCart(getCurrentCartUuid(username, uuid), novelId);
    }

    @GetMapping("/{uuid}/decrement/{novelId}")
    public void decrement(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long novelId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), novelId);
    }

    @DeleteMapping("/{uuid}/remove/{novelId}")
    public void remove(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long novelId) {
        cartService.deleteFromCart(getCurrentCartUuid(username, uuid), novelId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void merge(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }

}
