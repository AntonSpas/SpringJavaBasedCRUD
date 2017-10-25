package com.myproject.dao;

import com.myproject.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void createBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
    }

    public void updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
    }

    public void removeBook(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        if (book != null) session.delete(book);
    }

    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        return book;
    }

    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> listBooks = session.createQuery("FROM Book").list();
        return listBooks;
    }

    @SuppressWarnings("unchecked")
    public List<Book> getBooks(String title) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Book WHERE Title = :title");
        query.setParameter("title", title);
        List<Book> books = query.list();
        return books;
    }

    @SuppressWarnings("unchecked")
    public List<Book> getNotReadBooks() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Book WHERE ReadAlready = 0");
        List<Book> books = query.list();
        return books;
    }
}
