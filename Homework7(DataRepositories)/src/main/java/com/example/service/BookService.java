package com.example.service;

import com.sun.istack.NotNull;
import com.example.filters.SearchFilter;
import lombok.RequiredArgsConstructor;
import com.example.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final SearchFilter searchFilter;

    @Transactional
    public Book addNewBook(@NotNull final Book book) {
        bookRepository.save(book);
        return book;
    }

    @Transactional
    public Book getBookByIsbn(@NotNull final String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (!book.isPresent())
            throw new NoSuchElementException("No book with isbn:" + isbn);
        return book.get();
    }

    @Transactional
    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Collection<Book> getBooksByTitle(@NotNull final String title) {
        return searchFilter.getBooksByTitle(title);
    }

    @Transactional
    public Page<Book> findPage(@NotNull final int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return bookRepository.findAll(pageable);
    }
}
