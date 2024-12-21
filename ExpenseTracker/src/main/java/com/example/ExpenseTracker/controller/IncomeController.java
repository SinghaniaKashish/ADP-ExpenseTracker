package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Income;
import com.example.ExpenseTracker.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public Income addIncome(@RequestBody Income income) {
    income.setTimeStamp(LocalDateTime.now());
        return incomeService.addIncome(income);
    }

    @GetMapping("/user/{userId}")
    public List<Income> getIncomesByUser(@PathVariable Long userId) {
        return incomeService.getIncomesByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
    }

    @GetMapping("/category/{categoryId}/user/{userId}")
    public List<Income> getIncomeByCategoryAndUser(
            @PathVariable Long categoryId,
            @PathVariable Long userId) {
        return incomeService.getIncomeByCategoryAndUser(categoryId, userId);
    }
}
