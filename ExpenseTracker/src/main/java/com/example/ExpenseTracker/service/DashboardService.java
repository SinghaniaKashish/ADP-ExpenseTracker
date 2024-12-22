package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.repository.ExpenseRepository;
import com.example.ExpenseTracker.repository.IncomeRepository;
import com.example.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    public Map<String, Double> getExpenseSummaryByCategoryForMonth(Long userId, int month, int year) {
        List<Object[]> results = expenseRepository.getExpensesByCategoryForMonth(userId, month, year);
        Map<String, Double> summary = new HashMap<>();
        for (Object[] result : results) {
            summary.put((String) result[0], (Double) result[1]);
        }
        return summary;
    }

    public Map<String, Map<String, Double>> getExpenseSummaryForYear(Long userId, int year) {
        List<Object[]> results = expenseRepository.getExpensesByCategoryForYear(userId, year);
        Map<String, Map<String, Double>> summary = new HashMap<>();
        for (Object[] result : results) {
            String month = String.valueOf(result[0]);
            String category = (String) result[1];
            Double amount = (Double) result[2];
            summary.putIfAbsent(month, new HashMap<>());
            summary.get(month).put(category, amount);
        }
        return summary;
    }

    public Map<String, Double> getIncomeSummaryByCategoryForMonth(Long userId, int month, int year) {
        List<Object[]> results = incomeRepository.getIncomesByCategoryForMonth(userId, month, year);
        Map<String, Double> summary = new HashMap<>();
        for (Object[] result : results) {
            summary.put((String) result[0], (Double) result[1]);
        }
        return summary;
    }

    public Map<String, Map<String, Double>> getIncomeSummaryForYear(Long userId, int year) {
        List<Object[]> results = incomeRepository.getIncomesByCategoryForYear(userId, year);
        Map<String, Map<String, Double>> summary = new HashMap<>();
        for (Object[] result : results) {
            String month = String.valueOf(result[0]);
            String category = (String) result[1];
            Double amount = (Double) result[2];
            summary.putIfAbsent(month, new HashMap<>());
            summary.get(month).put(category, amount);
        }
        return summary;
    }

    public double getSavingsForMonth(Long userId, int month, int year) {
        Double totalIncome = incomeRepository.getTotalIncomeForMonth(userId, month, year);
        Double totalExpenses = expenseRepository.getTotalExpensesForMonth(userId, month, year);
        return (totalIncome != null ? totalIncome : 0) - (totalExpenses != null ? totalExpenses : 0);
    }

    //custom
    public Map<String, Object> getMonthlySummary(Long userId, int month, int year) {
        Double totalIncome = incomeRepository.getTotalIncomeForMonth(userId, month, year);
        Double totalExpenses = expenseRepository.getTotalExpensesForMonth(userId, month, year);
        Double savings = (totalIncome != null ? totalIncome : 0) - (totalExpenses != null ? totalExpenses : 0);

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome != null ? totalIncome : 0);
        summary.put("totalExpenses", totalExpenses != null ? totalExpenses : 0);
        summary.put("savings", savings);

        return summary;
    }

    public Map<String, Object> getYearlySummary(Long userId, int year) {
        // Get total expenses income for the year
        Double totalExpenses = expenseRepository.getTotalExpensesForYear(userId, year);
        Double totalIncome = incomeRepository.getTotalIncomeForYear(userId, year);


        // Calculate yearly savings
        Double savings = (totalIncome != null ? totalIncome : 0) - (totalExpenses != null ? totalExpenses : 0);

        // Prepare the summary map
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome);
        summary.put("totalExpenses", totalExpenses != null ? totalExpenses : 0);
        summary.put("savings", savings);

        return summary;
    }

    public Map<String, Double> getLifetimeSummary(Long userId) {
        Double totalIncome = incomeRepository.getLifetimeTotalIncome(userId);
        Double totalExpenses = expenseRepository.getLifetimeTotalExpenses(userId);
        Double savings = (totalIncome != null ? totalIncome : 0) - (totalExpenses != null ? totalExpenses : 0);
        Map<String, Double> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome != null ? totalIncome : 0);
        summary.put("totalExpenses", totalExpenses != null ? totalExpenses : 0);
        summary.put("savings", savings);
        return summary;
    }

    public List<Map<String, Object>> getMonthlySummaryForAllUsers(int month, int year) {
    List<Map<String, Object>> allUsersSummary = new ArrayList<>();

    // Fetch all user IDs from the database (you can use your UserRepository)
    List<Long> userIds = userRepository.findAllUserIds();

    // Loop through each user and get their summary
    for (Long userId : userIds) {
        Map<String, Object> summary = getMonthlySummary(userId, month, year);
        if (summary != null) {
            summary.put("userId", userId); // Add userId to the summary
            allUsersSummary.add(summary);
        }
    }

    return allUsersSummary;
}


    // Expense-to-income ratio for a user
    public double getExpenseToIncomeRatio(Long userId) {
        double totalExpenses = expenseRepository.findByUserId(userId).stream()
                .mapToDouble(expense -> expense.getAmount())
                .sum();
        double totalIncomes = incomeRepository.findByUserId(userId).stream()
                .mapToDouble(income -> income.getAmount())
                .sum();

        return totalExpenses / (totalIncomes == 0 ? 1 : totalIncomes); // Avoid division by zero
    }
}
