package com.example.demo;

import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.BookModel;
import com.example.demo.DTO.BookResponseDTO;
import com.example.demo.Storage.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookCreationController {

    private final ResponseGenerator responseGenerator;

    @RequestMapping(value = "/create-new-book", method = RequestMethod.POST)
    public ResponseEntity<BookResponseDTO> createBook(
            @RequestBody final BookDTO bookDTO
    ) {
        Storage.getInstance().addBooks(BookModel.of(bookDTO.getTitle(), bookDTO.getIsbn(), bookDTO.getAuthor()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseGenerator.doCreateBook(bookDTO));
    }

}
