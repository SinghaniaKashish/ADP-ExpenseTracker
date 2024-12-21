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
  userId: number = Number(localStorage.getItem('userId'));

  months = [
    { value: 1, label: 'January' },
    { value: 2, label: 'February' },
    { value: 3, label: 'March' },
    { value: 4, label: 'April' },
    { value: 5, label: 'May' },
    { value: 6, label: 'June' },
    { value: 7, label: 'July' },
    { value: 8, label: 'August' },
    { value: 9, label: 'September' },
    { value: 10, label: 'October' },
    { value: 11, label: 'November' },
    { value: 12, label: 'December' },
  ];
  years: number[] = [];

  // Selected dropdown values
  selectedMonth: number = new Date().getMonth() + 1;
  selectedYear: number = new Date().getFullYear();
  selectedYearForYearSummary: number = new Date().getFullYear();

  // Summaries
  monthlySummary: any = null;
  yearlySummary: any = null;
  lifetimeSummary: any = null;

 // Chart data
 expenseChartData: any = [];
 expenseChartLabels: string[] = [];
 incomeChartData: any = [];
 incomeChartLabels: string[] = [];

 // Chart options
 chartOptions: ChartOptions = {
   responsive: true,
   plugins: {
     legend: {
       position: 'top',
       labels: {
         font: {
           size: 14,
         },
       },
     },
   },
 };



  constructor(private dashboardService: DashboardService, private router: Router) {}

  ngOnInit(): void {
    // Initialize years dropdown
    this.initializeYears();

    // Load default summaries
    this.loadMonthlySummary();
    this.loadYearlySummary();
    this.loadLifetimeSummary();


    //chart
    this.loadExpenseChart();
    this.loadIncomeChart();
  }

  initializeYears(): void {
    const currentYear = new Date().getFullYear();
    for (let i = currentYear; i >= currentYear - 20; i--) {
      this.years.push(i);
    }
  }

  // Load summaries
  loadMonthlySummary(): void {
    this.dashboardService
      .getSummaryByMonth(1, this.selectedMonth, this.selectedYear)
      .subscribe((data) => {
        this.monthlySummary = data;
      });
  }

  loadYearlySummary(): void {
    this.dashboardService
      .getSummaryByYear(1, this.selectedYearForYearSummary)
      .subscribe((data) => {
        this.yearlySummary = data;
      });
  }

  loadLifetimeSummary(): void {
    this.dashboardService.getLifetimeSummary(1).subscribe((data) => {
      this.lifetimeSummary = data;
    });
  }

  // Event handlers for dropdown changes
  onMonthChange(): void {
    this.loadMonthlySummary();
  }

  onYearChange(): void {
    this.loadYearlySummary();
  }

  logout() {
        localStorage.clear();
        this.router.navigate(['/login']);
      }

      // Load expense summary data for the current month
  loadExpenseChart(): void {
    this.dashboardService
      .getExpenseSummaryByCategoryForMonth(1, this.selectedMonth, this.selectedYear)
      .subscribe((data) => {
        this.expenseChartLabels = Object.keys(data);
        this.expenseChartData = {
          datasets: [
            {
              data: Object.values(data),
              backgroundColor: this.generateRandomColors(Object.keys(data).length),
              borderWidth: 1,
            },
          ],
        };
      });
  }

  // Load income summary data for the current month
  loadIncomeChart(): void {
    this.dashboardService
      .getIncomeSummaryByCategoryForMonth(1, this.selectedMonth, this.selectedYear)
      .subscribe((data) => {
        this.incomeChartLabels = Object.keys(data);
        this.incomeChartData = {
          datasets: [
            {
              data: Object.values(data),
              backgroundColor: this.generateRandomColors(Object.keys(data).length),
              borderWidth: 1,
            },
          ],
        };
      });
  }

  // Helper function to generate random colors for chart segments
  generateRandomColors(length: number): string[] {
    const colors = [];
    for (let i = 0; i < length; i++) {
      colors.push(`rgba(${this.randomInt(0, 255)}, ${this.randomInt(0, 255)}, ${this.randomInt(0, 255)}, 0.7)`);
    }
    return colors;
  }

  randomInt(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

}


//   summaryCards: { title: string; value: string }[] = [];
//   monthlyChartData: any = {};  // Update to hold chart data structure
//   monthlyChartLabels: string[] = [];
//   yearlyChartData: any = {};  // Update to hold chart data structure
//   yearlyChartLabels: string[] = [];
//   anonymizedData: { category: string; total: number }[] = [];
//   displayedColumns: string[] = ['category', 'total'];
//   chartOptions: ChartOptions = {
//     responsive: true,
//     maintainAspectRatio: false,
//   };
//   monthlyChartType: ChartType = 'pie';
//   yearlyChartType: ChartType = 'bar';


//   constructor(private router: Router, private dashboardService: DashboardService) {}

//   ngOnInit(): void {
//     this.loadSummaryCards();
//     this.loadMonthlyData();
//     this.loadYearlyData();
//     this.loadAnonymizedData();
//   }

//   logout() {
//     localStorage.clear();
//     this.router.navigate(['/login']);
//   }


//   loadSummaryCards() {
//     this.dashboardService.getLifetimeSummary(this.userId).subscribe((data) => {
//       this.summaryCards = [
//         { title: 'Total Expenses', value: `${data.totalExpenses || 0}` },
//         { title: 'Total Income', value: `${data.totalIncome || 0}` },
//         { title: 'Savings', value: `${data.savings || 0}` },
//       ];
//     });
//   }

//   loadMonthlyData() {
//     this.dashboardService.getExpenseSummaryByCategoryForMonth(1, 12, 2024).subscribe((data) => {
//     // Structure the monthly data properly for chart
//     this.monthlyChartLabels = Object.keys(data);
//     this.monthlyChartData = {
//       datasets: [
//         {
//           data: Object.values(data),
//           label: 'Monthly Expenses',
//           backgroundColor: 'rgba(63,81,181,0.5)',
//           borderColor: 'rgba(63,81,181,1)',
//           borderWidth: 1,
//         },
//       ],
//     };
//   });
//   }


//   loadYearlyData() {
//     this.dashboardService.getExpenseSummaryForYear(1, 2024).subscribe((data) => {
//       // Structure the yearly data properly for chart
//       this.yearlyChartLabels = Object.keys(data) as string[];
//       this.yearlyChartData = {
//         datasets: [
//           {
//             data: Object.values(data).map((item: any) =>
//               (Object.values(item) as number[]).reduce((a, b) => a + b, 0)
//             ),
//             label: 'Yearly Expenses',
//             backgroundColor: 'rgba(76,175,80,0.5)',
//             borderColor: 'rgba(76,175,80,1)',
//             borderWidth: 1,
//           },
//         ],
//       };
//     });
//   }

//   loadAnonymizedData() {
//     this.dashboardService.getAnonymizedExpenseSummaryByCategory().subscribe((data) => {
//       this.anonymizedData = Object.entries(data).map(([category, total]) => ({
//         category,
//         total: total as number,
//       }));
//     });
//   }
// }

