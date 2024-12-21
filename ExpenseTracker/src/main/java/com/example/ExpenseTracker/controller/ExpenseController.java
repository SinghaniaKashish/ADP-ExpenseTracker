package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
            expense.setTimeStamp(LocalDateTime.now());
        return expenseService.addExpense(expense);
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUser(@PathVariable Long userId) {
        return expenseService.getExpensesByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }

    @GetMapping("/category/{categoryId}/user/{userId}")
    public List<Expense> getExpensesByCategoryAndUser(
            @PathVariable Long categoryId,
            @PathVariable Long userId) {
        return expenseService.getExpensesByCategoryAndUser(categoryId, userId);
    }
   
}
