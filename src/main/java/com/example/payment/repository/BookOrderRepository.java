package com.example.payment.repository;

import com.example.payment.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, String> {
}
