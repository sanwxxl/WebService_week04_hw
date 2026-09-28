package org.example.week04.service;

import org.example.week04.domain.Book;
import org.example.week04.dto.BookRequest;
import org.example.week04.dto.BookResponse;
import org.example.week04.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(BookRequest request) {
        Book book = new Book(
                request.title(),
                request.author(),
                request.price(),
                request.category(),
                request.isbn()
        );
        Book savedBook = bookRepository.save(book);
        return toResponse(savedBook);
    }

    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> responses = new ArrayList<>();
        for (Book book : books) {
            responses.add(toResponse(book));
        }
        return responses;
    }

    public BookResponse getBook(Long id) {
        Book book = findBook(id);
        return toResponse(book);
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = findBook(id);
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setPrice(request.price());
        book.setCategory(request.category());
        book.setIsbn(request.isbn());
        Book updatedBook = bookRepository.update(book);
        return toResponse(updatedBook);
    }

    public void deleteBook(Long id) {
        findBook(id);
        bookRepository.deleteById(id);
    }

    private Book findBook(Long id) {
        Optional<Book> result = bookRepository.findById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "책을 찾을 수 없습니다. id=" + id);
        }
        return result.get();
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getCategory(),
                book.getIsbn()
        );
    }
}