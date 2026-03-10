import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Login } from '../model/login';
import { Register } from '../model/register';
import { Token } from '../model/token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url: string = "http://localhost:8080/user"


  constructor(private http: HttpClient) { }

  public login(user: Login) {
   return this.http.post<Token>(`${this.url}/login`, user).pipe(
      tap(res =>{
        console.log(res);
        const token = res.token;
        const payload = JSON.parse(atob(token.split(".")[1]))
        const expired = payload.exp * 1000;
        const role = payload.role;
        localStorage.setItem("token", token);
        localStorage.setItem("token_expiredAt", expired.toString())
        localStorage.setItem("role", role)
      }));
  }

  public register(user: Register): Observable<void> {
    return this.http.post<void>(`${this.url}/register`, user);
  }

  public isAuthenticated():boolean{
    const token = localStorage.getItem("token")
    const expiredAt = localStorage.getItem("token_expiredAt")
    const role = localStorage.getItem("role")
    if(!token || !expiredAt || !role){
      return false;
    }
    const now = Date.now();
   
    return now < Number(expiredAt) || (localStorage.clear(), false)
  }
}
