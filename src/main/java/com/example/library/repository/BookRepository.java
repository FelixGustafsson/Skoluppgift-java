package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorFirstNameContaining(String author);

    List<Book> findByGenresNameContaining(String genre);

    boolean existsByBookIdAndAvailableTrue(Long bookId);

    @Query("SELECT b FROM Book b " +
            "JOIN b.author a " +
            "JOIN b.genres g " +
            "WHERE (:title IS NULL OR b.title LIKE %:title%) " +
            "AND (:authorName IS NULL OR CONCAT(a.firstName, ' ', a.lastName) LIKE %:authorName%) " +
            "AND (:genreName IS NULL OR g.name LIKE %:genreName%)")
    List<Book> findBooksByCriteria(@Param("title") String title,
                                   @Param("authorName") String authorName,
                                   @Param("genreName") String genreName);
}