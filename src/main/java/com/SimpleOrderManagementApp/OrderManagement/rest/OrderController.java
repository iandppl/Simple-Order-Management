package com.SimpleOrderManagementApp.OrderManagement.rest;

import com.SimpleOrderManagementApp.OrderManagement.dto.OrderDto;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderQuantityDto;
import com.SimpleOrderManagementApp.OrderManagement.exceptions.ItemNotFoundException;
import com.SimpleOrderManagementApp.OrderManagement.service.ItemService;
import com.SimpleOrderManagementApp.OrderManagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final ItemService itemService;

    @Autowired
    public OrderController(
        OrderService orderService,
        ItemService itemService
    ) {
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findOrderByOrderId(@PathVariable Integer orderId) {
        OrderDto result = orderService.findOrderByOrderId(orderId);
        if(result==null){
            throw new ItemNotFoundException("There are such order");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto incomingOrderDto) {
        if(incomingOrderDto.getCreatedBy().length()==0 || incomingOrderDto.getCreatedBy() == null){
            throw new IllegalArgumentException("There are no created by user in order");
        }
        if(incomingOrderDto.getOrderQuantityDtoList().size()==0){
            throw new ItemNotFoundException("There are no items in order");
        }
        for (OrderQuantityDto orderQuantityDto : incomingOrderDto.getOrderQuantityDtoList()) {
            if(
               orderQuantityDto.getItemDto().getId()==null||
               !itemService.findItemById(orderQuantityDto.getItemDto().getId())
                       .equals(orderQuantityDto.getItemDto())
            ) {
                throw new ItemNotFoundException("There are items in order that are not found");
            }
        }
        OrderDto result = orderService.createOrder(incomingOrderDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto incomingOrderDto) {
        if(incomingOrderDto.getUpdatedBy().length()==0 || incomingOrderDto.getUpdatedBy() == null){
            throw new IllegalArgumentException("There are no updated by user in order");
        }
        if(incomingOrderDto.getOrderQuantityDtoList().size()==0){
            throw new ItemNotFoundException("There are no items in order");
        }
        for (OrderQuantityDto orderQuantityDto : incomingOrderDto.getOrderQuantityDtoList()) {
            if(
                    orderQuantityDto.getItemDto().getId()==null||
                            !itemService.findItemById(orderQuantityDto.getItemDto().getId())
                                    .equals(orderQuantityDto.getItemDto())
            ) {
                throw new ItemNotFoundException("There are items in order that are not found");
            }
        }
        OrderDto result = orderService.updateOrder(incomingOrderDto);
        if(result == null){
            throw new ItemNotFoundException("There are such order");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Integer> deleteOrder(@PathVariable Integer orderId){
        boolean result = orderService.deleteOrder(orderId);
        if(!result){
            throw new ItemNotFoundException("There are such order");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(orderId);
    }
}
