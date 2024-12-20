package com.example.ExpenseTracker.service;


import com.example.ExpenseTracker.model.ExpenseCategory;
import com.example.ExpenseTracker.model.IncomeCategory;
import com.example.ExpenseTracker.repository.ExpenseCategoryRepository;
import com.example.ExpenseTracker.repository.IncomeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private IncomeCategoryRepository incomeCategoryRepository;

    public ExpenseCategory addExpenseCategory(ExpenseCategory category) {
        return expenseCategoryRepository.save(category);
    }

    public IncomeCategory addIncomeCategory(IncomeCategory category) {
        return incomeCategoryRepository.save(category);
    }

    public void deleteExpenseCategory(Long id) {
        expenseCategoryRepository.deleteById(id);
    }

    public void deleteIncomeCategory(Long id) {
        incomeCategoryRepository.deleteById(id);
    }
}

