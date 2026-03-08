import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { authGuard } from './guards/auth.guard';
import { AuthComponent } from './layout/auth/auth.component';
import { RegisterComponent } from './pages/register/register.component';

export const routes: Routes = [
    {path:"", redirectTo:"home",pathMatch:"full"},
    {
        path:'auth',
        component:AuthComponent,
        children: [
            {path:"",redirectTo:"login",pathMatch:"full"},
            {path:"login",component:LoginComponent},
            {path:"register",component:RegisterComponent}
        ]
    },
    {
        path:"home",
        component: HomeComponent,
        canActivate:[authGuard]
    }
];
