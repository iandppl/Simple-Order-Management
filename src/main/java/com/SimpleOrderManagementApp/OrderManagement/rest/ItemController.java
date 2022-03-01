package com.SimpleOrderManagementApp.OrderManagement.rest;

import com.SimpleOrderManagementApp.OrderManagement.exceptions.ItemNotFoundException;
import com.SimpleOrderManagementApp.OrderManagement.dto.ItemDto;
import com.SimpleOrderManagementApp.OrderManagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(
       ItemService itemService
    ) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems(){
        List<ItemDto> result = itemService.findAllItems();
        if(result.size() == 0) {
            throw new ItemNotFoundException("There are no items in database");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto item) {
        ItemDto result = new ItemDto();
        result = itemService.createItem(item);
        if(result == null) {
            throw new IllegalArgumentException("Error! Item sent have empty price and weight");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
