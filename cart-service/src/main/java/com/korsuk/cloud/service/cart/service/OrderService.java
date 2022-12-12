package com.korsuk.cloud.service.cart.service;


import com.korsuk.cloud.service.cart.converters.OrderConverter;
import com.korsuk.cloud.service.cart.entities.Order;
import com.korsuk.cloud.service.cart.entities.OrderItem;
import com.korsuk.cloud.service.cart.myCart.CartNotEntity;
import com.korsuk.cloud.service.cart.repositories.OrderRepository;
import com.korsuk.dto.OrderDetailsDto;
import com.korsuk.dto.OrderDto;
import com.korsuk.dto.OrderItemDto;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderConverter orderConverter;

    private final NovelService novelService;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {
        String cartKey = cartService.getCartUuidFromSuffix(username);
        CartNotEntity currentCart = cartService.getCurrentCart(cartKey);

        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());

        List<OrderItem> items = currentCart.getNovelsInCart().stream()
                .map(orderItemDto -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(orderItemDto.getQuantity());
                    orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
                    orderItem.setPrice(orderItemDto.getPrice());
                    orderItem.setNovel(novelService.getNovelById(orderItemDto.getNovelId())
                            .orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + orderItemDto.getNovelId())));
                    return orderItem;
                })
                .collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        cartService.clearCart(cartKey);
    }

    public List<OrderDto> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
