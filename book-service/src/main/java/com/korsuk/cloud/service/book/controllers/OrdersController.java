package com.korsuk.cloud.service.book.controllers;


import com.korsuk.cloud.service.book.services.OrderService;
import com.korsuk.core.OrderDetailsDto;
import com.korsuk.core.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {


   private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader(required = false) String username, @RequestBody OrderDetailsDto orderDetailsDto) {
        orderService.createOrder(username, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader(required = false) String username) {
       return orderService.findOrdersByUsername(username);
    }
}
