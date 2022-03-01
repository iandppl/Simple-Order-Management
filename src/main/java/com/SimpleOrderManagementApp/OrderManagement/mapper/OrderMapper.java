package com.SimpleOrderManagementApp.OrderManagement.mapper;

import com.SimpleOrderManagementApp.OrderManagement.domain.Order;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
    Order toOrderEntity(OrderDto orderDto);
}
