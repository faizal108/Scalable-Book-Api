package com.faizal108.bookService.repositories;

import com.faizal108.commanEntity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findByIdAndIsAvailable(UUID bookId, boolean b);

    Optional<Book> findByTitle(String title);

    List<Book> findAllByIsAvailable(boolean b);
}