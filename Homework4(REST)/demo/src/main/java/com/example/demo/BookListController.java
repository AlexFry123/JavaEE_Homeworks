package com.example.demo;

import java.util.Arrays;
import java.util.List;

import com.example.demo.DTO.BookModel;
import com.example.demo.Storage.Storage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookListController {

    @ResponseBody
    @GetMapping("/books-list")
    public List<BookModel> getAllBooksOk() {
        return Storage.getInstance().getBooksStorage();
    }

}
