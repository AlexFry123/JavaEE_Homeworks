package com.example.demo.Storage;

import com.example.demo.DTO.BookModel;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static List<BookModel> booksStorage;
    private static final Storage SINGLE_INSTANCE = new Storage();

    private Storage() {
        booksStorage = new ArrayList<BookModel>();
    }

    public static Storage getInstance() {
        return SINGLE_INSTANCE;
    }

    public List<BookModel> getBooksStorage() {
        return booksStorage;
    }

    public void addBooks(BookModel model){
        booksStorage.add(model);
    }

}
