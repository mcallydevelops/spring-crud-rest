package com.develops.controllers;

import com.develops.models.Book;
import com.develops.repos.BookRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class BookController {

    private BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> retrieveBooks() {
        Iterable<Book> books = bookRepo.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/book")
    public ResponseEntity<Book> findByIdRequestParam(@RequestParam(value="isn") String isn) {
        Book response = bookRepo.findByIsn(isn);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/book/isn/{isn}")
    public ResponseEntity<Book> findByIdPathVariable(@PathVariable(value="isn") String isn) {
        Book response = bookRepo.findByIsn(isn);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> insertBook(@RequestBody BookRequest bookRequest) {
        Book book = new Book(bookRequest.getIsn(), bookRequest.getTitle(), bookRequest.getPublisher(), bookRequest.getPublishedDate());
        Book savedBook = bookRepo.save(book);
        return ResponseEntity.ok(savedBook);
    }

}
