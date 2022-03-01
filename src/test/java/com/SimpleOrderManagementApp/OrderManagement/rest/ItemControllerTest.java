package com.SimpleOrderManagementApp.OrderManagement.rest;

import com.SimpleOrderManagementApp.OrderManagement.dto.ItemDto;
import com.SimpleOrderManagementApp.OrderManagement.exceptions.ItemNotFoundException;
import com.SimpleOrderManagementApp.OrderManagement.service.ItemService;
import com.SimpleOrderManagementApp.SimpleOrderManagementApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class ItemControllerTest {
    @Mock
    private ItemService itemService;

    private ItemController itemController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        itemController = new ItemController(itemService);
    }

    private static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Test
    void findItemsWithResultTest() throws IOException {
        List<ItemDto> itemDtosFromJsonFile = Arrays.asList(getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemDtoList.json").getFile(),
                ItemDto[].class
        ));
        given(itemService.findAllItems()).willReturn(itemDtosFromJsonFile);
        List<ItemDto> result = itemController.getAllItems().getBody();
        Assertions.assertEquals(result.size(),2);
    }

    @Test
    void findItemsWithNoResultTest() throws IOException {
        List<ItemDto> emptyList = new ArrayList<>();
        given(itemService.findAllItems()).willReturn(emptyList);
        Assertions.assertThrows(
                ItemNotFoundException.class,
                ()->itemController.getAllItems()
        );
    }

    @Test
    void createItemWithAllVariableTest() throws IOException {
        ItemDto itemDtoFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemDtoCreation.json").getFile(),
                ItemDto.class
        );
        given(itemService.createItem(itemDtoFromJsonFile)).willReturn(itemDtoFromJsonFile);
        ItemDto result = itemController.createItem(itemDtoFromJsonFile).getBody();
        Assertions.assertEquals(result.getPrice(),itemDtoFromJsonFile.getPrice());
        Assertions.assertEquals(result.getWeight(),itemDtoFromJsonFile.getWeight());
        Assertions.assertEquals(result.getSize(),itemDtoFromJsonFile.getSize());
    }

    @Test
    void createItemWithoutAllVariableTest() throws IOException {
        ItemDto itemDtoFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemDtoCreation.json").getFile(),
                ItemDto.class
        );
        itemDtoFromJsonFile.setPrice(null);
        given(itemService.createItem(itemDtoFromJsonFile)).willReturn(null);
        Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->itemController.createItem(itemDtoFromJsonFile)
        );
    }
}
