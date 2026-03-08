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
        var payload = JSON.parse(atob(token.split(".")[1]))
        var expired = payload.exp * 1000;
        localStorage.setItem("authToken", token);
        localStorage.setItem("expiredAt", expired.toString())
      }),
      catchError(err => {
        console.log(err)
        return throwError(() => err);
      }
      ));


  }

  public register(user: Register): Observable<void> {
    return this.http.post<void>(`${this.url}/register`, user).pipe(catchError(err => {
      console.log(err);
      return throwError(() => err);
    }));
  }
}
