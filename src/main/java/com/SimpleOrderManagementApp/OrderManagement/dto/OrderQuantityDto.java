package com.SimpleOrderManagementApp.OrderManagement.dto;


import com.sun.istack.NotNull;

import java.util.Objects;

public class OrderQuantityDto {
    private Integer orderQuantityId;

    @NotNull
    private Integer quantity;

    @NotNull
    private ItemDto itemDto;

    public OrderQuantityDto() {
    }

    public OrderQuantityDto(Integer orderQuantityId, Integer quantity, ItemDto itemDto) {
        this.orderQuantityId = orderQuantityId;
        this.quantity = quantity;
        this.itemDto = itemDto;
    }

    public Integer getOrderQuantityId() {
        return orderQuantityId;
    }

    public void setOrderQuantityId(Integer orderQuantityId) {
        this.orderQuantityId = orderQuantityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderQuantityDto that = (OrderQuantityDto) o;
        return Objects.equals(orderQuantityId, that.orderQuantityId) && Objects.equals(quantity, that.quantity) && Objects.equals(itemDto, that.itemDto);
    }
}
