package com.SimpleOrderManagementApp.OrderManagement.service;

import com.SimpleOrderManagementApp.OrderManagement.domain.Item;
import com.SimpleOrderManagementApp.OrderManagement.dto.ItemDto;
import com.SimpleOrderManagementApp.OrderManagement.mapper.ItemMapper;
import com.SimpleOrderManagementApp.OrderManagement.repository.ItemRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class ItemServiceTest {
    @Mock
    private ItemMapper itemMapper;

    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        itemService = new ItemServiceImpl(itemRepository,itemMapper);
    }

    private static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Test
    void findItemsFromNonEmptyTableTest() throws IOException {
        List<Item> itemsFromJsonFile = Arrays.asList(getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemList.json").getFile(),
                Item[].class
        ));
        List<ItemDto> itemDtosFromJsonFile = Arrays.asList(getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemDtoList.json").getFile(),
                ItemDto[].class
        ));
        given(itemRepository.findAll()).willReturn(itemsFromJsonFile);
        given(itemMapper.toItemsDto(any())).willReturn(itemDtosFromJsonFile);
        List<ItemDto> result = itemService.findAllItems();
        Assertions.assertEquals(result.size(),2);
    }

    @Test
    void findItemByIdTest() throws IOException {
        Item itemFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemCreation.json").getFile(),
                Item.class
        );
        ItemDto itemDtoFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemDtoCreation.json").getFile(),
                ItemDto.class
        );
        given(itemRepository.findById(1)).willReturn(Optional.ofNullable(itemFromJsonFile));
        given(itemMapper.toItemDto(any())).willReturn(itemDtoFromJsonFile);
        ItemDto result = itemService.findItemById(1);
        Assertions.assertEquals(result.getId(),itemFromJsonFile.getId());
        Assertions.assertEquals(result.getPrice(),itemFromJsonFile.getPrice());
        Assertions.assertEquals(result.getWeight(),itemFromJsonFile.getWeight());
        Assertions.assertEquals(result.getSize(),itemFromJsonFile.getSize());
    }

    @Test
    void findItemsFromEmptyTableTest() {
        List<Item> emptyItemList = new ArrayList<>();
        List<ItemDto> emptyItemDtoList = new ArrayList<>();
        given(itemRepository.findAll()).willReturn(emptyItemList);
        given(itemMapper.toItemsDto(any())).willReturn(emptyItemDtoList);
        List<ItemDto> result = itemService.findAllItems();
        Assertions.assertEquals(result.size(),0);
    }

    @Test
    void createItemWithAllValues() throws IOException {
        Item itemFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemCreation.json").getFile(),
                Item.class
        );
        ItemDto itemDtoFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemDtoCreation.json").getFile(),
                ItemDto.class
        );
        given(itemMapper.toItemEntity(any())).willReturn(itemFromJsonFile);
        given(itemRepository.save(any())).willReturn(itemFromJsonFile);
        given(itemMapper.toItemDto(any())).willReturn(itemDtoFromJsonFile);
        ItemDto result = itemService.createItem(itemDtoFromJsonFile);
        Assertions.assertEquals(result.getId(),itemFromJsonFile.getId());
        Assertions.assertEquals(result.getPrice(),itemFromJsonFile.getPrice());
        Assertions.assertEquals(result.getWeight(),itemFromJsonFile.getWeight());
        Assertions.assertEquals(result.getSize(),itemFromJsonFile.getSize());
    }

    @Test
    void createItemWithoutAllValues() throws IOException {
        ItemDto itemDtoFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/ItemDtoCreation.json").getFile(),
                ItemDto.class
        );
        itemDtoFromJsonFile.setPrice(null);
        ItemDto result = itemService.createItem(itemDtoFromJsonFile);
        Assertions.assertNull(result);
    }
}
