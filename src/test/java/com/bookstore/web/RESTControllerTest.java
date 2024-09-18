package com.bookstore.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RESTControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAllBooks() throws Exception {
        this.mockMvc.perform(get("/rest/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString("Ernest Hemingway")));
    }

    @Test
    void getBookById() throws Exception {
        String bookJson = "{\"id\":1,\"title\":\"A farewell to Arms\",\"author\":\"Ernest Hemingway\",\"year\":1929,\"isbn\":\"1232323-21\",\"category\":{\"id\":1,\"name\":\"Art\"}}";
        this.mockMvc.perform(get("/rest/book/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(bookJson));
    }
}
