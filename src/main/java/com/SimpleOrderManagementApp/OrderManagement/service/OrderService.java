package com.SimpleOrderManagementApp.OrderManagement.service;

import com.SimpleOrderManagementApp.OrderManagement.dto.OrderDto;

public interface OrderService {
    OrderDto findOrderByOrderId(Integer orderId);
    OrderDto createOrder(OrderDto incomingOrderDto);
    OrderDto updateOrder(OrderDto incomingDto);
    boolean deleteOrder(Integer orderId);
}
