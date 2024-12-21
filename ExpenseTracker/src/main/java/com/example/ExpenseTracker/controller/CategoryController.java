package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.ExpenseCategory;
import com.example.ExpenseTracker.model.IncomeCategory;
import com.example.ExpenseTracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/expense")
    public ExpenseCategory addExpenseCategory(@RequestBody ExpenseCategory category) {
        return categoryService.addExpenseCategory(category);
    }

    @GetMapping("/expense/user/{userId}")
    public List<ExpenseCategory> getExpenseCategoriesByUser(@PathVariable Long userId) {
        return categoryService.getExpenseCategoriesByUser(userId);
    }

    @DeleteMapping("/expense/{id}")
    public void deleteExpenseCategory(@PathVariable Long id) {
        categoryService.deleteExpenseCategory(id);
    }

    @PostMapping("/income")
    public IncomeCategory addIncomeCategory(@RequestBody IncomeCategory category) {
        return categoryService.addIncomeCategory(category);
    }

    @GetMapping("/income/user/{userId}")
    public List<IncomeCategory> getIncomeCategoriesByUser(@PathVariable Long userId) {
        return categoryService.getIncomeCategoriesByUser(userId);
    }

    @DeleteMapping("/income/{id}")
    public void deleteIncomeCategory(@PathVariable Long id) {
        categoryService.deleteIncomeCategory(id);
    }
}
