package com.SimpleOrderManagementApp.OrderManagement.service;

import com.SimpleOrderManagementApp.OrderManagement.domain.Item;
import com.SimpleOrderManagementApp.OrderManagement.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto item);

    List<ItemDto> findAllItems();

    ItemDto findItemById(Integer id);
}
