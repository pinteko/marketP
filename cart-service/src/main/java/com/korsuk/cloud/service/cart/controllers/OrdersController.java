package com.korsuk.cloud.service.cart.controllers;


import com.korsuk.cloud.service.cart.service.OrderService;
import com.korsuk.dto.OrderDetailsDto;
import com.korsuk.dto.OrderDto;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final RestTemplate restTemplate;

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
