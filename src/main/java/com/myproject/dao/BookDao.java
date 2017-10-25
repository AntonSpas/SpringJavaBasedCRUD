package com.myproject.dao;

import com.myproject.model.Book;

import java.util.List;


public interface BookDao {

    public void createBook(Book book);

    public void updateBook(Book book);

    public void removeBook(int id);

    public Book getBookById(int id);

    public List<Book> listBooks();

    public List<Book> getBooks(String title);

    public List<Book> getNotReadBooks();
}