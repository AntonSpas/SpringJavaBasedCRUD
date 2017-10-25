package com.myproject.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Proxy(lazy=false)
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=3, max=100)
    @Column(name = "Title")
    private String title;

    @NotEmpty
    @Size(max=255)
    @Column(name = "Description")
    private String description;

    @Size(min=3, max=100)
    @Column(name = "Author")
    private String author;

    @Pattern(regexp = "\\d{3}-\\d{1}-\\d{4}-\\d{4}-\\d{1}|\\d{3}-\\d{1}-\\d{3}-\\d{5}-\\d{1}|\\d{3}-\\d{1}-\\d{5}-\\d{3}-\\d{1}")
    @Column(name = "ISBN")
    private String isbn;

    @NotNull
    @Min(value = -10000)
    @Max(value = 2017)
    @Column(name = "PrintYear")
    private Integer printYear;

    @Column(name = "ReadAlready")
    private boolean readAlready;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPrintYear() {
        return printYear;
    }

    public void setPrintYear(Integer printYear) {
        this.printYear = printYear;
    }

    public boolean isReadAlready() {
        return readAlready;
    }

    public void setReadAlready(boolean readAlready) {
        this.readAlready = readAlready;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", printYear=" + printYear +
                ", readAlready=" + readAlready +
                '}';
    }
}