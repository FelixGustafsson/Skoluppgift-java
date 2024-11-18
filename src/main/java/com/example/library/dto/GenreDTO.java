package com.example.library.dto;

public class GenreDTO {
    private Long genreId;
    private String name;

    public GenreDTO(Long genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
    }

    public String getName() {
        return name;
    }
}