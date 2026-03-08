import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = () =>  {
  
  const route = inject(Router);
  const authService = inject(AuthService);

  if(authService.isAuthenticated()){
    return true
  }
  route.navigate(["/auth"])
  return false;
};
