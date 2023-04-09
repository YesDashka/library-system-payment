package com.example.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_order_info")
public class BookOrderInfo {

//    @ManyToOne
//    @JoinColumn(name="id", nullable=false)
//    private BookOrder bookOrder;

    @Id
    @Column(name="book_id")
    private long bookId;

    @Column(name = "count")
    private int count;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BookIdCount{" +
                "bookId=" + bookId +
                ", count=" + count +
                '}';
    }
}
