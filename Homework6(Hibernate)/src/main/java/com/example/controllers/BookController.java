package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.service.BookService;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books-list";
    }

    @GetMapping(value = "/book/{isbn}")
    public String bookPage(
            @PathVariable final String isbn,
            Model model
    ){
        model.addAttribute("book",bookService.getBookByIsbn(isbn));
        return "book-show";
    }

}
