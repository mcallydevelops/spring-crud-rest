package com.develops.repos;

import com.develops.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, String> {
    Book findByIsn(String isn);
}
