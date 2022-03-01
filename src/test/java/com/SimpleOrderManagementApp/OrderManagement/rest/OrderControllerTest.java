package com.SimpleOrderManagementApp.OrderManagement.rest;

import com.SimpleOrderManagementApp.OrderManagement.dto.ItemDto;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderDto;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderQuantityDto;
import com.SimpleOrderManagementApp.OrderManagement.exceptions.ItemNotFoundException;
import com.SimpleOrderManagementApp.OrderManagement.service.ItemService;
import com.SimpleOrderManagementApp.OrderManagement.service.OrderService;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class OrderControllerTest {
    @Mock
    private ItemService itemService;

    @Mock
    private OrderService orderService;

    private OrderController orderController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        orderController = new OrderController(orderService,itemService);
    }

    private static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Test
    void findOrderWithResultTest() throws IOException {
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        given(orderService.findOrderByOrderId(any())).willReturn(orderDtosFromJsonFile);
        OrderDto result = orderController.findOrderByOrderId(1).getBody();
        Assertions.assertEquals(result.getOrderId(),1);
        Assertions.assertEquals(result.getCustomer(),"John");
        Assertions.assertEquals(result.getPaymentType(),"Master Card");
        Assertions.assertEquals(result.getOrderQuantityDtoList().size(),1);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getOrderQuantityId(),13);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getQuantity(),5);
    }

    @Test
    void findOrderWithoutResultTest() throws IOException {
        given(orderService.findOrderByOrderId(any())).willReturn(null);
        Assertions.assertThrows(
            ItemNotFoundException.class,
            ()->orderController.findOrderByOrderId(1)
        );
    }

    @Test
    void deleteOrderWithResultTest(){
        given(orderService.deleteOrder(any())).willReturn(true);
        Integer result = orderController.deleteOrder(1).getBody();
        Assertions.assertEquals(result, 1);
    }

    @Test
    void deleteOrderWithoutResultTest(){
        given(orderService.deleteOrder(any())).willReturn(false);
        Assertions.assertThrows(
                ItemNotFoundException.class,
                ()->orderController.deleteOrder(1)
        );
    }

    @Test
    void createOrderWithAllVariable() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        given(orderService.createOrder(any())).willReturn(orderDtosFromJsonFile);
        given(itemService.findItemById(any()))
                .willReturn(
                    orderDtosFromJsonFile.getOrderQuantityDtoList()
                        .get(0).getItemDto()
                );
        OrderDto result = orderController.createOrder(orderDtosFromJsonFile).getBody();
        Assertions.assertEquals(result.getOrderId(),1);
        Assertions.assertEquals(result.getCustomer(),"John");
        Assertions.assertEquals(result.getPaymentType(),"Master Card");
        Assertions.assertEquals(result.getOrderQuantityDtoList().size(),1);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getOrderQuantityId(),13);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getQuantity(),5);
    }

    @Test
    void createOrderWithoutCreatedByVariable() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        orderDtosFromJsonFile.setCreatedBy("");
        Assertions.assertThrows(
                IllegalArgumentException.class,
                ()-> orderController.createOrder(orderDtosFromJsonFile)
        );
    }

    @Test
    void createOrderWithoutOrderQuantityDtoListVariable() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        List<OrderQuantityDto> emptyList = new ArrayList<>();
        orderDtosFromJsonFile.setOrderQuantityDtoList(emptyList);
        Assertions.assertThrows(
                ItemNotFoundException.class,
                ()-> orderController.createOrder(orderDtosFromJsonFile)
        );
    }

    @Test
    void createOrderWithDifferentItemVariable() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        ItemDto newItem = new ItemDto(2,"",0.25,2.5);
        given(itemService.findItemById(any())).willReturn(newItem);
        Assertions.assertThrows(
                ItemNotFoundException.class,
                ()-> orderController.createOrder(orderDtosFromJsonFile)
        );
    }

    @Test
    void updateOrderWithAllVariable() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        orderDtosFromJsonFile.setUpdatedBy("Jenny");
        given(orderService.updateOrder(any())).willReturn(orderDtosFromJsonFile);
        given(itemService.findItemById(any()))
                .willReturn(
                        orderDtosFromJsonFile.getOrderQuantityDtoList()
                                .get(0).getItemDto()
                );
        OrderDto result = orderController.updateOrder(orderDtosFromJsonFile).getBody();
        Assertions.assertEquals(result.getOrderId(),1);
        Assertions.assertEquals(result.getCustomer(),"John");
        Assertions.assertEquals(result.getPaymentType(),"Master Card");
        Assertions.assertEquals(result.getOrderQuantityDtoList().size(),1);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getOrderQuantityId(),13);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getQuantity(),5);
    }

    @Test
    void updateOrderWithNoSuchOrder() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        orderDtosFromJsonFile.setUpdatedBy("Jenny");
        given(orderService.updateOrder(any())).willReturn(null);
        given(itemService.findItemById(any()))
                .willReturn(
                        orderDtosFromJsonFile.getOrderQuantityDtoList()
                                .get(0).getItemDto()
                );
        Assertions.assertThrows(
                ItemNotFoundException.class,
                ()->orderController.updateOrder(orderDtosFromJsonFile)
        );
    }

    @Test
    void updateOrderWithoutUpdatedByVariable() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        orderDtosFromJsonFile.setUpdatedBy("");
        Assertions.assertThrows(
                IllegalArgumentException.class,
                ()->orderController.updateOrder(orderDtosFromJsonFile)
        );
    }

    @Test
    void updateOrderWithoutOrderQuantityDtoListVariable() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        orderDtosFromJsonFile.setUpdatedBy("Jenny");
        List<OrderQuantityDto> emptyList = new ArrayList<>();
        orderDtosFromJsonFile.setOrderQuantityDtoList(emptyList);
        Assertions.assertThrows(
                ItemNotFoundException.class,
                ()->orderController.updateOrder(orderDtosFromJsonFile)
        );
    }

    @Test
    void updateOrderWithDifferentItemVariable() throws IOException{
        OrderDto orderDtosFromJsonFile = getObjectMapper().readValue(
                new ClassPathResource("jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        orderDtosFromJsonFile.setUpdatedBy("Jenny");
        ItemDto newItem = new ItemDto(2,"",0.25,2.5);
        given(itemService.findItemById(any())).willReturn(newItem);
        Assertions.assertThrows(
                ItemNotFoundException.class,
                ()-> orderController.updateOrder(orderDtosFromJsonFile)
        );
    }
}
