package com.falcon;

import java.math.BigDecimal;

public class Book {

    private Long id;

    private String book_name;

    private String author;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return book_name;
    }

    public void setName(String name) {
        this.book_name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
