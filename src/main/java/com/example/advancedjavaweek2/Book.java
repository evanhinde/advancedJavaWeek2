package com.example.advancedjavaweek2;

import java.util.Arrays;
import java.util.List;

public class Book {
    //variables
    private int bookId;
    private String bookName, author, genre;
    private double price;
    private boolean isAvailable;

    //constructors
    public Book(int bookId, String bookName, String author, String genre, double price, boolean isAvailable) {
        setBookId(bookId);
        setBookName(bookName);
        setAuthor(author);
        setGenre(genre);
        setPrice(price);
        setAvailable(isAvailable);
    }

    // overloaded constructor
    public Book(String bookName, String author, String genre, double price, boolean isAvailable) {
        this.bookId = -1;
        setBookName(bookName);
        setAuthor(author);
        setGenre(genre);
        setPrice(price);
        setAvailable(isAvailable);
    }

    //getters and setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        if(bookId <= 0) {
            throw new IllegalArgumentException("Book ID must be greater than 0");
        }
        else {
            this.bookId = bookId;
        }
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        if(bookName.isBlank()){
        throw new IllegalArgumentException("Book name cannot be empty");
        }
        else {
        this.bookName = bookName;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author.isBlank()){
        throw new IllegalArgumentException("Author cannot be empty");
        }
        else {
        this.author = author;
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        List<String> validGenres = findGenres();
        if(validGenres.contains(genre)) {
        this.genre = genre;
        }
        else {
            throw new IllegalArgumentException("Genre should be from the list: " + validGenres);
        }
    }

    public static List<String> findGenres() {
        return Arrays.asList("Comedy", "Thriller", "Fiction", "Non-Fiction", "Fantasy", "Crime", "History");
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0) {
        throw new IllegalArgumentException("Price cannot be less than 0");
        }
        else {
        this.price = price;
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

//toString

    @Override
    public String toString() {
        return bookId + ": The price of " + bookName + " written by " + author + " of genre " + genre + " is " + price + ".Availability: " + (isAvailable ? "Yes" : "No");
    }
}