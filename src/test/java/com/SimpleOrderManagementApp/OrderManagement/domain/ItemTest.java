package com.SimpleOrderManagementApp.OrderManagement.domain;

import com.SimpleOrderManagementApp.SimpleOrderManagementApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class ItemTest {

    @Test
    void testConstructor(){
        List<OrderQuantity> emptyList = new ArrayList<>();
        Item testItem = new Item(1,2.5,0.25,"",emptyList);
        Assertions.assertNotNull(testItem.getId());
        Assertions.assertNotNull(testItem.getPrice());
        Assertions.assertNotNull(testItem.getSize());
        Assertions.assertNotNull(testItem.getWeight());
        Assertions.assertNotNull(testItem.getOrderQuantityList());
    }

    @Test
    void setterConstructor(){
        List<OrderQuantity> emptyList = new ArrayList<>();
        Item testItem = new Item();
        testItem.setId(1);
        testItem.setPrice(2.5);
        testItem.setWeight(0.25);
        testItem.setSize("");
        testItem.setOrderQuantityList(emptyList);
        Assertions.assertNotNull(testItem.getId());
        Assertions.assertNotNull(testItem.getPrice());
        Assertions.assertNotNull(testItem.getSize());
        Assertions.assertNotNull(testItem.getWeight());
    }

    @Test
    void equalsTest(){
        List<OrderQuantity> emptyList = new ArrayList<>();
        Item testItem1 = new Item(1,2.5,0.25,"",emptyList);
        Item testItem2 = new Item(1,2.5,0.25,"",emptyList);
        Assertions.assertTrue(testItem1.equals(testItem2));
    }
}
