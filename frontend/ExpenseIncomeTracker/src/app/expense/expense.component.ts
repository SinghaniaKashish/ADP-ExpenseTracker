import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { SharedModule } from '../shared/shared.module';
import { ExpenseService } from '../services/expense.service';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../services/category.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-expense',
  standalone: true,
  imports: [CommonModule ,NavbarComponent , SharedModule],
  templateUrl: './expense.component.html',
  styleUrl: './expense.component.scss'
})
export class ExpenseComponent implements OnInit {
  expenseForm!: FormGroup;
  categoryForm!: FormGroup; 
  expenses: any[] = [];
  filteredExpenses: any[] = [];
  categories: any[] = [];
  userId: number = Number(localStorage.getItem('userId'));
  selectedCategoryId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private expenseService: ExpenseService,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.initializeForms();
    this.loadExpenses();
    this.loadCategories();
  }

  initializeForms() {
    this.expenseForm = this.fb.group({
      description: [''],
      amount: [0],
      categoryId: [null],
    });

    this.categoryForm = this.fb.group({
      name: [''], 
    });
  }

  loadExpenses() {
    this.expenseService.getExpensesByUser(this.userId).subscribe((data) => {
      this.expenses = data;
      this.filteredExpenses = [...this.expenses];
    });
  }

  loadCategories() {
    this.categoryService.getExpenseCategories(this.userId).subscribe((data) => {
      this.categories = data;
    });
  }

  filterExpensesByCategory() {
    if (this.selectedCategoryId) {
      this.expenseService
        .getExpensesByCategoryAndUser(this.selectedCategoryId, this.userId)
        .subscribe((data) => {
          this.filteredExpenses = data;
        });
    } else {
      this.filteredExpenses = [...this.expenses]; // Reset filter
    }
  }


  addExpense() {
    const formValues = this.expenseForm.value;
  
    const newExpense = {
      amount: formValues.amount,
      description: formValues.description,
      category: { id: formValues.categoryId }, // Send category ID
      user: { id: this.userId }, // Include user ID
    };
  
    this.expenseService.addExpense(newExpense).subscribe(() => {
      this.expenseForm.reset();
      this.loadExpenses();
    });
  }
  
  deleteExpense(id: number) {
    this.expenseService.deleteExpense(id).subscribe(() => {
      this.loadExpenses();
    });
  }

  // New method to handle category addition
  addCategory() {
    const newCategory = {
      ...this.categoryForm.value,
      userId: this.userId,
    };

    this.categoryService.addExpenseCategory(newCategory).subscribe(() => {
      this.categoryForm.reset();
      this.loadCategories(); 
    });
  }
}

