import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ExpenseService {
  private apiUrl = 'http://localhost:8080/api/expenses';

  constructor(private http: HttpClient) {}

  // Add an expense
  addExpense(expense: any): Observable<any> {
    return this.http.post(this.apiUrl, expense);
  }

  // Get expenses by user
  getExpensesByUser(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/user/${userId}`);
  }

  // Get expenses by category and user
  getExpensesByCategoryAndUser(categoryId: number, userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/category/${categoryId}/user/${userId}`);
  }

  // Delete an expense
  deleteExpense(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
