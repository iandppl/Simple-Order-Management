package com.SimpleOrderManagementApp.OrderManagement.service;

import com.SimpleOrderManagementApp.OrderManagement.domain.Item;
import com.SimpleOrderManagementApp.OrderManagement.domain.Order;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderDto;
import com.SimpleOrderManagementApp.OrderManagement.mapper.OrderMapper;
import com.SimpleOrderManagementApp.OrderManagement.mapper.OrderQuantityMapper;
import com.SimpleOrderManagementApp.OrderManagement.repository.OrderQuantityRepository;
import com.SimpleOrderManagementApp.OrderManagement.repository.OrderRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = SimpleOrderManagementApplication.class)
public class OrderServiceTest {
    @Mock
    private ItemService itemService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderQuantityMapper orderQuantityMapper;

    @Mock
    private OrderQuantityRepository orderQuantityRepository;

    private OrderService orderService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(
                itemService,orderMapper,orderRepository,orderQuantityMapper,orderQuantityRepository
        );
    }

    private static ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    @Test
    void findOrderByOrderId() throws IOException {
        Order orderFromJson = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/CreateOrder.json").getFile(),
                Order.class
        );

        OrderDto orderDtoFromJson = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );

        given(orderRepository.findById(1))
                .willReturn(Optional.ofNullable(orderFromJson));
        given(orderQuantityRepository.findByOrder(any()))
                .willReturn(orderFromJson.getOrderQuantityList());
        given(orderMapper.toOrderDto(any()))
                .willReturn(orderDtoFromJson);
        given(orderQuantityMapper.toOrderQuantitDtos(any()))
                .willReturn(orderDtoFromJson.getOrderQuantityDtoList());

        OrderDto result = orderService.findOrderByOrderId(1);
        Assertions.assertEquals(result.getOrderId(),1);
        Assertions.assertEquals(result.getCustomer(),"John");
        Assertions.assertEquals(result.getPaymentType(),"Master Card");
        Assertions.assertEquals(result.getOrderQuantityDtoList().size(),1);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getOrderQuantityId(),13);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getQuantity(),5);
    }

    @Test
    void findOrderByOrderIdWithNoResult() {
        given(orderRepository.findById(1))
                .willReturn(Optional.ofNullable(null));
        OrderDto result = orderService.findOrderByOrderId(1);
        Assertions.assertNull(result);
    }

    @Test
    void deleteOrderByOrderId() throws IOException {
        Order orderFromJson = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/CreateOrder.json").getFile(),
                Order.class
        );
        given(orderRepository.findById(1))
                .willReturn(Optional.ofNullable(orderFromJson));
        boolean result = orderService.deleteOrder(1);
        Assertions.assertTrue(result);
    }

    @Test
    void deleteOrderByOrderIdWithNoResult(){
        given(orderRepository.findById(1))
                .willReturn(Optional.ofNullable(null));
        boolean result = orderService.deleteOrder(1);
        Assertions.assertFalse(result);
    }

    @Test
    void createOrder() throws IOException {
        Order orderFromJson = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/CreateOrder.json").getFile(),
                Order.class
        );

        OrderDto orderDtoFromJson = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        given(orderMapper.toOrderEntity(any()))
                .willReturn(orderFromJson);
        given(orderRepository.save(any()))
                .willReturn(orderFromJson);
        given(orderQuantityMapper.toOrderQuantityEntities(any()))
                .willReturn(orderFromJson.getOrderQuantityList());
        given(orderQuantityRepository.saveAll(any()))
                .willReturn(orderFromJson.getOrderQuantityList());
        given(orderMapper.toOrderDto(any()))
                .willReturn(orderDtoFromJson);
        given(orderQuantityMapper.toOrderQuantitDtos(any()))
                .willReturn(orderDtoFromJson.getOrderQuantityDtoList());
        OrderDto result = orderService.createOrder(orderDtoFromJson);
        Assertions.assertEquals(result.getOrderId(),1);
        Assertions.assertEquals(result.getCustomer(),"John");
        Assertions.assertEquals(result.getPaymentType(),"Master Card");
        Assertions.assertEquals(result.getOrderQuantityDtoList().size(),1);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getOrderQuantityId(),13);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getQuantity(),5);
    }

    @Test
    void updateOrder() throws IOException {
        Order orderFromJson = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/CreateOrder.json").getFile(),
                Order.class
        );

        OrderDto orderDtoFromJson = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        given(orderRepository.findById(1))
                .willReturn(Optional.ofNullable(orderFromJson));
        given(orderRepository.save(any()))
                .willReturn(orderFromJson);
        given(orderQuantityMapper.toOrderQuantityEntities(any()))
                .willReturn(orderFromJson.getOrderQuantityList());
        given(orderQuantityRepository.findByOrder(any()))
                .willReturn(orderFromJson.getOrderQuantityList());
        given(orderQuantityRepository.saveAll(any()))
                .willReturn(orderFromJson.getOrderQuantityList());
        given(orderMapper.toOrderDto(any()))
                .willReturn(orderDtoFromJson);
        given(orderQuantityMapper.toOrderQuantitDtos(any()))
                .willReturn(orderDtoFromJson.getOrderQuantityDtoList());

        OrderDto result = orderService.updateOrder(orderDtoFromJson);
        Assertions.assertEquals(result.getOrderId(),1);
        Assertions.assertEquals(result.getCustomer(),"John");
        Assertions.assertEquals(result.getPaymentType(),"Master Card");
        Assertions.assertEquals(result.getOrderQuantityDtoList().size(),1);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getOrderQuantityId(),13);
        Assertions.assertEquals(result.getOrderQuantityDtoList().get(0).getQuantity(),5);
    }

    @Test
    void updateOrderWithoutResult() throws IOException {
        OrderDto orderDtoFromJson = getObjectMapper().readValue(
                new ClassPathResource(
                        "jsonData/CreateOrderDto.json").getFile(),
                OrderDto.class
        );
        given(orderRepository.findById(1))
                .willReturn(Optional.ofNullable(null));
        OrderDto result = orderService.updateOrder(orderDtoFromJson);
        Assertions.assertNull(result);
    }
}
