package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.ExpenseCategory;
import com.example.ExpenseTracker.model.IncomeCategory;
import com.example.ExpenseTracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/expense")
    public ExpenseCategory addExpenseCategory(@RequestBody ExpenseCategory category) {
        return categoryService.addExpenseCategory(category);
    }

    @PostMapping("/income")
    public IncomeCategory addIncomeCategory(@RequestBody IncomeCategory category) {
        return categoryService.addIncomeCategory(category);
    }

    @DeleteMapping("/expense/{id}")
    public void deleteExpenseCategory(@PathVariable Long id) {
        categoryService.deleteExpenseCategory(id);
    }

    @DeleteMapping("/income/{id}")
    public void deleteIncomeCategory(@PathVariable Long id) {
        categoryService.deleteIncomeCategory(id);
    }
}
