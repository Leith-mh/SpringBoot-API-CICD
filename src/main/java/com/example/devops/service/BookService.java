package com.example.devops.service;

import java.util.List;
import java.util.Optional;
import com.example.devops.entity.BookEntity;


public interface BookService {
    List<BookEntity> findAllBooks();
    Optional<BookEntity> findById(Long id);
    BookEntity addBook(BookEntity book);
    BookEntity updateBook(BookEntity book);
    void deleteBook(Long id);
}
