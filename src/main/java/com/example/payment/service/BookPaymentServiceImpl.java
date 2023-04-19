package com.example.payment.service;

import com.example.payment.controller.request.BookOrderRequest;
import com.example.payment.entity.Order;
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

    public BookPaymentServiceImpl(
            BookOrderRepository repository,
            BookPriceRepository priceRepository,
            BookOrderInfoRepository orderInfoRepository
    ) {
        this.repository = repository;
        this.priceRepository = priceRepository;
        this.orderInfoRepository = orderInfoRepository;
    }

    @Override
    public BookOrderResult doPayment(BookOrderRequest request) {
        double totalPrice = calculateOrderPrice(request.getBooks());
        List<BookOrderInfo> bookOrderInfos = orderInfoRepository.saveAll(request.getBooks());
        Order newOrder = repository.save(Order.newOrder(bookOrderInfos));
//        todo create real payment
        Order successOrder = repository.save(Order.successOrder(newOrder));
        return new BookOrderResult(successOrder.getId(), totalPrice, OrderStatus.SUCCESS);
    }

    private record OrderPriceCount(double price, long count) {
        double sum() {
            return price * count;
        }
    }

    private double calculateOrderPrice(List<BookOrderInfo> orderedBooks) {
        List<Long> ids = orderedBooks.stream().map(BookOrderInfo::getBookId).toList();
        Map<Long, Double> bookPrices = priceRepository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(BookPrice::getBookId, BookPrice::getPrice));

        return orderedBooks.stream()
                .map(orderInfo -> new OrderPriceCount(bookPrices.get(orderInfo.getBookId()), orderInfo.getCount()))
                .mapToDouble(OrderPriceCount::sum)
                .sum();
    }

}
