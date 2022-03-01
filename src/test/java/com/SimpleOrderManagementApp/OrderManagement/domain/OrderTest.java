package com.SimpleOrderManagementApp.OrderManagement.domain;

import com.SimpleOrderManagementApp.SimpleOrderManagementApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class OrderTest {
    @Test
    void testConstructor(){
        List<OrderQuantity> emptyList = new ArrayList<>();
        Order testOrder = new Order(1, "John", "Master Card", new Date(), "Jeremy", new Date(), "", emptyList);
        Assertions.assertNotNull(testOrder.getOrderId());
        Assertions.assertNotNull(testOrder.getCustomer());
        Assertions.assertNotNull(testOrder.getPaymentType());
        Assertions.assertNotNull(testOrder.getCreatedDate());
        Assertions.assertNotNull(testOrder.getCreatedBy());
        Assertions.assertNotNull(testOrder.getUpdatedDate());
        Assertions.assertNotNull(testOrder.getUpdatedBy());
        Assertions.assertNotNull(testOrder.getOrderQuantityList());
    }

    @Test
    void setterConstructor(){
        List<OrderQuantity> emptyList = new ArrayList<>();
        Order testOrder = new Order();
        testOrder.setOrderId(1);
        testOrder.setCustomer("John");
        testOrder.setPaymentType("Master Card");
        testOrder.setCreatedDate(new Date());
        testOrder.setCreatedBy("Jeremy");
        testOrder.setUpdatedBy("");
        testOrder.setUpdatedDate(new Date());
        testOrder.setOrderQuantityList(emptyList);
        Assertions.assertNotNull(testOrder.getOrderId());
        Assertions.assertNotNull(testOrder.getCustomer());
        Assertions.assertNotNull(testOrder.getPaymentType());
        Assertions.assertNotNull(testOrder.getCreatedDate());
        Assertions.assertNotNull(testOrder.getCreatedBy());
        Assertions.assertNotNull(testOrder.getUpdatedDate());
        Assertions.assertNotNull(testOrder.getUpdatedBy());
        Assertions.assertNotNull(testOrder.getOrderQuantityList());
    }
}
