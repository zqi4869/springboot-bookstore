package com.bookstore;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerService implements CommandLineRunner {
    @Autowired
    BookService bookService;

    @Override
    public void run(String... args) {
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("A farewell to Arms");
        book1.setIsbn("1232323-21");
        book1.setAuthor("Ernest Hemingway");
        book1.setYear(1929);
        bookService.saveBook(book1);

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("Animal Farm");
        book2.setIsbn("2212334-5");
        book2.setAuthor("George Orwell");
        book2.setYear(1945);
        bookService.saveBook(book2);

        bookService.findAllBooks().forEach(book -> System.out.println("Book id="+book.getId()
                +", title="+book.getTitle()+", author="+book.getAuthor()+", year="+book.getYear()));
    }
}
