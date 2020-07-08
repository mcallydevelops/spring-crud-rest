package com.develops.controllers;

import com.develops.models.Book;
import com.develops.repos.BookRepo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    private BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> retrieveBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Iterable<Book> books = bookRepo.findAll();
        return new ResponseEntity<Iterable<Book>>(books, headers, HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<Book> findByIdRequestParam(@RequestParam(value="isn") String isn) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Optional<Book> dbResult = bookRepo.findByIsn(isn);
        if(dbResult.isPresent()) {
            return new ResponseEntity<Book>(dbResult.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<Book>(headers, HttpStatus.OK);
    }

    @GetMapping("/book/isn/{isn}")
    public ResponseEntity<Book> findByIdPathVariable(@PathVariable(value="isn") String isn) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Optional<Book> dbResult = bookRepo.findByIsn(isn);
        if(dbResult.isPresent()) {
            return new ResponseEntity<Book>(dbResult.get(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<Book>(headers, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> insertBook(@RequestBody BookRequest bookRequest) {
        Book requestBook = new Book(bookRequest.getIsn(), bookRequest.getTitle(), bookRequest.getPublisher(), bookRequest.getPublishedDate());
        Optional<Book> dbResult = bookRepo.findByIsn(bookRequest.getIsn());
        if(dbResult.isPresent()) {
            Book updatedBook = dbResult.get();
            requestBook.setId(updatedBook.getId());
            requestBook.setIsn(updatedBook.getIsn());
            Book u = bookRepo.save(requestBook);
            return ResponseEntity.ok(u);
        }
        Book savedBook = bookRepo.save(requestBook);
        return ResponseEntity.ok(savedBook);
    }

}
