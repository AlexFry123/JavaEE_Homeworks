package com.example.demo.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class BookModel {

    private final String title;
    private final String isbn;
    private final String author;

}
