package com.example.library.dto;

import java.util.List;

public class BookRequestDTO {
    private String title;
    private int publicationYear;
    private boolean available;
    private Long authorId;
    private List<Long> genreIds;

    public BookRequestDTO(String title, int publicationYear, boolean available, Long authorId, List<Long> genreIds) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.available = available;
        this.authorId = authorId;
        this.genreIds = genreIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public Long getAuthorId() {
        return authorId;
    }
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    public List<Long> getGenreIds() {
        return genreIds;
    }
    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }


}