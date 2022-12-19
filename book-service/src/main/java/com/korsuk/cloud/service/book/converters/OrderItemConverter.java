package com.korsuk.cloud.service.book.converters;

import com.korsuk.cart.CartItemDto;
import com.korsuk.cloud.service.book.entities.OrderItem;
import com.korsuk.cloud.service.book.services.NovelService;
import com.korsuk.core.OrderItemDto;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {

    private final NovelService novelService;
    private final NovelConverter novelConverter;

    private final OrderConverter orderConverter;

    public OrderItem dtoToEntity(CartItemDto orderItemDto) {
        return new OrderItem(
                novelService.getNovelById(orderItemDto.getNovelId()).orElseThrow(() -> new ResourceNotFoundException("Novel not found")),
                orderItemDto.getQuantity(),
                orderItemDto.getPricePerProduct(),
                orderItemDto.getPrice()
        );
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                novelConverter.entityToDto(orderItem.getNovel()),
                orderConverter.entityToDto(orderItem.getOrder()),
                orderItem.getQuantity(),
                orderItem.getPricePerProduct(),
                orderItem.getPrice());
    }
}
