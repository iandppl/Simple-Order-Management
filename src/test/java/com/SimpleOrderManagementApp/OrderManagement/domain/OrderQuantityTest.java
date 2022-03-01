package com.SimpleOrderManagementApp.OrderManagement.domain;

import com.SimpleOrderManagementApp.OrderManagement.dto.ItemDto;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderQuantityDto;
import com.SimpleOrderManagementApp.SimpleOrderManagementApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class OrderQuantityTest {
    @Test
    void testConstructor(){
        Item newItem = new Item();
        Order newOrder = new Order();
        OrderQuantity testOrder = new OrderQuantity(1,1, newItem,newOrder);
        Assertions.assertNotNull(testOrder.getOrderQuantityId());
        Assertions.assertNotNull(testOrder.getItem());
        Assertions.assertNotNull(testOrder.getOrder());
        Assertions.assertNotNull(testOrder.getQuantity());
    }

    @Test
    void setterConstructor(){
        Item newItem = new Item();
        Order newOrder = new Order();
        OrderQuantity testOrder = new OrderQuantity();
        testOrder.setOrderQuantityId(1);
        testOrder.setItem(newItem);
        testOrder.setOrder(newOrder);
        testOrder.setQuantity(1);
        Assertions.assertNotNull(testOrder.getOrderQuantityId());
        Assertions.assertNotNull(testOrder.getItem());
        Assertions.assertNotNull(testOrder.getOrder());
        Assertions.assertNotNull(testOrder.getQuantity());
    }

    @Test
    void equalsTest(){
        Item newItem = new Item();
        Order newOrder = new Order();
        OrderQuantity testOrder1 = new OrderQuantity(1,1, newItem,newOrder);
        OrderQuantity testOrder2 = new OrderQuantity(1,1, newItem,newOrder);
        Assertions.assertTrue(testOrder1.equals(testOrder2));
    }
}
