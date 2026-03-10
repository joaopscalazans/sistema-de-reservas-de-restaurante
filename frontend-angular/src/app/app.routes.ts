import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { authGuard } from './guards/auth.guard';
import { AuthComponent } from './layout/auth/auth.component';
import { RegisterComponent } from './pages/register/register.component';
import { MainBackgroundLayoutComponent } from './layout/main-background-layout/main-background-layout.component';
import { DiningTableComponent } from './pages/dining-table/dining-table.component';

export const routes: Routes = [
    {path:"", redirectTo:"main",pathMatch:"full"},
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
        path:"main",
        component: MainBackgroundLayoutComponent,
        canActivate:[authGuard],
        children:[
            {path:'',redirectTo:'home',pathMatch:'full'},
            {path:"home",component:HomeComponent},
            {path:"dining-table",component:DiningTableComponent}
        ]
    }
];
