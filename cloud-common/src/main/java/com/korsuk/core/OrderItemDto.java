package com.korsuk.core;

public class OrderItemDto {

    private Long id;

    private NovelDto novelDto;

    private Long orderId;

    private Integer quantity;

    private Double pricePerProduct;

    private Double price;

    public OrderItemDto() {
    }

    public OrderItemDto(Long id, NovelDto novelDto, Long orderId, Integer quantity, Double pricePerProduct, Double price) {
        this.id = id;
        this.novelDto = novelDto;
        this.orderId = orderId;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NovelDto getNovelDto() {
        return novelDto;
    }

    public void setNovelDto(NovelDto novelDto) {
        this.novelDto = novelDto;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(Double pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
