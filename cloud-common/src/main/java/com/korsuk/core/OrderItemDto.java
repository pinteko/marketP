package com.korsuk.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;

    private NovelDto novelDto;

    private Long orderId;

    private Integer quantity;

    private Double pricePerProduct;

    private Double price;

}
