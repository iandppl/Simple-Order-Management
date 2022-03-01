package com.SimpleOrderManagementApp.OrderManagement.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderQuantity")
public class OrderQuantity {

    @Id
    @Column(name = "ORDER_QUANTITY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_quantity_id_seq")
    @SequenceGenerator(name = "order_quantity_id_seq",allocationSize = 1)
    private Integer orderQuantityId;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID",nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID",nullable = false)
    private Order order;

    public OrderQuantity() {
    }

    public OrderQuantity(Integer orderQuantityId, Integer quantity, Item item, Order order) {
        this.orderQuantityId = orderQuantityId;
        this.quantity = quantity;
        this.item = item;
        this.order = order;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderQuantity that = (OrderQuantity) o;
        return Objects.equals(orderQuantityId, that.orderQuantityId) && Objects.equals(item, that.item) && Objects.equals(order, that.order);
    }
}
