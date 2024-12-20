package com.example.ExpenseTracker.service;

import org.springframework.beans.factory.annotation.Autowired;

// public class DashboardService {
    
// }
import org.springframework.stereotype.Service;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import com.example.ExpenseTracker.repository.IncomeRepository;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    public double getTotalIncome(Long userId) {
        List<Income> incomes = incomeRepository.findByUserId(userId);
        return incomes.stream().mapToDouble(Income::getAmount).sum();
    }

    public double getTotalExpenses(Long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getNetSavings(Long userId) {
        return getTotalIncome(userId) - getTotalExpenses(userId);
    }

    // Implement other methods for monthly and yearly breakdown
}
