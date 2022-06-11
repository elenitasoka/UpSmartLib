package com.example.upsmartlib;

public class Book {
    private String title;
    private String author;
    private String image;
    private String pages;
    private int availableCopys;
    private String bookId;

    public Book() {
    }

    public Book(String title, String author, String image, String pages, int availableCopys, String bookId) {
        this.title = title;
        this.author = author;
        this.image = image;
        this.pages = pages;
        this.availableCopys = availableCopys;
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopys() {
        return availableCopys;
    }

    public void setAvailableCopys(int availableCopys) {
        this.availableCopys = availableCopys;
    }

    public String getImage() {
        return image;
    }

    public String getPages() {
        return pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
