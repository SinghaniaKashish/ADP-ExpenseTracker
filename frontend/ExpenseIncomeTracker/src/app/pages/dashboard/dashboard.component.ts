import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';
import { NavbarComponent } from "../../navbar/navbar.component";
import { DashboardService } from '../../services/dashboard.service';
import { ChartOptions, ChartType } from 'chart.js';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [SharedModule, NavbarComponent, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {

  fullName: string | null = localStorage.getItem('fullName');
  summaryCards: { title: string; value: string }[] = [];
  monthlyChartData: any = {};  // Update to hold chart data structure
  monthlyChartLabels: string[] = [];
  yearlyChartData: any = {};  // Update to hold chart data structure
  yearlyChartLabels: string[] = [];
  anonymizedData: { category: string; total: number }[] = [];
  displayedColumns: string[] = ['category', 'total'];
  chartOptions: ChartOptions = {
    responsive: true,
    maintainAspectRatio: false,
  };
  monthlyChartType: ChartType = 'pie';
  yearlyChartType: ChartType = 'bar';


  constructor(private router: Router, private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.loadSummaryCards();
    this.loadMonthlyData();
    this.loadYearlyData();
    this.loadAnonymizedData();
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }


  loadSummaryCards() {
    this.dashboardService.getLifetimeSummary(1).subscribe((data) => {
      this.summaryCards = [
        { title: 'Total Expenses', value: `${data.totalExpenses || 0}` },
        { title: 'Total Income', value: `${data.totalIncome || 0}` },
        { title: 'Savings', value: `${data.savings || 0}` },
      ];
    });
  }

  loadMonthlyData() {
    this.dashboardService.getExpenseSummaryByCategoryForMonth(1, 12, 2024).subscribe((data) => {
    //   this.monthlyChartLabels = Object.keys(data);
    //   this.monthlyChartData = Object.values(data);
    // });
    // Structure the monthly data properly for chart
    this.monthlyChartLabels = Object.keys(data);
    this.monthlyChartData = {
      datasets: [
        {
          data: Object.values(data),
          label: 'Monthly Expenses',
          backgroundColor: 'rgba(63,81,181,0.5)',
          borderColor: 'rgba(63,81,181,1)',
          borderWidth: 1,
        },
      ],
    };
  });
  }

  // loadYearlyData() {
  //   this.dashboardService.getExpenseSummaryForYear(1, 2024).subscribe((data) => {
  //     this.yearlyChartLabels = Object.keys(data) as string[];
  //     this.yearlyChartData = Object.values(data).map((item: any) =>
  //       (Object.values(item) as number[]).reduce((a, b) => a + b, 0)
  //     );
  //   });
  // }
  loadYearlyData() {
    this.dashboardService.getExpenseSummaryForYear(1, 2024).subscribe((data) => {
      // Structure the yearly data properly for chart
      this.yearlyChartLabels = Object.keys(data) as string[];
      this.yearlyChartData = {
        datasets: [
          {
            data: Object.values(data).map((item: any) =>
              (Object.values(item) as number[]).reduce((a, b) => a + b, 0)
            ),
            label: 'Yearly Expenses',
            backgroundColor: 'rgba(76,175,80,0.5)',
            borderColor: 'rgba(76,175,80,1)',
            borderWidth: 1,
          },
        ],
      };
    });
  }

  loadAnonymizedData() {
    this.dashboardService.getAnonymizedExpenseSummaryByCategory().subscribe((data) => {
      this.anonymizedData = Object.entries(data).map(([category, total]) => ({
        category,
        total: total as number,
      }));
    });
  }
}

