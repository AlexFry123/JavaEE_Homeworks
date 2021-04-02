package com.example.controllers;

import com.example.model.Book;
import org.springframework.data.domain.Page;
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
        return paginatedHomePage(1, model);
    }

    @GetMapping(value = "/{pageNo}")
    public String paginatedHomePage(
            @PathVariable final Integer pageNo,
            Model model) {
        Page<Book> page =  bookService.findPage(pageNo, 10);
        model.addAttribute("books",page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNo);
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
