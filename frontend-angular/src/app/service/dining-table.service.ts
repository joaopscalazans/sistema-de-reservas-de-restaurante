import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { DiningTableRequest } from '../model/diningtable/dining-table-request';
import { DiningTable } from '../model/diningtable/dining-table';

@Injectable({
  providedIn: 'root'
})
export class DiningTableService {

  private url = "http://localhost:8080/dining-table"
  private http = inject(HttpClient);

  public save(table: DiningTableRequest){
    return this.http.post<void>(this.url,table);
  }

  public listAll(){
    return this.http.get<DiningTable[]>(this.url);
  }

  public update(id:number,table:DiningTableRequest){
    return this.http.patch<void>(`${this.url}/${id}`,table);
  }

  public delete(id:number){
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
