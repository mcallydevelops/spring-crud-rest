package com.develops.repos;

import com.develops.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepo extends CrudRepository<Book, String> {
    Optional<Book> findByIsn(String isn);
}
