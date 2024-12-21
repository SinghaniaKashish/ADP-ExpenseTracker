package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/expenses/summary/month")
    public Map<String, Double> getExpenseSummaryByCategoryForMonth(
            @RequestParam Long userId,
            @RequestParam int month,
            @RequestParam int year) {
        return dashboardService.getExpenseSummaryByCategoryForMonth(userId, month, year);
    }

    @GetMapping("/expenses/summary/year")
    public Map<String, Map<String, Double>> getExpenseSummaryForYear(
            @RequestParam Long userId,
            @RequestParam int year) {
        return dashboardService.getExpenseSummaryForYear(userId, year);
    }

    @GetMapping("/incomes/summary/month")
    public Map<String, Double> getIncomeSummaryByCategoryForMonth(
            @RequestParam Long userId,
            @RequestParam int month,
            @RequestParam int year) {
        return dashboardService.getIncomeSummaryByCategoryForMonth(userId, month, year);
    }

    @GetMapping("/incomes/summary/year")
    public Map<String, Map<String, Double>> getIncomeSummaryForYear(
            @RequestParam Long userId,
            @RequestParam int year) {
        return dashboardService.getIncomeSummaryForYear(userId, year);
    }

    @GetMapping("/savings/summary/month")
    public double getSavingsForMonth(
            @RequestParam Long userId,
            @RequestParam int month,
            @RequestParam int year) {
        return dashboardService.getSavingsForMonth(userId, month, year);
    }

    @GetMapping("/summary/lifetime")
    public Map<String, Double> getLifetimeSummary(@RequestParam Long userId) {
        return dashboardService.getLifetimeSummary(userId);
    }


    // Get anonymized expense summary by category
    @GetMapping("/expenses/anonymized-summary/category")
    public Map<String, Double> getAnonymizedExpenseSummaryByCategory() {
        return dashboardService.getAnonymizedExpenseSummaryByCategory();
    }

    // Get anonymized income summary by category
    @GetMapping("/incomes/anonymized-summary/category")
    public Map<String, Double> getAnonymizedIncomeSummaryByCategory() {
        return dashboardService.getAnonymizedIncomeSummaryByCategory();
    }

    // Get expense-to-income ratio for a user
    @GetMapping("/ratio/expense-to-income")
    public double getExpenseToIncomeRatio(@RequestParam Long userId) {
        return dashboardService.getExpenseToIncomeRatio(userId);
    }
}
