import { Component, inject } from '@angular/core';
import { RouterOutlet, RouterLink, Router } from "@angular/router";
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-main-background-layout',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './main-background-layout.component.html',
  styleUrl: './main-background-layout.component.css'
})
export class MainBackgroundLayoutComponent {

  private authService = inject(AuthService)
  private router = inject(Router)

  logout(){
    this.authService.logout();
    this.router.navigate(["auth/login"]);
  }

  getRole(){
    return this.authService.getRole()
  }

}
