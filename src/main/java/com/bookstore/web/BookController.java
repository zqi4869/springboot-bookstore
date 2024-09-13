package com.bookstore.web;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
