package com.faizal108.bookService.services.impl;

import com.faizal108.bookService.repositories.BookRepository;
import com.faizal108.bookService.repositories.LoanRepository;
import com.faizal108.bookService.repositories.UserRepository;
import com.faizal108.bookService.services.BookService;
import com.faizal108.bookService.utils.CommonUtils;
import com.faizal108.bookService.utils.ResponseModel;
import com.faizal108.bookService.utils.model.AddBookModel;
import com.faizal108.bookService.utils.model.UpdateBookModel;
import com.faizal108.bookService.utils.model.UpdateBookStatus;
import com.faizal108.commanEntity.book.Book;
import com.faizal108.commanEntity.user.Loan;
import com.faizal108.commanEntity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseModel addBook(AddBookModel book) {
        try{
            Book book1 = book.ConvertToBook();
            bookRepository.save(book1);
            return CommonUtils.create(null,HttpStatus.OK);
        }catch (Exception e){
            return CommonUtils.create(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel deleteBook(UUID id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        try{
            if(existingBook.isPresent()){
                bookRepository.deleteById(id);
                return CommonUtils.create(null,HttpStatus.OK);
            }
            return CommonUtils.create(null,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return CommonUtils.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel updateBook(UpdateBookModel book) {

        Optional<Book> existingBook = bookRepository.findById(book.getId());

        try{
            if(existingBook.isPresent()){
                existingBook.get().setTitle(book.getTitle());
                existingBook.get().setAuthor(book.getAuthor());
                existingBook.get().setGenre(book.getGenre());
                bookRepository.save(existingBook.get());
                return CommonUtils.create(null,HttpStatus.OK);
            }
            return CommonUtils.create(null,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return CommonUtils.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel getAllBooks() {
        try{
            return CommonUtils.create(bookRepository.findAll(),HttpStatus.OK);
        }catch (Exception e) {
            return CommonUtils.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel getById(UUID id) {

        Optional<Book> existingBook = bookRepository.findById(id);
        try{
            return existingBook.map(book -> CommonUtils.create(book, HttpStatus.OK)).orElseGet(() -> CommonUtils.create(null, HttpStatus.NOT_FOUND));
        }catch (Exception e){
            return CommonUtils.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel getByTitle(String title) {

        Optional<Book> existingBook = bookRepository.findByTitle(title);
        try{
            return existingBook.map(book -> CommonUtils.create(book,HttpStatus.OK)).orElseGet(() -> CommonUtils.create(null,HttpStatus.NOT_FOUND));
        }catch (Exception e){
            return CommonUtils.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseModel getByGenre(List<String> genre) {
        return null;
    }

    @Override
    public ResponseModel getAllLoanedBook() {

        try{
            List<Book> allBook = bookRepository.findAllByIsAvailable(false) ;
            return CommonUtils.create(allBook,HttpStatus.OK);
        }catch (Exception e){
            return CommonUtils.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean addFines(UUID userID) {

        try{
                Optional<User> user = userRepository.findById(userID);
                if(user.isPresent()){
                    float currentFine = user.get().getBookFines();
                    user.get().setBookFines(currentFine + 100);
                    userRepository.save(user.get());
                }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ResponseModel finePaid(UUID id){
        try{
            Optional<User> user = userRepository.findById(id);

            if(user.isPresent()){
                user.get().setBookFines(0f);
                userRepository.save(user.get());
                return CommonUtils.create(null,HttpStatus.NOT_FOUND);
            }
            return CommonUtils.create(null,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return CommonUtils.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    * Transactional Operations
    * */

    @Transactional
    @Override
    public ResponseModel bookLoaned(UpdateBookStatus status) {
        try{
            Optional<Book> existingBook = bookRepository.findByIdAndIsAvailable(status.getBookId(),true);
            if(existingBook.isPresent()) {
                existingBook.get().setAvailable(false);
                bookRepository.save(existingBook.get());

                Optional<User> user = userRepository.findById(status.getUserId());

                if(user.isPresent()){
                    Loan loan = new Loan();
                    loan.setBook(existingBook.get());
                    loan.setUser(user.get());
                    loan.setIsReturned(false);
                    loanRepository.save(loan);
                }
                return CommonUtils.create(null,HttpStatus.OK);
            }
            return CommonUtils.create(null,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return CommonUtils.create(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public ResponseModel returnBook(UUID bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        return bookOpt.map(b -> {
            b.setAvailable(true);

            // Fetch the loan associated with the book and update the isReturned status
            if (b.getLoans() != null && !b.getLoans().isEmpty()) {
                Loan loan = b.getLoans().get(0); // Assuming the first loan is the one to be updated
                loan.setIsReturned(true);
                loanRepository.save(loan);
            }

            bookRepository.save(b);
            return CommonUtils.create(null, HttpStatus.OK);
        }).orElseGet(() -> CommonUtils.create(null, HttpStatus.NOT_FOUND));
    }
}
