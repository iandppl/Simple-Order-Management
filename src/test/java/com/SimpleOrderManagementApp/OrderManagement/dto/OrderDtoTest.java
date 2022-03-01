package com.SimpleOrderManagementApp.OrderManagement.dto;

import com.SimpleOrderManagementApp.SimpleOrderManagementApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class OrderDtoTest {
    @Test
    void testConstructor(){
        List<OrderQuantityDto> orderQuantityDtoList = new ArrayList<>();
        OrderDto testOrderDto = new OrderDto(1, "John", "Master Card", new Date(), "Jeremy", new Date(), "",orderQuantityDtoList);
        Assertions.assertNotNull(testOrderDto.getOrderId());
        Assertions.assertNotNull(testOrderDto.getCustomer());
        Assertions.assertNotNull(testOrderDto.getPaymentType());
        Assertions.assertNotNull(testOrderDto.getCreatedDate());
        Assertions.assertNotNull(testOrderDto.getCreatedBy());
        Assertions.assertNotNull(testOrderDto.getUpdatedDate());
        Assertions.assertNotNull(testOrderDto.getUpdatedBy());
        Assertions.assertNotNull(testOrderDto.getOrderQuantityDtoList());
    }

    @Test
    void setterConstructor(){
        List<OrderQuantityDto> orderQuantityDtoList = new ArrayList<>();
        OrderDto testOrderDto = new OrderDto();
        testOrderDto.setOrderId(1);
        testOrderDto.setCustomer("John");
        testOrderDto.setPaymentType("Master Card");
        testOrderDto.setCreatedDate(new Date());
        testOrderDto.setOrderQuantityDtoList(orderQuantityDtoList);
        testOrderDto.setCreatedBy("Jenny");
        testOrderDto.setUpdatedBy("Johnny");
        testOrderDto.setUpdatedDate(new Date());
        Assertions.assertNotNull(testOrderDto.getOrderId());
        Assertions.assertNotNull(testOrderDto.getCustomer());
        Assertions.assertNotNull(testOrderDto.getPaymentType());
        Assertions.assertNotNull(testOrderDto.getCreatedDate());
        Assertions.assertNotNull(testOrderDto.getCreatedBy());
        Assertions.assertNotNull(testOrderDto.getUpdatedDate());
        Assertions.assertNotNull(testOrderDto.getUpdatedBy());
        Assertions.assertNotNull(testOrderDto.getOrderQuantityDtoList());
    }
}
