import { Component, inject } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Register } from '../../model/register';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  private authService = inject(AuthService);
  private route = inject(Router);
  newUser: Register = {
    name: '',
    email: '',
    password: ''
  }

  register(){
    this.authService.register(this.newUser).subscribe({
    next: () => {
      this.route.navigate([""])
    },
    error: err =>{
      alert(err.error.message)
    }
    });
    
  }

}
