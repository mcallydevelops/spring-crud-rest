package com.develops.controllers;

import com.develops.models.Book;
import com.develops.repos.BookRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        Optional<Book> dbResult = bookRepo.findByIsn(isn);
        Book result = dbResult.isPresent() ? dbResult.get() : new Book();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/book/isn/{isn}")
    public ResponseEntity<Book> findByIdPathVariable(@PathVariable(value="isn") String isn) {
        Optional<Book> dbResult = bookRepo.findByIsn(isn);
        Book result = dbResult.isPresent() ? dbResult.get() : new Book();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> insertBook(@RequestBody BookRequest bookRequest) {
        Book requestBook = new Book(bookRequest.getIsn(), bookRequest.getTitle(), bookRequest.getPublisher(), bookRequest.getPublishedDate());
        Optional<Book> dbResult = bookRepo.findByIsn(bookRequest.getIsn());
        if(dbResult.isPresent()) {
            Book updatedBook = dbResult.get();
            requestBook.setId(updatedBook.getId());
            requestBook.setIsn(updatedBook.getIsn());
            Book savedBook = bookRepo.save(requestBook);
            return ResponseEntity.ok(savedBook);
        }
        Book savedBook = bookRepo.save(requestBook);
        return ResponseEntity.ok(savedBook);
    }

}
