import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private apiUrl = 'http://localhost:8080/api/categories';

  constructor(private http: HttpClient) {}

  getExpenseCategories(userId: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/categories/expense/user/${userId}`);
  }
  
  getIncomeCategories(userId: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/categories/income/user/${userId}`);
  }
  
  addExpenseCategory(category: { name: string, userId: number }): Observable<any> {
    return this.http.post(`http://localhost:8080/api/categories/expense`, category);
  }
  
  addIncomeCategory(category: { name: string, userId: number }): Observable<any> {
    return this.http.post(`http://localhost:8080/api/categories/income`, category);
  }
  
  deleteExpenseCategory(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/categories/expense/${id}`);
  }
  
  deleteIncomeCategory(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/categories/income/${id}`);
  }
  
}
