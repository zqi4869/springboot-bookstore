package com.bookstore.web;

import com.bookstore.domain.Book;
import com.bookstore.service.RESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RESTController {
    @Autowired
    private RESTService restService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return restService.findAllBooks();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        return restService.findBookById(id);
    }
}
