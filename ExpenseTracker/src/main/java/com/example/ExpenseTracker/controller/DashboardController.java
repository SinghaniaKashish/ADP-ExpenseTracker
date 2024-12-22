package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/summary/lifetime")
    public Map<String, Double> getLifetimeSummary(@RequestParam Long userId) {
        return dashboardService.getLifetimeSummary(userId);
    }

    @GetMapping("/summary/monthly")
    public ResponseEntity<Map<String, Object>> getSummaryByMonth(
            @RequestParam Long userId, 
            @RequestParam int month, 
            @RequestParam int year) {
        Map<String, Object> summary = dashboardService.getMonthlySummary(userId, month, year);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/summary/yearly")
    public ResponseEntity<Map<String, Object>> getSummaryByYear(
            @RequestParam Long userId, 
            @RequestParam int year) {
        Map<String, Object> summary = dashboardService.getYearlySummary(userId, year);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/summary/monthly/all")
    public ResponseEntity<List<Map<String, Object>>> getSummaryByMonthForAllUsers(
        @RequestParam int month,
        @RequestParam int year) {
    List<Map<String, Object>> allUsersSummary = dashboardService.getMonthlySummaryForAllUsers(month, year);
    return ResponseEntity.ok(allUsersSummary);
}


    
    // Get expense-to-income ratio for a user
    @GetMapping("/ratio/expense-to-income")
    public double getExpenseToIncomeRatio(@RequestParam Long userId) {
        return dashboardService.getExpenseToIncomeRatio(userId);
    }
}
