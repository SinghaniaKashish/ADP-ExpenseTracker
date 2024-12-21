// package com.example.ExpenseTracker.service;


// import com.example.ExpenseTracker.model.ExpenseCategory;
// import com.example.ExpenseTracker.model.IncomeCategory;
// import com.example.ExpenseTracker.repository.ExpenseCategoryRepository;
// import com.example.ExpenseTracker.repository.IncomeCategoryRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class CategoryService {
//     @Autowired
//     private ExpenseCategoryRepository expenseCategoryRepository;

//     @Autowired
//     private IncomeCategoryRepository incomeCategoryRepository;

//     public ExpenseCategory addExpenseCategory(ExpenseCategory category) {
//         return expenseCategoryRepository.save(category);
//     }

//     public IncomeCategory addIncomeCategory(IncomeCategory category) {
//         return incomeCategoryRepository.save(category);
//     }

//     public void deleteExpenseCategory(Long id) {
//         expenseCategoryRepository.deleteById(id);
//     }

//     public void deleteIncomeCategory(Long id) {
//         incomeCategoryRepository.deleteById(id);
//     }
// }

package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.model.ExpenseCategory;
import com.example.ExpenseTracker.model.IncomeCategory;
import com.example.ExpenseTracker.repository.ExpenseCategoryRepository;
import com.example.ExpenseTracker.repository.IncomeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    private IncomeCategoryRepository incomeCategoryRepository;

    public ExpenseCategory addExpenseCategory(ExpenseCategory category) {
        return expenseCategoryRepository.save(category);
    }

    public List<ExpenseCategory> getExpenseCategoriesByUser(Long userId) {
        return expenseCategoryRepository.findByUserId(userId);
    }

    public void deleteExpenseCategory(Long id) {
        expenseCategoryRepository.deleteById(id);
    }

    public IncomeCategory addIncomeCategory(IncomeCategory category) {
        return incomeCategoryRepository.save(category);
    }

    public List<IncomeCategory> getIncomeCategoriesByUser(Long userId) {
        return incomeCategoryRepository.findByUserId(userId);
    }

    public void deleteIncomeCategory(Long id) {
        incomeCategoryRepository.deleteById(id);
    }
}
