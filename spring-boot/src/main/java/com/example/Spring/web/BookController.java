package com.example.Spring.web;

import com.example.Spring.model.Book;
import com.example.Spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/hello")
    public String book() {
        return "Hello World, from Spring Boot 2!";
    }

    @GetMapping
    public Iterable<Book> all() {
        return bookService.findAll();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> get(@PathVariable("isbn") String isbn) {
        return bookService.find(isbn).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    /*
  *
    {
    "isbn":"4325464573243",
    "title":"Document Info",
    "authors":[
      "authors1","authors2","authors3"
      ]
    }
    *
    * */
    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriBuilder) {
        Book created = bookService.create(book);
        URI newBookUri = uriBuilder.path("/books/{isbn}").build(created.getIsbn());
        return ResponseEntity.created(newBookUri).body(created);
    }
}
