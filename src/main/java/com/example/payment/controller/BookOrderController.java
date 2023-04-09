package com.example.payment.controller;

import com.example.payment.controller.request.BookOrderRequest;
import com.example.payment.entity.BookOrderResult;
import com.example.payment.service.BookPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class BookOrderController {

    private final BookPaymentService paymentService;

    public BookOrderController(BookPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<BookOrderResult> makeOrder(@RequestBody BookOrderRequest request) {
        BookOrderResult bookOrderResult = paymentService.doPayment(request);
        return new ResponseEntity<>(bookOrderResult, HttpStatus.OK);
    }
}
