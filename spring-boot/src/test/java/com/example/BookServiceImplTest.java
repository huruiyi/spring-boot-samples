package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.model.Book;
import com.example.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookServiceImplTest {

  private BookServiceImpl service;

  @BeforeEach
  public void setup() {
    this.service = new BookServiceImpl();
  }

  @Test
  void shouldReturnEmptyOptionalWhenNotFound() {
    assertThat(service.find("1234")).isEmpty();
  }

  @Test
  void shouldFindAfterCreation() {
    assertThat(service.findAll()).isEmpty();

    Book book = new Book("1234", "Spring 5 Recipes", "Marten Deinum", "Josh Long");
    assertThat(service.create(book)).isEqualTo(book);
    assertThat(service.find(book.getIsbn())).contains(book);
  }

  @Test
  void shouldReturnAllBooksAfterCreation() {
    assertThat(service.findAll()).isEmpty();

    Book book1 = new Book("1234", "Spring 5 Recipes", "Marten Deinum", "Josh Long");
    Book book2 = new Book("4321", "Spring Boot 2 Recipes", "Marten Deinum");
    service.create(book1);
    service.create(book2);

    assertThat(service.findAll()).containsExactly(book1, book2);
  }

}
