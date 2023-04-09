package com.example.payment.repository;

import com.example.payment.entity.BookPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPriceRepository extends JpaRepository<BookPrice, Long> {
}
