package com.example.payment.service;

import com.example.payment.controller.request.BookOrderRequest;
import com.example.payment.entity.BookOrderResult;

public interface BookPaymentService {

    BookOrderResult doPayment(BookOrderRequest request);

}
