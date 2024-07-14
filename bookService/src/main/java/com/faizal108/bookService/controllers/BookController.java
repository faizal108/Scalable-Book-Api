package com.faizal108.bookService.controllers;

import com.faizal108.bookService.services.BookService;
import com.faizal108.bookService.utils.ResponseModel;
import com.faizal108.bookService.utils.model.AddBookModel;
import com.faizal108.bookService.utils.model.UpdateBookModel;
import com.faizal108.bookService.utils.model.UpdateBookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;


    /*
     * Get Mappings
     * */

    @GetMapping("/ping")
    public String ping() {
        return "Alive!";
    }

    @GetMapping("/all")
    public ResponseModel getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseModel getById(@PathVariable UUID id) {
        return bookService.getById(id);
    }

    @GetMapping("/title/{title}")
    public ResponseModel getByTitle(@PathVariable String title) {
        return bookService.getByTitle(title);
    }

    @GetMapping("/genre")
    public ResponseModel getByGenre(@RequestParam List<String> genre) {
        return bookService.getByGenre(genre);
    }

    @GetMapping("/loaned")
    public ResponseModel getAllLoanedBook() {
        return bookService.getAllLoanedBook();
    }


    /*
    * Post Mappings
    * */

    @PostMapping("/add")
    public ResponseModel addBook(@RequestBody AddBookModel book) {
        return bookService.addBook(book);
    }

    /*
     * Put Mappings
     * */

    @PutMapping("/update")
    public ResponseModel updateBook(@RequestBody UpdateBookModel book) {
        return bookService.updateBook(book);
    }

    @PutMapping("/bookloaned")
    public ResponseModel bookLoaned(@RequestBody UpdateBookStatus status) {
        return bookService.bookLoaned(status);
    }

    @PutMapping("/return/{bookId}")
    public ResponseModel returnBook(@PathVariable UUID bookId) {
        return bookService.returnBook(bookId);
    }

    @PutMapping("/finePaid/{userId}")
    public ResponseModel finePaid(@PathVariable UUID userId) {
        return bookService.finePaid(userId);
    }

    /*
     * Delete Mappings
     * */

    @DeleteMapping("/delete/{id}")
    public ResponseModel deleteBook(@PathVariable UUID id) {
        return bookService.deleteBook(id);
    }
}
