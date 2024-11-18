package com.example.library.controller;

import com.example.library.dto.BookDTO;
import com.example.library.dto.BookRequestDTO;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Genre;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.GenreRepository;
import com.example.library.service.BookService;
import com.example.library.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {

        if (!loginService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        BookDTO savedBook = bookService.createBook(bookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId, @RequestBody BookRequestDTO bookRequestDTO) {
        if (!loginService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        BookDTO updatedBook = bookService.updateBook(bookId, bookRequestDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        if (!loginService.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{bookId}")
    public BookDTO getBookDetails(@PathVariable Long bookId) {
        return bookService.getBookDetails(bookId);
    }

    @GetMapping("/search")
    public List<BookDTO> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) String genre) {

        return bookService.searchBooks(title, author, genre);
    }

    @GetMapping("/available/{bookId}")
    public boolean bookIsAvailable (@PathVariable Long bookId) {
        return bookService.getIsBookAvailable(bookId);
    }
}