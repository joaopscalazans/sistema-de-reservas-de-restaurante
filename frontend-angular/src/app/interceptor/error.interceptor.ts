import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router)

  return next(req).pipe(
    catchError(err => {
      switch (err.status) {
        case 0:
          alert("Erro de conexão com o servidor. Verifique se o backend está rodando Sem conexão com o servidor\n\nPara resolver:\n1. Rode o backend (Spring Boot)\n2. Verifique a porta 8080\n3. Atualize a página");
          break;
        case 401:
          alert("Sessão expirada. Faça o login novamente");
          localStorage.clear();
          router.navigate(["/auth/login"]);
          break;
        case 403:
          alert("Acesso Negado. Faça o login novamente");
          localStorage.clear();
          router.navigate(["/auth/login"]);
          break;
        case 500:
          alert("Erro no servidor");
          break;
      }
      return throwError(() => err);
    })
  );
};
