package com.SimpleOrderManagementApp.OrderManagement.service;

import com.SimpleOrderManagementApp.OrderManagement.domain.Item;
import com.SimpleOrderManagementApp.OrderManagement.domain.Order;
import com.SimpleOrderManagementApp.OrderManagement.domain.OrderQuantity;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderDto;
import com.SimpleOrderManagementApp.OrderManagement.dto.OrderQuantityDto;
import com.SimpleOrderManagementApp.OrderManagement.mapper.OrderMapper;
import com.SimpleOrderManagementApp.OrderManagement.mapper.OrderQuantityMapper;
import com.SimpleOrderManagementApp.OrderManagement.repository.OrderQuantityRepository;
import com.SimpleOrderManagementApp.OrderManagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private final ItemService itemService;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderQuantityMapper orderQuantityMapper;
    private final OrderQuantityRepository orderQuantityRepository;

    @Autowired
    public OrderServiceImpl(
        ItemService itemService,
        OrderMapper orderMapper,
        OrderRepository orderRepository,
        OrderQuantityMapper orderQuantityMapper,
        OrderQuantityRepository orderQuantityRepository
    ){
        this.itemService = itemService;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.orderQuantityMapper = orderQuantityMapper;
        this.orderQuantityRepository = orderQuantityRepository;
    }

    private OrderDto findOrderQuantity (Order order) {
        List<OrderQuantity> orderQuantityList = orderQuantityRepository.findByOrder(order);
        OrderDto result = orderMapper.toOrderDto(order);
        List<OrderQuantityDto> orderQuantityDtos = orderQuantityMapper.toOrderQuantitDtos(orderQuantityList);
        result.setOrderQuantityDtoList(orderQuantityDtos);
        return result;
    }

    public OrderDto findOrderByOrderId(Integer orderId) {
        Order orderFound = orderRepository.findById(orderId).orElse(null);
        if(orderFound == null) {
            return null;
        }
        return findOrderQuantity(orderFound);
    }

    @Transactional
    private Order createOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    private OrderDto mapOrder(Order order, List<OrderQuantity> orderQuantityList){
        OrderDto resultDto = orderMapper.toOrderDto(order);
        List<OrderQuantityDto> orderQuantityListResultDto = orderQuantityMapper.toOrderQuantitDtos(orderQuantityList);
        resultDto.setOrderQuantityDtoList(orderQuantityListResultDto);
        return resultDto;
    }

    public OrderDto createOrder(OrderDto incomingOrderDto) {
        Order order = orderMapper.toOrderEntity(incomingOrderDto);
        order.setCreatedDate(new Date());
        Order result = createOrUpdateOrder(order);
        List<OrderQuantity> orderQuantityList = orderQuantityMapper.toOrderQuantityEntities(incomingOrderDto.getOrderQuantityDtoList());
        orderQuantityList = orderQuantityList.stream().map(orderQuantity -> {
            orderQuantity.setOrder(result);
            return orderQuantity;
        }).collect(Collectors.toList());
        List<OrderQuantity> orderQuantityListResult = orderQuantityRepository.saveAll(orderQuantityList);
        return mapOrder(result, orderQuantityListResult);
    }

    @Transactional
    private void deleteOrderQuantity(List<OrderQuantity> deleteOrderQuantityList){
        orderQuantityRepository.deleteAll(deleteOrderQuantityList);
    }

    private List<OrderQuantity> getDeleteOrderQuantity(List<OrderQuantity> incomingList, List<OrderQuantity> currentList){
        return currentList.stream().filter(current->
                incomingList.stream().noneMatch(
                        incoming->incoming.equals(current)
                )
        ).collect(Collectors.toList());
    }

    public OrderDto updateOrder(OrderDto incomingDto) {
        Order order = orderRepository.findById(incomingDto.getOrderId()).orElse(null);
        if(order == null) {
            return null;
        }
        order.setUpdatedBy(incomingDto.getUpdatedBy());
        order.setUpdatedDate(new Date());
        Order result = createOrUpdateOrder(order);
        List<OrderQuantity> incomingOrderQuantityList = orderQuantityMapper
                .toOrderQuantityEntities(incomingDto.getOrderQuantityDtoList());
        incomingOrderQuantityList = incomingOrderQuantityList.stream().map(orderQuantity -> {
            orderQuantity.setOrder(result);
            return orderQuantity;
        }).collect(Collectors.toList());

        List<OrderQuantity> currentOrderQuantityList = orderQuantityRepository.findByOrder(order);
        currentOrderQuantityList = currentOrderQuantityList.stream().map(current->{
            Item currentItem = current.getItem();
            currentItem.setOrderQuantityList(null);
            current.setItem(currentItem);
            return current;
        }).collect(Collectors.toList());

        List<OrderQuantity> orderQuantityToDelete = getDeleteOrderQuantity(incomingOrderQuantityList,currentOrderQuantityList);

        deleteOrderQuantity(orderQuantityToDelete);

        List<OrderQuantity> orderQuantityListResult = orderQuantityRepository.saveAll(incomingOrderQuantityList);

        return mapOrder(result, orderQuantityListResult);
    }

    public boolean deleteOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            return false;
        }
        List<OrderQuantity> orderQuantityList= order.getOrderQuantityList();
        deleteOrderQuantity(orderQuantityList);
        orderRepository.deleteById(orderId);
        return true;
    }
}