package com.SimpleOrderManagementApp.OrderManagement.dto;

import com.SimpleOrderManagementApp.OrderManagement.domain.Item;
import com.SimpleOrderManagementApp.OrderManagement.domain.OrderQuantity;
import com.SimpleOrderManagementApp.SimpleOrderManagementApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class OrderQuantityDtoTest {
    @Test
    void testConstructor(){
        ItemDto newItem = new ItemDto();
        OrderQuantityDto testOrderQuantityDto = new OrderQuantityDto(1,1,newItem);
        Assertions.assertNotNull(testOrderQuantityDto.getOrderQuantityId());
        Assertions.assertNotNull(testOrderQuantityDto.getQuantity());
        Assertions.assertNotNull(testOrderQuantityDto.getItemDto());
    }

    @Test
    void setterConstructor(){
        ItemDto testItem = new ItemDto();
        OrderQuantityDto testOrderQuantityDto = new OrderQuantityDto();
        testOrderQuantityDto.setOrderQuantityId(1);
        testOrderQuantityDto.setQuantity(1);
        testOrderQuantityDto.setItemDto(testItem);
        Assertions.assertNotNull(testOrderQuantityDto.getOrderQuantityId());
        Assertions.assertNotNull(testOrderQuantityDto.getQuantity());
        Assertions.assertNotNull(testOrderQuantityDto.getItemDto());
    }

    @Test
    void equalsTest(){
        ItemDto newItem = new ItemDto();
        OrderQuantityDto testOrderQuantityDto1 = new OrderQuantityDto(1,1,newItem);
        OrderQuantityDto testOrderQuantityDto2 = new OrderQuantityDto(1,1,newItem);
        Assertions.assertTrue(testOrderQuantityDto1.equals(testOrderQuantityDto2));
    }
}
