package com.example.payment.controller.request;

import com.example.payment.entity.BookOrderInfo;

import java.util.List;

public class BookOrderRequest {

    private List<BookOrderInfo> books;

    public List<BookOrderInfo> getBooks() {
        return books;
    }

    public void setBooks(List<BookOrderInfo> books) {
        this.books = books;
    }

    public BookOrderRequest() {
    }

    public BookOrderRequest(List<BookOrderInfo> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookOrderRequest{" +
                "books=" + books +
                '}';
    }
}
