package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);

    List<Expense> findByCategoryIdAndUserId(Long categoryId, Long userId);

    @Query("SELECT e.category.name AS category, SUM(e.amount) AS totalAmount " +
           "FROM Expense e " +
           "WHERE e.user.id = :userId AND MONTH(e.timeStamp) = :month AND YEAR(e.timeStamp) = :year " +
           "GROUP BY e.category.name")
    List<Object[]> getExpensesByCategoryForMonth(@Param("userId") Long userId,
                                                 @Param("month") int month,
                                                 @Param("year") int year);

    @Query("SELECT MONTH(e.timeStamp) AS month, e.category.name AS category, SUM(e.amount) AS totalAmount " +
           "FROM Expense e " +
           "WHERE e.user.id = :userId AND YEAR(e.timeStamp) = :year " +
           "GROUP BY MONTH(e.timeStamp), e.category.name")
    List<Object[]> getExpensesByCategoryForYear(@Param("userId") Long userId, @Param("year") int year);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId AND MONTH(e.timeStamp) = :month AND YEAR(e.timeStamp) = :year")
    Double getTotalExpensesForMonth(@Param("userId") Long userId,
                                    @Param("month") int month,
                                    @Param("year") int year);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId AND YEAR(e.timeStamp) = :year")
    Double getTotalExpensesForYear(@Param("userId") Long userId, 
                                   @Param("year") int year);


    @Query("SELECT e.user.id AS userId, SUM(e.amount) AS totalExpenses " +
           "FROM Expense e " +
           "WHERE MONTH(e.timeStamp) = :month AND YEAR(e.timeStamp) = :year " +
           "GROUP BY e.user.id")
    List<Object[]> getAnonymizedExpensesForMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId")
    Double getLifetimeTotalExpenses(@Param("userId") Long userId);

     // Get total expenses by category across all users
     @Query("SELECT e.category.name, SUM(e.amount) FROM Expense e GROUP BY e.category.name")
     List<Object[]> getAnonymizedExpenseSummaryByCategory();
 
     // Get total expenses per month across all users
     @Query("SELECT MONTH(e.timeStamp), SUM(e.amount) FROM Expense e GROUP BY MONTH(e.timeStamp)")
     List<Object[]> getAnonymizedMonthlyExpenseSummary();

    
}
