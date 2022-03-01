package com.SimpleOrderManagementApp.OrderManagement.repository;

import com.SimpleOrderManagementApp.OrderManagement.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByCustomer(String customer);
}
