package com.korsuk.cloud.service.book.converters;

import com.korsuk.cart.CartItemDto;
import com.korsuk.cloud.service.book.entities.OrderItem;
import com.korsuk.cloud.service.book.services.NovelService;
import com.korsuk.cloud.service.book.services.OrderService;
import com.korsuk.core.OrderItemDto;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final NovelConverter novelConverter;

    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
//        return new OrderItem(
//                novelService.getNovelById(orderItemDto.getNovelDto().getId()).orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + orderItemDto.getNovelDto().getId())),
//                orderService.findOrderById(orderItemDto.getOrderId()).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderItemDto.getOrderId())),
//                orderItemDto.getQuantity(),
//                orderItemDto.getPricePerProduct(),
//                orderItemDto.getPrice()
//        );
        throw new UnsupportedOperationException();
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                novelConverter.entityToDto(orderItem.getNovel()),
                orderItem.getOrder().getId(),
                orderItem.getQuantity(),
                orderItem.getPricePerProduct(),
                orderItem.getPrice());
    }
}
