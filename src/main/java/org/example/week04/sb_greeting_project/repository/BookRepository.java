package org.example.week04.sb_greeting_project.repository;

import com.webservice.week04.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book update(Book book);

    void deleteById(Long id);
}