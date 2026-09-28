package org.example.week04.repository;

import org.example.week04.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryBookRepository implements BookRepository {

    private Map<Long, Book> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Book save(Book book) {
        sequence = sequence + 1;
        book.setId(sequence);
        store.put(book.getId(), book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Book> findById(Long id) {
        Book book = store.get(id);
        return Optional.ofNullable(book);
    }

    @Override
    public Book update(Book book) {
        store.put(book.getId(), book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}