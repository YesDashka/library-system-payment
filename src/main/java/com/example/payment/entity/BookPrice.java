package com.example.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_price")
public class BookPrice {

    @Id
    @Column(name="book_id")
    private final long bookId;

    @Column(name = "price")
    private final double price;

    protected BookPrice() {
        this.bookId = 0;
        this.price = 0;
    }

    public BookPrice(long bookId, double price) {
        this.bookId = bookId;
        this.price = price;
    }

    public long getBookId() {
        return bookId;
    }

    public double getPrice() {
        return price;
    }
}
