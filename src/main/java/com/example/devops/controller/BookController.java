package com.example.devops.controller;

import com.example.devops.entity.BookEntity;
import com.example.devops.repository.BookRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book")
    public ResponseEntity<List<BookEntity>> getBooks() {
        try {
            return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the book by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/book/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable("id") long id) {
        try {
            // check if book exist in database
            BookEntity bookObj = getbookRec(id);

            if (bookObj != null) {
                return new ResponseEntity<>(bookObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Create new book
     *
     * @param book
     * @return ResponseEntity
     */
    @PostMapping("/book")
    public ResponseEntity<BookEntity> newBook(@RequestBody BookEntity book) {
        BookEntity newBook = bookRepository
                .save(BookEntity.builder()
                        .name(book.getName())
                        .author(book.getAuthor())
                        .genre(book.getGenre())
                        .build());
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    /**
     * Update Book record by using it's id
     *
     * @param id
     * @param book
     * @return
     */
    @PutMapping("/book/{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable("id") long id, @RequestBody BookEntity bookEntity) {

        // check ifbook exist in database
        BookEntity bookObj = getbookRec(id);

        if (bookObj != null) {
            bookObj.setName(bookEntity.getName());
            bookObj.setAuthor(bookEntity.getAuthor());
            bookObj.setGenre(bookEntity.getGenre());
            return new ResponseEntity<>(bookRepository.save(bookObj), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete book by Id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") long id) {
        try {
            // check if book exist in database
            BookEntity book = getbookRec(id);

            if (book != null) {
                bookRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete all books
     *
     * @return ResponseEntity
     */
    @DeleteMapping("/book")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Method to get the book record by id
     *
     * @param id
     * @return BookEntity
     */
    private BookEntity getbookRec(long id) {
        Optional<BookEntity> bookObj = bookRepository.findById(id);

        if (bookObj.isPresent()) {
            return bookObj.get();
        }
        return null;
    }

}
