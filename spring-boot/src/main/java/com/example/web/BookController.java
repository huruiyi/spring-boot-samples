package com.example.web;

import com.example.model.Book;
import com.example.service.BookService;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping("/")
  public Iterable<Book> all() {
    return bookService.findAll();
  }

  @GetMapping("/list")
  public List<Book> getAllBooks() {
    return Arrays.asList(
        new Book(1000L, "Mastering Spring 5.2", "Ranga Karanam"),
        new Book(2000L, "Mastering Spring 6.2", "Ranga Karanam"));
  }

  @GetMapping("/{isbn}")
  public ResponseEntity<Book> get(@PathVariable("isbn") String isbn) {
    return bookService.find(isbn).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  /*
      {
      "isbn":"4325464573243",
      "title":"Document Info",
      "authors":[
        "authors1","authors2","authors3"
        ]
      }
  * */
  @PostMapping
  public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriBuilder) {
    Book created = bookService.create(book);
    URI newBookUri = uriBuilder.path("/books/{isbn}").build(created.getIsbn());
    return ResponseEntity.created(newBookUri).body(created);
  }

}
