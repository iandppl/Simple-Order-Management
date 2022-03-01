package com.SimpleOrderManagementApp.OrderManagement.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customerOrder")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_id_seq")
    @SequenceGenerator(name = "order_id_seq",allocationSize = 1)
    private Integer orderId;

    @Column(name = "CUSTOMER")
    private String customer;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderQuantity> orderQuantityList;

    public Order() {
    }

    public Order(Integer orderId, String customer, String paymentType, Date createdDate, String createdBy, Date updatedDate, String updatedBy, List<OrderQuantity> orderQuantityList) {
        this.orderId = orderId;
        this.customer = customer;
        this.paymentType = paymentType;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.orderQuantityList = orderQuantityList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<OrderQuantity> getOrderQuantityList() {
        return orderQuantityList;
    }

    public void setOrderQuantityList(List<OrderQuantity> orderQuantityList) {
        this.orderQuantityList = orderQuantityList;
    }
}
