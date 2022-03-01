package com.SimpleOrderManagementApp.OrderManagement.dto;

import com.SimpleOrderManagementApp.OrderManagement.domain.Item;
import com.SimpleOrderManagementApp.SimpleOrderManagementApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class ItemDtoTest {
    @Test
    void testConstructor(){
        ItemDto testItem = new ItemDto(1,"",0.25,2.5);
        Assertions.assertNotNull(testItem.getId());
        Assertions.assertNotNull(testItem.getPrice());
        Assertions.assertNotNull(testItem.getSize());
        Assertions.assertNotNull(testItem.getWeight());
    }

    @Test
    void setterConstructor(){
        ItemDto testItem = new ItemDto();
        testItem.setId(1);
        testItem.setPrice(2.5);
        testItem.setWeight(0.25);
        testItem.setSize("");
        Assertions.assertNotNull(testItem.getId());
        Assertions.assertNotNull(testItem.getPrice());
        Assertions.assertNotNull(testItem.getSize());
        Assertions.assertNotNull(testItem.getWeight());
    }
}
