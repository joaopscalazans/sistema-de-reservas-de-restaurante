import { Component, inject } from '@angular/core';
import {FormsModule} from '@angular/forms'
import { AuthService } from '../../service/auth.service';
import { Login } from '../../model/login';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  private authService = inject(AuthService)
  private router = new Router();
  user:Login = {
    email: '',
    password: ''
  }

  login(){
    this.authService.login(this.user).subscribe({
      next: res =>{
        this.router.navigate(["/home"]);
      },
      error: err =>{
        alert(err.error.message)
      }
    });
  }

}
