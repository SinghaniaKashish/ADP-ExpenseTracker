import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private apiUrl = 'http://localhost:8080/api/dashboard';

  constructor(private http: HttpClient) {}

  getLifetimeSummary(userId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/summary/lifetime?userId=${userId}`);
  }

  getExpenseSummaryByCategoryForMonth(userId: number, month: number, year: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/expenses/summary/month?userId=${userId}&month=${month}&year=${year}`);
  }

  getExpenseSummaryForYear(userId: number, year: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/expenses/summary/year?userId=${userId}&year=${year}`);
  }

  getAnonymizedExpenseSummaryByCategory(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/expenses/anonymized-summary/category`);
  }
}
