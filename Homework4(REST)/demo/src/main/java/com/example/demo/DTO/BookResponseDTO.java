package com.example.demo.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data(staticConstructor = "of")
public class BookResponseDTO {

    private final String title;
    private final String isbn;
    private final String author;

}
