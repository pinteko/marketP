package com.korsuk.cloud.service.cart.converters;



import com.korsuk.cloud.service.cart.entities.OrderItem;
import com.korsuk.cloud.service.cart.service.NovelService;
import com.korsuk.dto.OrderItemDto;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {

    private final NovelService novelService;
    private final NovelConverter novelConverter;

    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
        return new OrderItem(
                novelService.getNovelById(orderItemDto.getNovelId()).orElseThrow(() -> new ResourceNotFoundException("Novel not found")),
                orderItemDto.getQuantity(),
                orderItemDto.getPricePerProduct(),
                orderItemDto.getPrice()
        );
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(novelConverter.entityToDto(orderItem.getNovel()));
    }
}
