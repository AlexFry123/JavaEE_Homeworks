package com.example.repository;

import com.example.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;


public interface BookRepository extends Repository<Book, String> {
    void save(Book book);

    Optional<Book> findByIsbn(String isbn);

    Collection<Book> findAll();

    Page<Book> findAll(Pageable pageable);
}