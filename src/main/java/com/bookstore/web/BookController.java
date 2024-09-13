package com.bookstore.web;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/booklist")
    public String booklist(Model model) {
        List<Book> bookList = bookService.findAllBooks();
        model.addAttribute("booklist", bookList);
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addbook(Model model) {
        return "addbook";
    }

    @GetMapping("/deletebook/{id}")
    public String deletebook(Model model, @PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "redirect:/booklist";
    }

    @GetMapping("/savebook")
    public String savebook(Model model, String author, String title, String isbn, Integer year) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);
        bookService.saveBook(book);
        return "redirect:/booklist";
    }

}
