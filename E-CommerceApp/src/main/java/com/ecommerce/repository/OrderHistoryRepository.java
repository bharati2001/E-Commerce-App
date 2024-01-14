package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>{

}
