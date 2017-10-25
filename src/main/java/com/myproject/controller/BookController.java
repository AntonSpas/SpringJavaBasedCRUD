package com.myproject.controller;


import com.myproject.model.Book;
import com.myproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/read/{id}")
    public String readBook(@PathVariable("id") int id, @RequestParam Integer page, @RequestParam int stay, Model model) {
        Book book = bookService.getBookById(id);
        book.setReadAlready(true);
        bookService.updateBook(book);
        if (stay == 1) {
            model.addAttribute("book", book);
            model.addAttribute("page", page);
            return "book";
        } else if (stay == 2) {
            return "redirect:/searchnotread?page=" + page;
        }

        return "redirect:/?page=" + page;
    }

    @RequestMapping(value = "/book/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @RequestParam Integer page) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("page", page);
        return "book";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBook(@RequestParam Integer page, Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("page", page);
        model.addAttribute("update", false);
        model.addAttribute("action", "/create");
        return "create-update-book";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") int id, @RequestParam Integer page, @RequestParam int stay, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("page", page);
        model.addAttribute("update", true);
        model.addAttribute("action", "/update?page=" + page +"&stay=" + stay);
        return "create-update-book";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBook(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "create-update-book";
        }
        bookService.createBook(book);
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateBook(@Valid Book book, BindingResult result, @RequestParam Integer page, @RequestParam int stay, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("update", true);
            model.addAttribute("action", "/update?page=" + page +"&stay=" + stay);
            return "create-update-book";
        }
        book.setReadAlready(false);
        bookService.updateBook(book);

        if (stay == 1) {
            model.addAttribute("book", book);
            model.addAttribute("page", page);
            return "book";
        } else if (stay == 2) {
            return "redirect:/searchnotread?page=" + page;
        }

        return "redirect:/?page=" + page;
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id, @RequestParam Integer page, @RequestParam int stay, Model model) {
        bookService.removeBook(id);
        if (stay == 1) {
            model.addAttribute("page", page);
            return "book";
        } else if (stay == 2) {
            return "redirect:/searchnotread?page=" + page;
        }

        return "redirect:/?page=" + page;
    }

    @RequestMapping(value="/searchresults", method = RequestMethod.POST)
    public String searchResults(@ModelAttribute("searchedbook") Book book, Model model) {
        List<Book> searchResult = bookService.getBooks(book.getTitle());
        model.addAttribute("listBooks", searchResult);
        model.addAttribute("searchresults", true);
        return "books";
    }

    @RequestMapping(value="/searchnotread")
    public String searchNotRead(@RequestParam(required = false) Integer page, Model model) {
        model.addAttribute("searchedbook", new Book());
        model.addAttribute("searchresults", true);
        model.addAttribute("root", "/searchnotread");
        model.addAttribute("stay", 2);

        List<Book> searchNotRead = bookService.getNotReadBooks();

        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(searchNotRead);
        pagedListHolder.setPageSize(10);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            page = 1;
        }

        model.addAttribute("page", page);

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        }
        return "books";
    }

    @RequestMapping(value = "/")
    public String listOfUsers(@RequestParam(required = false) Integer page, Model model) {
        model.addAttribute("searchedbook", new Book());
        model.addAttribute("searchresults", false);
        model.addAttribute("root", "/");
        model.addAttribute("stay", 0);

        List<Book> books = bookService.listBooks();

        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(books);
        pagedListHolder.setPageSize(10);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            page = 1;
        }
        model.addAttribute("page", page);

        if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(0);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        } else if (page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page - 1);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        }
        return "books";
    }
}

