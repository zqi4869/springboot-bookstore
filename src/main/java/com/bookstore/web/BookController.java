package com.bookstore.web;

import com.bookstore.domain.Book;
import com.bookstore.domain.Category;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/booklist")
    public String booklist(Model model) {
        List<Book> bookList = bookService.findAllBooks();
        model.addAttribute("booklist", bookList);
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addbook(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "addbook";
    }

    @GetMapping("/deletebook/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deletebook(Model model, @PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "redirect:/booklist";
    }

    @GetMapping("/savebook")
    public String savebook(Model model, Integer id, String author, String title, String isbn, Integer year, Integer categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        Book book = new Book();
        book.setId(id);
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setTitle(title);
        book.setYear(year);
        book.setCategory(category);
        bookService.saveBook(book);
        return "redirect:/booklist";
    }

    @GetMapping("/editpage/{id}")
    public String editpage(Model model, @PathVariable("id") Integer id) {
        Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "editpage";
    }
}
