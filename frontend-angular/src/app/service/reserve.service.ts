import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ReserveRequest } from '../model/reserve/reserveRequest';
import { Reserve } from '../model/reserve/reserve';

@Injectable({
  providedIn: 'root'
})
export class ReserveService {

  private url = "http://localhost:8080/reserve";
  private http = inject(HttpClient);

  save(reserve : ReserveRequest){
    return this.http.post<void>(this.url, reserve);
  }

  getAll(){
    return this.http.get<Reserve[]>(this.url);
  }

  cancel(id:number){
    return this.http.patch<void>(`${this.url}/${id}/cancel`,null);
  }
}
