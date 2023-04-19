package com.example.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "book_order")
public class Order {

    @Id
    @Column(name = "id")
    private final String id;

    @OneToMany(mappedBy = "bookId")
    private final List<BookOrderInfo> orderedBooks;

    @Column(name = "date")
    private final LocalDate date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private final OrderStatus status;


    protected Order() {
        this.orderedBooks = new ArrayList<>();
        this.id = "";
        this.date = null;
        this.status = null;
    }

    private Order(List<BookOrderInfo> orderedBooks, OrderStatus status) {
        this.id = UUID.randomUUID().toString();
        this.orderedBooks = new ArrayList<>(orderedBooks);
        this.date = LocalDate.now();
        this.status = status;
    }

    private Order(String id, List<BookOrderInfo> orderedBooks, OrderStatus status, LocalDate date) {
        this.id = id;
        this.orderedBooks = new ArrayList<>(orderedBooks);
        this.status = status;
        this.date = date;
    }

    public static Order newOrder(List<BookOrderInfo> orderedBooks) {
        return new Order(orderedBooks, OrderStatus.IN_PROGRESS);
    }

    public static Order successOrder(Order order) {
        return new Order(
                order.id,
                order.orderedBooks,
                OrderStatus.SUCCESS,
                order.date
        );
    }

    public String getId() {
        return id;
    }

    public List<BookOrderInfo> getOrderedBooks() {
        return orderedBooks;
    }

    public LocalDate getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
