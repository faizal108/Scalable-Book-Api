package com.faizal108.bookService.repositories;

import com.faizal108.commanEntity.book.Book;
import com.faizal108.commanEntity.user.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {
    @Query("SELECT user.id FROM Loan  WHERE dueDate < :currentDate AND isReturned IS FALSE")
    List<UUID> findAllDueBooksUser(@Param("currentDate") Date currentDate);
}
