package com.korsuk.cloud.service.book.services;

import com.korsuk.cart.CartDto;
import com.korsuk.cloud.service.book.converters.OrderConverter;
import com.korsuk.cloud.service.book.entities.Order;
import com.korsuk.cloud.service.book.entities.OrderItem;
import com.korsuk.cloud.service.book.intergations.CartServiceIntegration;
import com.korsuk.cloud.service.book.repository.OrderRepository;
import com.korsuk.core.OrderDetailsDto;
import com.korsuk.core.OrderDto;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderConverter orderConverter;
    private final NovelService novelService;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {

        CartDto currentCart = cartServiceIntegration.getUserCart(username);

        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());

        List<OrderItem> items = currentCart.getNovelsInCart().stream()
                .map(cartItemDto -> {
                   return OrderItem.builder()
                            .order(order)
                            .quantity(cartItemDto.getQuantity())
                            .pricePerProduct(cartItemDto.getPricePerProduct())
                            .price(cartItemDto.getPrice())
                            .novel(novelService.getNovelById(cartItemDto.getNovelId()).orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + cartItemDto.getNovelId())))
                            .build();
//                    OrderItem orderItem = new OrderItem();
//                    orderItem.setOrder(order);
//                    orderItem.setQuantity(cartItemDto.getQuantity());
//                    orderItem.setPricePerProduct(cartItemDto.getPricePerProduct());
//                    orderItem.setPrice(cartItemDto.getPrice());
//                    orderItem.setNovel(novelService.getNovelById(cartItemDto.getNovelId())
//                            .orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + cartItemDto.getNovelId())));
//                    return orderItem;
                })
                .collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        cartServiceIntegration.clearUserCart(username);
    }

    public List<OrderDto> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }
}
