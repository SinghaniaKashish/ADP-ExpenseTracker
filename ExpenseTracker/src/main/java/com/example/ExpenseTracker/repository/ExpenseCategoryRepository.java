// package com.example.ExpenseTracker.repository;

// import com.example.ExpenseTracker.model.ExpenseCategory;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
// }


package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    List<ExpenseCategory> findByUserId(Long userId);
}
