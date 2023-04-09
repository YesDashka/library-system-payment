package com.example.payment.service;

import com.example.payment.controller.request.BookOrderRequest;
import com.example.payment.entity.BookOrder;
import com.example.payment.entity.BookOrderInfo;
import com.example.payment.entity.BookOrderResult;
import com.example.payment.entity.BookPrice;
import com.example.payment.entity.OrderStatus;
import com.example.payment.repository.BookOrderInfoRepository;
import com.example.payment.repository.BookOrderRepository;
import com.example.payment.repository.BookPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookPaymentServiceImpl implements BookPaymentService {


    private final BookOrderRepository repository;
    private final BookPriceRepository priceRepository;
    private final BookOrderInfoRepository orderInfoRepository;

    public BookPaymentServiceImpl(BookOrderRepository repository, BookPriceRepository priceRepository, BookOrderInfoRepository orderInfoRepository) {
        this.repository = repository;
        this.priceRepository = priceRepository;
        this.orderInfoRepository = orderInfoRepository;
    }

    @Override
    public BookOrderResult doPayment(BookOrderRequest request) {
        double totalPrice = calculateOrderPrice(request.getBooks());
        List<BookOrderInfo> bookOrderInfos = orderInfoRepository.saveAll(request.getBooks());
        System.out.println(request);
        BookOrder newOrder = repository.save(BookOrder.newOrder(bookOrderInfos));
//        todo create real payment
        BookOrder successOrder = repository.save(BookOrder.successOrder(newOrder));
        return new BookOrderResult(totalPrice, OrderStatus.SUCCESS);
    }

    private record OrderPrice(double price, long count) {}

    private double calculateOrderPrice(List<BookOrderInfo> orderedBooks) {
        List<Long> ids = orderedBooks.stream().map(BookOrderInfo::getBookId).toList();
        Map<Long, Double> bookPrices = priceRepository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(BookPrice::getBookId, BookPrice::getPrice));

        return orderedBooks.stream()
                .map(orderInfo -> {
                    Double price = bookPrices.get(orderInfo.getBookId());
                    int count = orderInfo.getCount();
                    return new OrderPrice(price, count);
                })
                .mapToDouble(s -> s.count * s.price)
                .sum();
    }

}
