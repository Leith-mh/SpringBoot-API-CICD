package com.example.devops.service.impl;

import com.example.devops.repository.BookRepository;
import com.example.devops.service.BookService;
import com.example.devops.entity.BookEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public BookEntity updateBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);

    }
}
