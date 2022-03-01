package com.SimpleOrderManagementApp.OrderManagement.service;

import com.SimpleOrderManagementApp.OrderManagement.domain.Item;
import com.SimpleOrderManagementApp.OrderManagement.dto.ItemDto;
import com.SimpleOrderManagementApp.OrderManagement.mapper.ItemMapper;
import com.SimpleOrderManagementApp.OrderManagement.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,ItemMapper itemMapper){
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDto createItem(ItemDto itemDto){
        if(itemDto.getPrice()==null || itemDto.getWeight()==null){
            return null;
        }
        Item incomingItem = itemMapper.toItemEntity(itemDto);
        ItemDto result = itemMapper.toItemDto(itemRepository.save(incomingItem));
        return result;
    }

    @Override
    public List<ItemDto> findAllItems() {
        List<Item> foundItems = itemRepository.findAll();
        return itemMapper.toItemsDto(foundItems);
    }

    public ItemDto findItemById(Integer id) {
        Item foundItem = itemRepository.findById(id).orElse(null);
        return itemMapper.toItemDto(foundItem);
    }
}
