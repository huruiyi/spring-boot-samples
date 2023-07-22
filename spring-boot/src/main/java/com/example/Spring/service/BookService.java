package com.example.Spring.service;

import com.example.Spring.model.Book;

import java.util.Optional;

public interface BookService {

  Iterable<Book> findAll();

  Book create(Book book);

  Optional<Book> find(String isbn);
}
