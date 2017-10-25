package com.myproject.service;

import com.myproject.dao.BookDao;
import com.myproject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public void createBook(Book book) {
        bookDao.createBook(book);
    }

    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    public void removeBook(int id) {
        bookDao.removeBook(id);
    }

    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    public List<Book> listBooks() {
        return bookDao.listBooks();
    }

    public List<Book> getBooks(String title) {
        return bookDao.getBooks(title);
    }

    public List<Book> getNotReadBooks() { return bookDao.getNotReadBooks(); }
}