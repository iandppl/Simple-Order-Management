package com.SimpleOrderManagementApp.OrderManagement.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_seq",allocationSize = 1)
    private Integer id;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "WEIGHT", nullable = false)
    private Double weight;

    @Column(name = "SIZE")
    private String size;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderQuantity> orderQuantityList;

    public Item() {
    }

    public Item(Integer id, Double price, Double weight, String size, List<OrderQuantity> orderQuantityList) {
        this.id = id;
        this.price = price;
        this.weight = weight;
        this.size = size;
        this.orderQuantityList = orderQuantityList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<OrderQuantity> getOrderQuantityList() {
        return orderQuantityList;
    }

    public void setOrderQuantityList(List<OrderQuantity> orderQuantityList) {
        this.orderQuantityList = orderQuantityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(price, item.price) && Objects.equals(weight, item.weight) && Objects.equals(size, item.size);
    }
}