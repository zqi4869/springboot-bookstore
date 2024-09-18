package com.bookstore.web;

import com.bookstore.domain.Book;
import com.bookstore.domain.Category;
import com.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void findBookByTitle() {
        Book book = bookRepository.findBookByTitle("A farewell to Arms");
        assertThat(book).isNotNull();
        assertThat(book.getCategory()).isNotNull();
        assertThat(book.getCategory().getName()).isEqualTo("Art");
    }

    @Test
    public void createNewBook() {
        Category category = new Category();
        category.setId(2);
        category.setName("Computer");

        Book book = new Book();
        book.setTitle("test book1");
        book.setIsbn("1001-21");
        book.setAuthor("test author1");
        book.setYear(1929);
        book.setCategory(category);
        bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBook() {
        List<Book> all = bookRepository.findAll();
        for (Book book : all) {
            if (book.getTitle().equals("test book1")) {
                bookRepository.delete(book);
            }
        }
        List<Book> all1 = bookRepository.findAll();
        assertThat(Math.abs(all1.size() - all.size())).isEqualTo(1);
    }

}
