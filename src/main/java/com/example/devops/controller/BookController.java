package com.example.devops.controller;


import com.example.devops.entity.BookEntity;
import com.example.devops.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<BookEntity> findAllBooks(){
        return bookService.findAllBooks();
    }
    @GetMapping("/{id}")
    public Optional<BookEntity> findBookById(@PathVariable("id") Long id){
        return bookService.findById(id);
    }

    @PostMapping
    public BookEntity addBook(@RequestBody BookEntity bookEntity){
        return bookService.addBook(bookEntity);
    }

    @PutMapping
    public BookEntity updateBook(@RequestBody BookEntity bookEntity){
        return  bookService.updateBook(bookEntity);
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable("id") Long id){
        bookService.deleteBook(id);
    }

}
