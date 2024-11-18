package com.example.library.dto;

import java.util.List;


public class BookDTO {
    private Long bookId;
    private String title;
    private Integer publicationYear;
    private Boolean available;
    private AuthorDTO author;
    private List<GenreDTO> genres;


    public BookDTO(Long bookId, String title, Integer publicationYear, Boolean available,
                   AuthorDTO author, List<GenreDTO> genres) {
        this.bookId = bookId;
        this.title = title;
        this.publicationYear = publicationYear;
        this.available = available;
        this.author = author;
        this.genres = genres;
    }


    public Long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Boolean getAvailable() {
        return available;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

}