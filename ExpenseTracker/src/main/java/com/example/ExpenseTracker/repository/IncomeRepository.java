package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUserId(Long userId);
    List<Income> findByCategoryIdAndUserId(Long categoryId, Long userId);

    @Query("SELECT i.category.name AS category, SUM(i.amount) AS totalAmount " +
           "FROM Income i " +
           "WHERE i.user.id = :userId AND MONTH(i.timeStamp) = :month AND YEAR(i.timeStamp) = :year " +
           "GROUP BY i.category.name")
    List<Object[]> getIncomesByCategoryForMonth(@Param("userId") Long userId,
                                                @Param("month") int month,
                                                @Param("year") int year);

    @Query("SELECT MONTH(i.timeStamp) AS month, i.category.name AS category, SUM(i.amount) AS totalAmount " +
           "FROM Income i " +
           "WHERE i.user.id = :userId AND YEAR(i.timeStamp) = :year " +
           "GROUP BY MONTH(i.timeStamp), i.category.name")
    List<Object[]> getIncomesByCategoryForYear(@Param("userId") Long userId, @Param("year") int year);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user.id = :userId AND MONTH(i.timeStamp) = :month AND YEAR(i.timeStamp) = :year")
    Double getTotalIncomeForMonth(@Param("userId") Long userId,
                                  @Param("month") int month,
                                  @Param("year") int year);

    @Query("SELECT i.user.id AS userId, SUM(i.amount) AS totalIncome " +
           "FROM Income i " +
           "WHERE MONTH(i.timeStamp) = :month AND YEAR(i.timeStamp) = :year " +
           "GROUP BY i.user.id")
    List<Object[]> getAnonymizedIncomeForMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user.id = :userId")
    Double getLifetimeTotalIncome(@Param("userId") Long userId);

       // Get total income by category across all users
    @Query("SELECT i.category.name, SUM(i.amount) FROM Income i GROUP BY i.category.name")
    List<Object[]> getAnonymizedIncomeSummaryByCategory();

    // Get total incomes per month across all users
    @Query("SELECT MONTH(i.timeStamp), SUM(i.amount) FROM Income i GROUP BY MONTH(i.timeStamp)")
    List<Object[]> getAnonymizedMonthlyIncomeSummary();
}