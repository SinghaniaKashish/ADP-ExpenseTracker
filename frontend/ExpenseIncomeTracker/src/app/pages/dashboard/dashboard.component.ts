import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SharedModule } from '../../shared/shared.module';
import { NavbarComponent } from "../../navbar/navbar.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [SharedModule, NavbarComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  fullName: string | null = localStorage.getItem('fullName');

  constructor(private router: Router) {}

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}

