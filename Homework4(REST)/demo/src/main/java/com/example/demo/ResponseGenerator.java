package com.example.demo;
import com.example.demo.DTO.BookDTO;
import com.example.demo.DTO.BookModel;
import com.example.demo.DTO.BookResponseDTO;
import com.example.demo.Storage.Storage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseGenerator {

    public List<BookResponseDTO> doFilterByTitle(final String title){
        List<BookResponseDTO> result = new ArrayList<>();
        Storage.getInstance().getBooksStorage().forEach(bookModel -> {
            if(bookModel.getTitle().contains(title)) result.add(BookResponseDTO.of(bookModel.getTitle(),bookModel.getIsbn(),bookModel.getAuthor()));
        });
            return result;
    }

    public BookResponseDTO doCreateBook(final BookDTO bookDTO) {
        return BookResponseDTO.of(bookDTO.getTitle(), bookDTO.getIsbn(), bookDTO.getAuthor());
    }

}
