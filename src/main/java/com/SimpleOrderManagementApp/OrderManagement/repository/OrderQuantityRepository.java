package com.SimpleOrderManagementApp.OrderManagement.repository;

import com.SimpleOrderManagementApp.OrderManagement.domain.Order;
import com.SimpleOrderManagementApp.OrderManagement.domain.OrderQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderQuantityRepository extends JpaRepository<OrderQuantity,Integer> {
    List<OrderQuantity> findByOrder(Order order);
}
