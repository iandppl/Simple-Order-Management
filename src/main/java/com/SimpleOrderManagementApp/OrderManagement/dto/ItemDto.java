package com.SimpleOrderManagementApp.OrderManagement.dto;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import java.util.Objects;

public class ItemDto {

    private Integer id;
    private String size;

    @NotNull
    private Double price;

    @NotNull
    private Double weight;

    public ItemDto() {
    }

    public ItemDto(Integer id, String size, Double price, Double weight) {
        this.id = id;
        this.size = size;
        this.price = price;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(id, itemDto.id) && Objects.equals(size, itemDto.size) && Objects.equals(price, itemDto.price) && Objects.equals(weight, itemDto.weight);
    }
}
