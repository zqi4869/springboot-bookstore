package com.bookstore;

import com.bookstore.domain.Book;
import com.bookstore.domain.Category;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerService implements CommandLineRunner {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;

    @Override
    public void run(String... args) {
        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Art");
        categoryService.saveCategory(category1);

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Computer");
        categoryService.saveCategory(category2);

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("A farewell to Arms");
        book1.setIsbn("1232323-21");
        book1.setAuthor("Ernest Hemingway");
        book1.setYear(1929);
        book1.setCategory(category1);
        bookService.saveBook(book1);

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("Python");
        book2.setIsbn("2212334-5");
        book2.setAuthor("Guido van Rossum");
        book2.setYear(1989);
        book2.setCategory(category2);
        bookService.saveBook(book2);

        Book book3 = new Book();
        book3.setId(3);
        book3.setTitle("C++ Primer");
        book3.setIsbn("3001234-2");
        book3.setAuthor("Bjarne Stroustrup");
        book3.setYear(1990);
        book3.setCategory(category2);
        bookService.saveBook(book3);

        bookService.findAllBooks().forEach(book -> System.out.println("Book id="+book.getId()
                +", title="+book.getTitle()+", author="+book.getAuthor()+", year="+book.getYear()
                +", category="+book.getCategory().getName()));
    }
}
