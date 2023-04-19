package com.example.payment.repository;

import com.example.payment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<Order, String> {
}
