import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private apiUrl = 'http://localhost:8080/api/dashboard';

  constructor(private http: HttpClient) {}

  getSummaryByMonth(userId: number, month: number, year: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/summary/monthly`, {
      params: { userId, month, year },
    });
  }

  getSummaryByYear(userId: number, year: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/summary/yearly`, { params: { userId, year } });
  }

  getLifetimeSummary(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/summary/lifetime`, { params: { userId } });
  }

  getExpenseSummaryByCategoryForMonth(
    userId: number,
    month: number,
    year: number
  ): Observable<{ [key: string]: number }> {
    return this.http.get<{ [key: string]: number }>(
      `${this.apiUrl}/expenses/summary/month`,
      { params: { userId, month, year } }
    );
  }

  // API for getting income summary by category for the current month
  getIncomeSummaryByCategoryForMonth(
    userId: number,
    month: number,
    year: number
  ): Observable<{ [key: string]: number }> {
    return this.http.get<{ [key: string]: number }>(
      `${this.apiUrl}/incomes/summary/month`,
      { params: { userId, month, year } }
    );
  }


}

