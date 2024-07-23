package com.faizal108.bookService.schedulars;

import com.faizal108.bookService.repositories.BookRepository;
import com.faizal108.bookService.repositories.LoanRepository;
import com.faizal108.bookService.services.BookService;
import com.faizal108.commanEntity.book.Book;
import com.faizal108.commanEntity.user.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class BookSchedular {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BookService bookService;

    private List<UUID> users;

//    This Method will run on 9am everyday
    @Scheduled(cron = "0 24 13 * * ?")
    private void getAllDueBooks(){

        try{
            Calendar cal = Calendar.getInstance();
            Date currentDate = cal.getTime();
            this.users = loanRepository.findAllDueBooksUser(currentDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Finding Done!! sc - 1");
    }

//    This Method will run on 9:05am everyday
    @Scheduled(cron = "30 24 13 * * ?")
    private void addFinesToUser(){

        for (UUID userID: users) {
            try{
                if(bookService.addFines(userID)){
                    System.out.println("User Fines Updated!!");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
