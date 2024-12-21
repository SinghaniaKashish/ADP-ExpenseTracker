// package com.example.ExpenseTracker.repository;

// import com.example.ExpenseTracker.model.IncomeCategory;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long> {
// }
package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long> {
    List<IncomeCategory> findByUserId(Long userId);
}
