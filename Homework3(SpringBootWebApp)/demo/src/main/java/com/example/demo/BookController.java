package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private static List<BookModel> booksStorage = new ArrayList<BookModel>();

    @RequestMapping({ "/", "" })
    public String defaultRedirect() {
        return "redirect:/create-new-book";
    }

    @RequestMapping(value = "/create-new-book", method = RequestMethod.GET)
    public String createNewBookGet() {
        return "book-form-controller-get";
    }

    @RequestMapping(value = "/create-new-book", method = RequestMethod.POST)
    public String createNewBookPost(@ModelAttribute BookModel bookModel, Model model) {
        model.addAttribute("bookModel", bookModel);
        booksStorage.add(bookModel);
        return "redirect:/books-list";
    }

    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(@ModelAttribute BookModel bookModel, Model model){
        model.addAttribute("booksList", booksStorage);
        return "books-list";
    }
}
