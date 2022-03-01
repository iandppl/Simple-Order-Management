package com.SimpleOrderManagementApp.OrderManagement.dto;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private Integer orderId;

    @NotNull
    private String customer;

    @NotNull
    private String paymentType;

    private Date createdDate;

    private String createdBy;

    private Date updatedDate;

    private String updatedBy;

    @NotNull
    private List<OrderQuantityDto> orderQuantityDtoList;

    public OrderDto() {
    }

    public OrderDto(
        Integer orderId, String customer, String paymentType, Date createdDate,
        String createdBy, Date updatedDate, String updatedBy,
        List<OrderQuantityDto> orderQuantityDtoList
    ) {
        this.orderId = orderId;
        this.customer = customer;
        this.paymentType = paymentType;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.orderQuantityDtoList = orderQuantityDtoList;
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

    public List<OrderQuantityDto> getOrderQuantityDtoList() {
        return orderQuantityDtoList;
    }

    public void setOrderQuantityDtoList(List<OrderQuantityDto> orderQuantityDtoList) {
        this.orderQuantityDtoList = orderQuantityDtoList;
    }
}
