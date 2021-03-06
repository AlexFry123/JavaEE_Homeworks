package com.example.demo;


import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.BookModel;
import com.example.demo.DTO.BookResponseDTO;
import com.example.demo.Storage.Storage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookListSearchController {

    private final ResponseGenerator responseGenerator;

    @ResponseBody
    @RequestMapping(value = "/search-by-title", method = RequestMethod.POST)
    public ResponseEntity<List<BookResponseDTO>> searchByTitle(
            @RequestBody final BookDTO bookDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseGenerator.doFilterByTitle(bookDTO.getTitle()));
    }

}
