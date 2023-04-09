package com.example.payment.repository;

import com.example.payment.entity.BookOrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderInfoRepository extends JpaRepository<BookOrderInfo, Long> {
}
