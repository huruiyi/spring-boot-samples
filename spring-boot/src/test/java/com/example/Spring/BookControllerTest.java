package com.example.Spring;

import com.example.Spring.model.Book;
import com.example.Spring.service.BookService;
import com.example.Spring.web.BookController;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  //@MockBean //成员变量值不能共享
  @Autowired
  private BookService bookService;
  @MockBean
  private BookService mockBookService;

  @Test
  public void findAl() {
    bookService.create(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"));
    bookService.create(new Book("9780451524935", "1984", "George Orwell"));
    bookService.create(new Book("9780618260300", "The Hobbit", "J.R.R. Tolkien"));

    Iterable<Book> books = bookService.findAll();
    books.forEach(item -> {
      System.out.println(item.getIsbn());
    });
  }

  @Test
  public void testHelloWorldController() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/books/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello World, from Spring Boot 2!"))
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
  }

  @Test
  public void shouldReturnListOfBooks() throws Exception {

    List<Book> books = Arrays.asList(
        new Book("123", "Spring 5 Recipes", "Marten Deinum", "Josh Long"),
        new Book("321", "Pro Spring MVC", "Marten Deinum", "Colin Yates"));

    when(mockBookService.findAll()).thenReturn(books);

    mockMvc.perform(get("/books"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].isbn", Matchers.containsInAnyOrder("123", "321")))
        .andExpect(MockMvcResultMatchers.jsonPath("$[*].title", Matchers.containsInAnyOrder("Spring 5 Recipes", "Pro Spring MVC")));
  }

  @Test
  public void shouldReturn404WhenBookNotFound() throws Exception {
    when(mockBookService.find(anyString())).thenReturn(Optional.empty());
    mockMvc.perform(get("/books/123")).andExpect(status().isNotFound());
  }

  @Test
  public void shouldReturnBookWhenFound() throws Exception {

    when(mockBookService.find(anyString())).thenReturn(
        Optional.of(new Book("123", "Spring 5 Recipes", "Marten Deinum", "Josh Long")));

    mockMvc.perform(get("/books/123"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.equalTo("123")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("Spring 5 Recipes")));
  }

  @Test
  public void shouldAddBook() throws Exception {
    when(mockBookService.create(any(Book.class))).thenReturn(new Book("123456789", "Test Book Stored", "T. Author"));
    mockMvc.perform(post("/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"isbn\" : \"123456789\"}, \"title\" : \"Test Book\", \"authors\" : [\"T. Author\"]"))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "http://localhost/books/123456789"));
  }

}
