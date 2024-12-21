import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { IncomeService } from '../services/income.service';
import { SharedModule } from '../shared/shared.module';
import { CommonModule } from '@angular/common';
import { CategoryService } from '../services/category.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-income',
  standalone: true,
  imports: [CommonModule, NavbarComponent, SharedModule],
  templateUrl: './income.component.html',
  styleUrl: './income.component.scss'
})

export class IncomeComponent implements OnInit {
  incomeForm!: FormGroup;
  categoryForm!: FormGroup;
  incomes: any[] = [];
  filteredIncome: any[] = [];
  categories: any[] = [];
  userId: number = Number(localStorage.getItem('userId'));
  selectedCategoryId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private incomeService: IncomeService,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    this.initializeForms();
    this.loadIncome();
    this.loadCategories();
  }

  initializeForms() {
    this.incomeForm = this.fb.group({
      description: [''],
      amount: [0],
      categoryId: [null],
    });

    this.categoryForm = this.fb.group({
      name: [''],
    });
  }

  loadIncome() {
    this.incomeService.getIncomeByUser(this.userId).subscribe((data) => {
      this.incomes = data;
      this.filteredIncome = [...this.incomes];
    });
  }

  loadCategories() {
    this.categoryService.getIncomeCategories(this.userId).subscribe((data) => {
      this.categories = data;
    });
  }

  filterIncomeByCategory() {
    if (this.selectedCategoryId) {
      this.incomeService
        .getIncomeByCategoryAndUser(this.selectedCategoryId, this.userId)
        .subscribe((data) => {
          this.filteredIncome = data;
        });
    } else {
      this.filteredIncome = [...this.incomes];
    }
  }

  addIncome() {
    const formValues = this.incomeForm.value;

    const newIncome = {
      amount: formValues.amount,
      description: formValues.description,
      category: { id: formValues.categoryId },
      user: { id: this.userId },
    };

    this.incomeService.addIncome(newIncome).subscribe(() => {
      this.incomeForm.reset();
      this.loadIncome();
    });
  }

  deleteIncome(id: number) {
    this.incomeService.deleteIncome(id).subscribe(() => {
      this.loadIncome();
    });
  }

  addCategory() {
    const newCategory = {
      ...this.categoryForm.value,
      userId: this.userId,
    };

    this.categoryService.addIncomeCategory(newCategory).subscribe(() => {
      this.categoryForm.reset();
      this.loadCategories();
    });
  }
}