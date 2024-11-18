package com.example.library.service;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.BookDTO;
import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.GenreDTO;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Genre;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.GenreRepository;
import com.example.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    public BookDTO createBook(BookRequestDTO bookRequestDTO) {
        Author author = authorRepository.findById(bookRequestDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setPublicationYear(bookRequestDTO.getPublicationYear());
        book.setAvailable(bookRequestDTO.isAvailable());
        book.setAuthor(author);

        if (bookRequestDTO.getGenreIds() != null && !bookRequestDTO.getGenreIds().isEmpty()) {
            List<Genre> genres = genreRepository.findAllById(bookRequestDTO.getGenreIds());
            book.setGenres(genres);
        }

        Book savedBook = bookRepository.save(book);
        return convertToDTO(savedBook);
    }

    public BookDTO updateBook(Long bookId, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));

        book.setTitle(bookRequestDTO.getTitle());
        book.setPublicationYear(bookRequestDTO.getPublicationYear());
        book.setAvailable(bookRequestDTO.isAvailable());

        if (bookRequestDTO.getAuthorId() != null) {
            Author author = authorRepository.findById(bookRequestDTO.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + bookRequestDTO.getAuthorId()));
            book.setAuthor(author);
        }

        if (bookRequestDTO.getGenreIds() != null && !bookRequestDTO.getGenreIds().isEmpty()) {
            List<Genre> genres = genreRepository.findAllById(bookRequestDTO.getGenreIds());
            book.setGenres(genres);
        }

        return convertToDTO(bookRepository.save(book));
    }

    public boolean getIsBookAvailable(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
        if( book.isDeleted()) {
            return false;
        } else {
            return book.isAvailable();
        }

    }

    public void deleteBook(Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));

        if (book.isAvailable()) {
            book.setIsDeleted(true);
            bookRepository.save(book);
        }
    }

    public BookDTO getBookDetails(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
    if (book.isDeleted()) {
        throw new ResourceNotFoundException("Book is deleted");
    }
        return convertToDTO(book);
    }

    public BookDTO convertToDTO(Book book) {
        AuthorDTO authorDTO = null;

        if (book.getAuthor() != null) {
            authorDTO = new AuthorDTO(
                    book.getAuthor().getAuthorId(),
                    book.getAuthor().getFirstName(),
                    book.getAuthor().getLastName()
            );
        }

        List<GenreDTO> genreDTOs = book.getGenres().stream()
                .map(genre -> new GenreDTO(genre.getGenreId(), genre.getName()))
                .collect(Collectors.toList());

        return new BookDTO(
                book.getBookId(),
                book.getTitle(),
                book.getPublicationYear(),
                book.isAvailable(),
                authorDTO,
                genreDTOs
        );
    }

    public List<BookDTO> searchBooks(String title, String authorName, String genreName) {
        List<Book> books = bookRepository.findBooksByCriteria(title, authorName, genreName);

        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}