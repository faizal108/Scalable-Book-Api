package com.faizal108.bookService.services;

import com.faizal108.bookService.utils.ResponseModel;
import com.faizal108.bookService.utils.model.AddBookModel;
import com.faizal108.bookService.utils.model.UpdateBookModel;
import com.faizal108.bookService.utils.model.UpdateBookStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface BookService {

    ResponseModel addBook(AddBookModel book);
    ResponseModel deleteBook(UUID id);
    ResponseModel updateBook(UpdateBookModel book);
    ResponseModel getAllBooks();
    ResponseModel bookLoaned(UpdateBookStatus status);
    ResponseModel getById(UUID id);
    ResponseModel getByTitle(String title);
    ResponseModel getByGenre(List<String> genre);
    ResponseModel getAllLoanedBook();
//    ResponseModel getAllDueDateBooks();

    ResponseModel returnBook(UUID bookId);

    boolean addFines(UUID userID);

    ResponseModel finePaid(UUID id);
}
