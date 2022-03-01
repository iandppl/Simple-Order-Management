package com.SimpleOrderManagementApp.OrderManagement.mapper;

import com.SimpleOrderManagementApp.OrderManagement.domain.Item;
import com.SimpleOrderManagementApp.OrderManagement.dto.ItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    ItemDto toItemDto(Item item);
    Item toItemEntity(ItemDto itemDto);
    List<ItemDto> toItemsDto(List<Item> item);
    List<Item> toItemsEntity(List<ItemDto> itemDtos);
}
