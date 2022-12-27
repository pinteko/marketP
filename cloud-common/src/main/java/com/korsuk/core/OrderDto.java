package com.korsuk.core;

import com.korsuk.cart.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private Double totalPrice;
    private String address;
    private String phone;
}
