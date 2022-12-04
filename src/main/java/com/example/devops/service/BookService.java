package com.example.devops.service;

import com.example.devops.entity.BookEntity;
import com.example.devops.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookEntity> findAllBooks();
    Optional<BookEntity> findById(Long id);
    BookEntity addBook(BookEntity book);
    BookEntity updateBook(BookEntity book);
    void deleteBook(Long id);
}
