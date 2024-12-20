import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { Router, RouterModule } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [SharedModule, RouterModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  user = {
    email: '',
    password: ''
  };

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    const { email, password } = this.user;
  
    this.http.post('http://localhost:8080/api/users/login', { email, password }).subscribe({
      next: (response: any) => {
        // Store user data in localStorage
        localStorage.setItem('userId', response.id);
        localStorage.setItem('fullName', response.fullName);
  
        // Navigate to dashboard
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        // Handle errors
        if (err.status === 401) {
          alert('Invalid email or password. Please try again.');
        } else {
          alert('Login failed. Please try again later.');
        }
        console.error('Error:', err);
      },
    });
  }
  
  
}
