package com.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Data;


@Data
public class Book {

  private long id;
  private String name;
  private String author;

  private String isbn;
  private String title;
  private List<String> authors = new ArrayList<>();

  public Book() {
  }

  public Book(long id, String name, String author) {
    super();
    this.id = id;
    this.name = name;
    this.author = author;
  }

  public Book(String isbn, String title, String... authors) {
    this.isbn = isbn;
    this.title = title;
    this.authors.addAll(Arrays.asList(authors));
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getAuthors() {
    return Collections.unmodifiableList(authors);
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return Objects.equals(isbn, book.isbn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", author='" + author + '\'' +
        ", isbn='" + isbn + '\'' +
        ", title='" + title + '\'' +
        ", authors=" + authors +
        '}';
  }
}
